import javax.swing.*;
import java.awt.*;

public class PongGame extends JPanel {
    static final int WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480;
    private Ball gameball;
    private Paddle userPaddle, pcPaddle;

    public PongGame() {
        // Initialize the gameball
        gameball = new Ball(300, 200, 3, 3, 3, Color.YELLOW, 10);
        userPaddle = new Paddle(10, 200, 75, 3, Color.BLUE);
        pcPaddle = new Paddle(610, 200, 75, 3, Color.RED);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Draw the ball
        gameball.paint(g);

        // Draw the paddle
        userPaddle.paint(g);
        pcPaddle.paint(g);
    }


    public void gameLogic() {

        gameball.moveBall();

        gameball.bounceOffEdges(0, WINDOW_HEIGHT);
    }
}