import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;

class GameScreen extends JFrame {

    public GameScreen() {
        super("Pong game!");

        // Set the layout of the window
        setLayout(new BorderLayout());

        // Add the ball
        Ball ball = new Ball();
        add(ball, BorderLayout.CENTER);

        // Add the player bar
        Bar bar = new Bar();
        add(bar, BorderLayout.WEST);

        // Window settings
        setSize(800, 800);  // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close the program when the window is closed
        setVisible(true);  // Display the window

        // Request focus for the bar to ensure it receives key events
        bar.requestFocusInWindow();

        // Timer to update the player's position
        Timer timer = new Timer(30, e -> bar.move());
        timer.start();
    }
}

class Ball extends JPanel implements ActionListener {
    private int x, y, diameter;
    private int xSpeed, ySpeed;
    private Timer timer;
    private Random random;

    public Ball() {
        diameter = 20;
        x = (800 - diameter) / 2;
        y = (800 - diameter) / 2;
        random = new Random();
        xSpeed = random.nextInt(5) + 1;
        ySpeed = random.nextInt(5) + 1;
        timer = new Timer(30, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, diameter, diameter);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 800);  // Set the preferred size of the panel
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveBall();
        repaint();
    }

    private void moveBall() {
        x += xSpeed;
        y += ySpeed;

        if (x <= 0 || x >= getWidth() - diameter) {
            xSpeed = -xSpeed;
        }
        if (y <= 0 || y >= getHeight() - diameter) {
            ySpeed = -ySpeed;
        }
    }
}

class Bar extends JPanel implements KeyListener {
    private int y, width, height;
    private int ySpeed;

    public Bar() {
        y = 300;  // Initial position
        width = 20;
        height = 100;
        ySpeed = 0;
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 800);  // Set the preferred size of the bar
    }

    // Draw the player bar
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(30, y, width, height);  // Draw the player bar on the left side

    }

    // Move the player bar
    public void move() {
        y += ySpeed;
        if (y < 0) {
            y = 0;
        }
        if (y > getHeight() - height) {
            y = getHeight() - height;
        }
        repaint();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            ySpeed = -5;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            ySpeed = 5;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            ySpeed = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}