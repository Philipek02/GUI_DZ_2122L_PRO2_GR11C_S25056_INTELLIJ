import javax.swing.*;

public class Rock extends JButton implements Runnable{
    boolean moveRight = true;
    int rockHeight = 120;
    int rockWidth = 120;
    int rockSpeed = 4;

    Rock(){
        double randomDirection = Math.random()*3+1;
        if((int)randomDirection == 1){
            moveRight = false;
        }
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }
    @Override
    public void run() {
        if (moveRight) {
            while (getX() < (1000) + rockWidth && !Thread.currentThread().isInterrupted()) {
                this.setBounds(this.getX() + rockSpeed, this.getY(), rockWidth  , rockHeight);

                try {
                    Thread.sleep(1000 / 45);
                } catch (InterruptedException e) {
                    System.out.println("przerwano przesuwanie");
                    return;
                }
            }



        }else {
            while (getX() > (-rockWidth) && !Thread.currentThread().isInterrupted()) {
                this.setBounds(this.getX() - rockSpeed, this.getY(), rockWidth, rockHeight);
                try {
                    Thread.sleep(1000 / 45);
                } catch (InterruptedException e) {
                    System.out.println("przerwano przesuwanie");
                    return;
                }
            }


        }
    }

    public boolean getMoveRight() {
        return moveRight;
    }
}
