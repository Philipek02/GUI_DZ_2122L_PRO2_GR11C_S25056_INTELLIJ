import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.LocalTime;

public class InfoPanel extends JPanel {

//    GamePanel gamePanel;
    int healthPoints = 10;
    int score = 0;
    JLabel healthPointsLabel = new JLabel("Health Points: " + healthPoints);
    JLabel scoreLabel = new JLabel("Score: " + score);
    JLabel timerLabel;
    int power = 1;
    int maxPower = 3;



    //    Thread timer = new Thread(Timer);
//        timer.start();



    InfoPanel(){
            //KOMENDY DO INFOPANELU
        setBackground(new Color(77, 155, 250, 255));
        setPreferredSize(new Dimension(getWidth(), 80));
        setMaximumSize(new Dimension(getWidth(), getHeight()));
        setVisible(true);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            //HEALTHPOINTS PANEL
        JPanel healthPointsPanel = new JPanel();
        healthPointsPanel.setPreferredSize(new Dimension(200,80));
        healthPointsPanel.setLayout(new BoxLayout(healthPointsPanel, BoxLayout.Y_AXIS));
        healthPointsPanel.setBackground(new Color(241, 241, 121));
        healthPointsLabel.setAlignmentY(CENTER_ALIGNMENT);
            //DODAWANIE DO HP PANEL
        healthPointsPanel.add(this.healthPointsLabel);
        healthPointsPanel.add(scoreLabel);


            //TIMER
        JPanel timerPanel = new JPanel();
        timerPanel.setBackground(new Color(77, 155, 250, 255));
        timerPanel.setPreferredSize(new Dimension(100,80));
        timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.X_AXIS));
        timerLabel = new JLabel();
        Timer timer = new Timer(this);
        String timerText = timer.getCurrentTimer();
        timerLabel.setText(timerText);
        timerPanel.add(Box.createVerticalGlue());
        timerPanel.add(timerLabel);



            //WATEK TIMER
        Thread timerThread = new Thread(timer);
        timerThread.start();
        GamePanel.threads.add(timerThread);

            //BUTTON
        JLabel powerLabel = new JLabel("Current power: " + getPower());

        JButton upgradePowerButton = new JButton();
        upgradePowerButton.setText("Upgrade power (-10 score)");
        upgradePowerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((getScore()-11)>=0) {
                    if (getPower() < maxPower) {
                        setPower(getPower() + 1);
                        setPowerLabel(powerLabel);
                        setScore(getScore() - 11);
                        setScoreLabel();
                    }else{
                        System.out.println("Osiagnieto maksymalny Power!");
                    }
                }else{
                    System.out.println("niewystarczajaca ilosc Score!");
                }
            }
        });
        //UPGRADE UPGRADE PANEL
        JPanel upgradePanel = new JPanel();
        upgradePanel.setLayout(new BoxLayout(upgradePanel, BoxLayout.Y_AXIS));
        upgradePanel.setBackground(new Color(241, 241, 121));

        upgradePanel.add(upgradePowerButton);
        upgradePanel.add(powerLabel);
        upgradePanel.add(new JLabel("MAX " + maxPower));


            //DODAWANIE DO INFOPANELU
        add(Box.createHorizontalGlue());
        add(healthPointsPanel);
        add(Box.createHorizontalGlue());
        add(timerPanel);
        add(Box.createHorizontalGlue());
        add(upgradePanel);
        add(Box.createHorizontalGlue());

    }

    public void setPowerLabel(JLabel powerLabel){
        powerLabel.setText("Current power: " + (getPower()));
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setScoreLabel(){
        score++;
        scoreLabel.setText("Score: " + score);

    }

    public void minusHP(){
        this.healthPoints--;
        healthPointsLabel.setText("Health Points: " + this.healthPoints);
    }

    public void setTimerLabel(String timerText) {
        timerLabel.setText(timerText);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public JLabel getTimerLabel() {
        return timerLabel;
    }
}
