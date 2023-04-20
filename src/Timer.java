
import java.time.LocalTime;


public class Timer implements Runnable{

    LocalTime timer = LocalTime.of(0,0,0);
    InfoPanel infoPanel;
    int counter = 0;


    public Timer(InfoPanel infoPanel) {
         this.infoPanel = infoPanel;
    }

    @Override
    public void run() {
//        System.out.println("wejscie w watek timer");
        while(!Thread.currentThread().isInterrupted()){
            if(infoPanel.getHealthPoints() <= 0){
                Thread.currentThread().interrupt();
            }
            try {
//                System.out.println(timer.getSecond());
                timer = timer.plusSeconds(1);
                infoPanel.setTimerLabel(getCurrentTimer());
                if(counter % 5 == 0){
                    setSpeed();
                }
                counter++;
                System.out.println(counter);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;

            }
        }
    }

    public void setSpeed(){
        GamePanel.setDifficulty();

        for (YellowDuck duck : GamePanel.getDuckList()){
            duck.setSpeed(GamePanel.getDifficulty());

        }


    }

    public String getCurrentTimer() {
        return "Time: " + timer;
    }
}
