import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.Hashtable;

public class DifficultyFrame extends JFrame {



        final int windowHeight = 600;
        final int windowWidth = 1000;
        DifficultyFrame() {
            setTitle("DIFFICULTY SELECT");
            setSize(windowWidth, windowHeight);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setResizable(false);
            ImageIcon logo = new ImageIcon("src/graphics/logo.png");
            setIconImage(logo.getImage());

            JPanel panel = new JPanel();


            panel.setBackground(new Color(0xFF0475A9, true));
            panel.setLayout( new BoxLayout(panel, BoxLayout.Y_AXIS));
            JLabel title = new JLabel("CHOOSE DIFFICULTY LEVEL");
            title.setSize(200,100);
            panel.add(Box.createVerticalGlue());
            panel.add(title);

            title.setAlignmentX(CENTER_ALIGNMENT);

            JSlider slider = new JSlider(SwingConstants.HORIZONTAL,
                    1,3,2);


            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            slider.setMinorTickSpacing(1);
            slider.setMajorTickSpacing(1);
                // PRZYPISYWANIE JLABELOW DO WARTOSCI
                //http://www.java2s.com/Tutorial/Java/0240__Swing/LabelingJSliderPositionsusetexttomarkJSlider.htm
            Hashtable<Integer, JLabel> difficulties =
                    new Hashtable<Integer, JLabel>();
            difficulties.put(1, new JLabel("Easy"));
            difficulties.put(2, new JLabel("Medium"));
            difficulties.put(3, new JLabel("Hard"));
            slider.setLabelTable(difficulties);


            panel.add(slider);
            slider.setMaximumSize(new Dimension(200,20));

            panel.add(Box.createVerticalGlue());

            JButton confirmButton = new JButton();

            confirmButton.setAlignmentX(CENTER_ALIGNMENT);
            confirmButton.setText("CONFIRM");
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int value = slider.getValue();
                    SwingUtilities.invokeLater(()->new Game(value));
                    dispose();
                }
            });
            panel.add(Box.createVerticalGlue());
            panel.add(confirmButton);
            panel.add(Box.createVerticalGlue());
            slider.setBackground(new Color(0xFF0475A9, true));




            add(panel);
            panel.setVisible(true);
            setVisible(true);



        }

}
