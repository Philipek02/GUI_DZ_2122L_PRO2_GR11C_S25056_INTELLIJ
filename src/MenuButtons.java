import javax.swing.*;
import java.awt.*;

public class MenuButtons extends JButton {
    MenuButtons(String text){
        super(text);
        setPreferredSize(new Dimension(150,50));
        setAlignmentX(CENTER_ALIGNMENT);
        setMaximumSize(new Dimension(200,100));
        setVisible(true);


    }

}
