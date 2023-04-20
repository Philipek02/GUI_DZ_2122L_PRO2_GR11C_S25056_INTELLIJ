import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JFrame {
    final int windowHeight = 600;
    final int windowWidth = 1000;
    int difficulty;

    Game(int difficulty){



        this.difficulty = difficulty;
        setTitle("DUCK SHOOTER");
        setSize(windowWidth,windowHeight);
        setLocationRelativeTo(null);
        setResizable(false);
        ImageIcon logo = new ImageIcon("src/graphics/logo.png");
        setIconImage(logo.getImage());

        setLayout(new BorderLayout());
        InfoPanel ip = new InfoPanel();

        JLayeredPane test = new JLayeredPane();
        GamePanel gp = new GamePanel(this, ip, difficulty);
        ObstaclePanel obstaclePanel = new ObstaclePanel();
        test.add(obstaclePanel,1,0);
        obstaclePanel.setBounds(0,0,getWidth(),getHeight());
        test.add(gp,0,0);
        gp.setBounds(0,0,getWidth(),getHeight());
        add(test);
        test.setVisible(true);

        add(ip, BorderLayout.NORTH);


        Thread spawnSomeDucks = new Thread(gp);
        spawnSomeDucks.start();




        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                spawnSomeDucks.interrupt();
            }
        });
        setVisible(true);


        // https://www.tabnine.com/code/java/methods/javax.swing.KeyStroke/getKeyStroke
        AbstractAction abstractAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gp.closeGame();
            }
        };
        //Tworzenie wlasnego skrotu
        KeyStroke ctrlShiftQKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q,KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK);
        //Dodawanie do input mapy
        gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ctrlShiftQKeyStroke,"closeOperation");
        //przypisywanie operacji do KEY w Mapie i umieszczanie w Action mapie
        gp.getActionMap().put("closeOperation", abstractAction);
    }



}
