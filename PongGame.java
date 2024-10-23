import javax.swing.*;
import java.awt.*;

public class PongGame extends JPanel {
    static final int WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480;
    private Ball gameball;

    public PongGame() {
        // Initialize the gameball
        gameball = new Ball(300, 200, 3, 3, 3, Color.YELLOW, 10);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Draw the ball
        gameball.paint(g);
    }

    public void gameLogic() {

        gameball.moveBall();

        gameball.bounceOffEdges(0, WINDOW_HEIGHT);
    }
}