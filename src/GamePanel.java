import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{

    static ArrayList<Thread> threads = new ArrayList<>();
    static ArrayList<YellowDuck> duckList = new ArrayList<>();

    int healthPoints = 10;

    static int difficulty;

    Game gameFrame;
    InfoPanel infoPanel;

    Image image;

                        // SPAWNUJE RANDOMOWO KACZKI
    @Override
    public void run() {

        DuckColor[] kolory = {DuckColor.Yellow, DuckColor.Blue, DuckColor.Green};
        try {
            while (!Thread.currentThread().isInterrupted()) {

                double random = Math.random() * 700 + 300;      // Losuje co ile milisekund sie respi
                int random2 = (int) (Math.random() * 3);           // Losuje index koloru kaczki

                spawnDuck(kolory[random2]);
                Thread.sleep(500 + (int) random);

            }
        }catch (InterruptedException e){
            for(Thread thread : threads){
                thread.interrupt();
            }
        }
    }

    enum DuckColor{
        Yellow,
        Blue,
        Green
    }

    GamePanel(Game gameFrame, InfoPanel infoPanel, int difficulty){
        super();
        this.difficulty = difficulty;
        this.gameFrame = gameFrame;
        this.infoPanel = infoPanel;
        try {
            image = ImageIO.read(new File("src/graphics/background.png")).getScaledInstance(1000,520, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setVisible(true);
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0,0,null);
    }



    void spawnDuck(DuckColor color){

        int random = (int)(Math.random()*350)+40; // wspolrzedna Y kaczki


        YellowDuck duck = switch (color){
            case Yellow -> new YellowDuck(this,infoPanel, difficulty);
            case Green -> new GreenDuck(this, infoPanel, difficulty);
            case Blue -> new BlueDuck(this,infoPanel, difficulty);
        };

        duck.setHealthPoints(
                duck.getHealthPoints() +
                switch (difficulty) {
                    case 2 -> 1;
                    case 3 -> 2;
                    default -> 0;
                }
        );

        add(duck);
        duckList.add(duck);

        switch (color){
            case Yellow -> {
                if(duck.getMoveRight()) {
                    duck.setIcon(new ImageIcon("src/graphics/yellow_duck_toRight.png"));
                    duck.setBounds(-50, random, duck.getWidth(), duck.getDuckHeight());
                }else {
                    duck.setBounds(1050, random, duck.getWidth(), duck.getDuckHeight());
                    duck.setIcon(new ImageIcon("src/graphics/yellow_duck_toLeft.png"));

                }
            }
            case Blue -> {
                if(duck.getMoveRight()) {
                    duck.setIcon(new ImageIcon("src/graphics/blue_duck_toRight.png"));
                    duck.setBounds(-50, random, duck.getWidth(), duck.getDuckHeight());
                }else {
                    duck.setBounds(1050, random, duck.getWidth(), duck.getDuckHeight());
                    duck.setIcon(new ImageIcon("src/graphics/blue_duck_toLeft.png"));
                }
            }
            case Green -> {
                if(duck.getMoveRight()) {
                    duck.setIcon(new ImageIcon("src/graphics/green_duck_toRight.png"));
                    duck.setBounds(-50, random, duck.getWidth(), duck.getDuckHeight());
                }else {
                    duck.setBounds(1050, random, duck.getWidth(), duck.getDuckHeight());
                    duck.setIcon(new ImageIcon("src/graphics/green_duck_toLeft.png"));
                }
            }
        }

//        System.out.println("jest na wysokosci Y = " + random);

        Thread move = new Thread(duck);
        threads.add(move);
        move.start();
        duck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                duck.setHealthPoints(duck.getHealthPoints()-infoPanel.getPower());
//                System.out.println(duck.getHealthPoints());
                if(duck.getHealthPoints() <= 0){
                    duck.setVisible(false);
                    move.interrupt();
                    infoPanel.setScoreLabel();
                }
            }
        });

        duck.setOpaque(false);
        duck.setContentAreaFilled(false);
        duck.setBorderPainted(false);
//        System.out.println("Nowe health points: " + duck.getHealthPoints());

    }

    public void closeGame(){
        gameFrame.dispatchEvent(new WindowEvent(gameFrame, WindowEvent.WINDOW_CLOSING));
        for(Thread thread : threads){
            thread.interrupt();
        }
        SwingUtilities.invokeLater(()-> new ResultsFrame(infoPanel, infoPanel.getTimerLabel()));
    }


    public void setHealthPoints() {
        this.healthPoints--;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public static ArrayList<YellowDuck> getDuckList() {
        return duckList;
    }

    public static void setDifficulty() {
        difficulty++;
    }

    public static int getDifficulty() {
        return difficulty;
    }
}
