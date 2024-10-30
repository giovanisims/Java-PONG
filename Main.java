import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    //declare and initialize the frame
    static JFrame f = new JFrame("Pong");

    public static void main(String[] args) {

        //make it so program exits on close button click
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //the size of the game will be 480x640
        f.setSize(650,495);

        PongGame game = new PongGame();
        f.add(game);

        //show the window
        f.setVisible(true);

        timerRefresh(game);


    }

    private static void timerRefresh(PongGame game) {
        int DELAY = 33;

        Timer timerRefresh = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                game.gameLogic();

                game.repaint();

            }
        });

        timerRefresh.start();
    }
}