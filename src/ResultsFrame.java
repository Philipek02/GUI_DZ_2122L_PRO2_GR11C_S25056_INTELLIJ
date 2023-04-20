import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class ResultsFrame extends JFrame {
    final int windowHeight = 600;
    final int windowWidth = 1000;

    ResultsFrame(InfoPanel infoPanel, JLabel usersTime){
        setTitle("DUCK SHOOTER RESULTS");
        setSize(windowWidth,windowHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        ImageIcon logo = new ImageIcon("src/graphics/logo.png");
        setIconImage(logo.getImage());

        JPanel panel = new JPanel();
        panel.setLayout( new BoxLayout(panel, BoxLayout.Y_AXIS));
        setVisible(true);
        panel.setVisible(true);
        add(panel);
        setBackground(Color.BLUE);
        panel.add(Box.createVerticalGlue());
        JLabel text = new JLabel("GAME OVER! \n" +
                "YOUR SCORE:");

        panel.add(new JLabel());
        panel.add(text);

        text.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(new JLabel(""+ infoPanel.getScore()));
        panel.add(usersTime);
        usersTime.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton returnButton = new JButton();
        returnButton.setText("Return to MENU");
        panel.add(returnButton);
        returnButton.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(Box.createVerticalGlue());
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }


}
