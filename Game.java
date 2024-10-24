import javax.swing.*;
import java.awt.*;


public class Game extends JPanel {
    private JFrame frame;
    boolean crash = false;

    Game(String name){
        frame = (JFrame) new Frame(name);
        frame.add(this);
        frame.setSize(400, 320);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void play(){
        boolean playing = true;

    }








}
