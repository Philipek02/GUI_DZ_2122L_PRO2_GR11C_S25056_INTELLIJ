import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
   final int windowHeight = 600;
   final int windowWidth = 1000;
   GUI(){
       setTitle("DUCK SHOOTER MENU");
       setSize(windowWidth,windowHeight);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setResizable(false);
       ImageIcon logo = new ImageIcon("src/graphics/logo.png");
       setIconImage(logo.getImage());

       JPanel panel = new JPanel();

       MenuButtons newGameButton = new MenuButtons("NEW GAME");
       MenuButtons highScoresButton = new MenuButtons("HIGH SCORES");
       MenuButtons exitButton = new MenuButtons("EXIT");

       panel.setBackground(new Color(0xFF0475A9, true));
       panel.add(Box.createVerticalGlue());
       panel.add(newGameButton);
       panel.add(Box.createVerticalGlue());
       panel.add(highScoresButton);
       panel.add(Box.createVerticalGlue());
       panel.add(exitButton);
       panel.add(Box.createVerticalGlue());

       panel.setLayout( new BoxLayout(panel, BoxLayout.Y_AXIS));
       setVisible(true);
       panel.setVisible(true);
       add(panel);

       ActionListener newGameListener = new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              SwingUtilities.invokeLater(()->new DifficultyFrame());


          }
       };
       newGameButton.addActionListener(newGameListener);

       ActionListener exitListener = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.exit(0);

           }
       };
        exitButton.addActionListener(exitListener);


    }

}
