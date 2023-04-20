import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ObstaclePanel extends JPanel implements Runnable  {


                //SPAWNUJE RANDOMOWO KAMIENIE
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()){
                double random = (Math.random() * 1500) + 1200;      // Losuje co ile milisekund sie respi
                spawnRock();
                Thread.sleep(500 + (int)random);
            }
            }catch (InterruptedException e) {
                System.out.println("zakonczono przesuwanie kamienia");
        }
    }

    public ObstaclePanel() {


        setLayout(null);

        ImageIcon bushImage = new ImageIcon("src/graphics/bush_obstacle.png");
        JButton bush = new JButton();
        bush.setIcon(bushImage);
        bush.setBounds(200,300,250, 200);
        bush.setBorder(BorderFactory.createEmptyBorder());
        bush.setFocusable(false);                               //TUTAJ ZMIENIAM BORDER
        add(bush);                                              // NIE PRZESLANO W PROJEKCIE
        bush.setVisible(true);
        setVisible(true);
        bush.setOpaque(false);
        setOpaque(false);
        bush.setContentAreaFilled(false);
        bush.setBorderPainted(false);
        Thread spawnSomeRocks = new Thread(this);
        spawnSomeRocks.start();


    }
    public void spawnRock(){
        Rock rock = new Rock();
        add(rock);
        int random = (int)(Math.random()*350)+40; // wspolrzedna Y Kamienia

        if(rock.getMoveRight()) {
            rock.setIcon(new ImageIcon("src/graphics/rock_obstacle.png"));
            rock.setBounds(-50, random, rock.getWidth(), rock.getHeight());
        }else {
            rock.setBounds(1050, random, rock.getWidth(), rock.getHeight());
            rock.setIcon(new ImageIcon("src/graphics/rock_obstacle.png"));
        }
        Thread moveRock = new Thread(rock);
        moveRock.start();
    }



}
