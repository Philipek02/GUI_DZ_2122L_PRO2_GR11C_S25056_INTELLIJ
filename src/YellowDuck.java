import javax.swing.*;
import java.awt.*;


public class YellowDuck extends JButton implements Runnable{

            //GLOWNA KLASA KACZKI - PO NIEJ DZIEDZICZA INNE KOLORY


    int duckHeight = 70;
    int duckWidth = 70;
    int healthPoints = 1;
    int speed;
    boolean moveRight = true;
    GamePanel gamePanel;
    InfoPanel infoPanel;
    @Override
    public void run() {
        if (getMoveRight()) {
            while (getX() < 1000 + duckWidth && !Thread.currentThread().isInterrupted()) {
                    this.setBounds(this.getX() + speed, this.getY(), duckWidth, duckHeight);

                    try {
                        Thread.sleep(1000 / 45);
                    } catch (InterruptedException e) {
                        System.out.println("przerwano przesuwanie");
                        return;
                    }
            }
//            System.out.println("kaczka znika ");
            gamePanel.setHealthPoints();
            infoPanel.minusHP();
//            System.out.println("Pozostale HP: " + gamePanel.getHealthPoints());
            if(gamePanel.getHealthPoints() == 0){
                gamePanel.closeGame();
            }

        }else {
            while (getX() > -duckWidth && !Thread.currentThread().isInterrupted()) {
                this.setBounds(this.getX() - speed, this.getY(), duckWidth, duckHeight);
                try {
                    Thread.sleep(1000 / 45);
                } catch (InterruptedException e) {
                    System.out.println("przerwano przesuwanie");
                    return;
                }
            }
            System.out.println("kaczka znika");
            gamePanel.setHealthPoints();
            infoPanel.minusHP();
//            System.out.println("Pozostale HP: " + gamePanel.getHealthPoints());
            if(gamePanel.getHealthPoints() <= 0){
                gamePanel.closeGame();

            }
        }
    }

    public YellowDuck(GamePanel gamePanel, InfoPanel infoPanel, int difficulty){
        this.speed = difficulty;
        this.gamePanel = gamePanel;
        this.infoPanel = infoPanel;
        double randomDirection = Math.random()*3+1;
        if((int)randomDirection == 1){
             this.moveRight = false;
        }


        setPreferredSize(new Dimension(duckWidth,duckHeight));
        setMaximumSize(new Dimension(duckWidth,duckHeight));

        setVisible(true);
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getDuckHeight() {
        return duckHeight;
    }

    public boolean getMoveRight() {
        return moveRight;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
