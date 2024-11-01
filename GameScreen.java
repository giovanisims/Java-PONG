import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class GameScreen extends JPanel implements ActionListener, KeyListener {
    private static final int DIAMETER = 30, TIMER_DELAY = 30;
    private int x = 399, y = 399, xSpeed, ySpeed, playerScore = 0;
    private boolean gameRunning = true, upPressed = false, downPressed = false;
    private double gameLevel = 1;
    private final Timer timer;
    private final Random RANDOM;
    private final PlayerPaddle PLAYERPADDLE;
    private final AIPaddle AIPADDLE;
    int windowWidth = getPreferredSize().width, windowHeight = getPreferredSize().height;

    public GameScreen() {
        RANDOM = new Random();
        resetBallSpeed();

        // Game refresh rate in ms, higher means slower game
        timer = new Timer(TIMER_DELAY, this);
        timer.start();
        setFocusable(true);
        addKeyListener(this);

        int PADDLEWIDTH = 20;
        int PADDLEHEIGHT = windowHeight / 8;

        PLAYERPADDLE = new PlayerPaddle(50, windowHeight / 2 - PADDLEHEIGHT / 2, PADDLEWIDTH, PADDLEHEIGHT, windowHeight);
        AIPADDLE = new AIPaddle(windowWidth - 70, windowHeight / 2 - PADDLEHEIGHT / 2, PADDLEWIDTH, PADDLEHEIGHT, windowHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameRunning) {
            g.setColor(Color.RED);
            g.fillOval(x, y, DIAMETER, DIAMETER);
            PLAYERPADDLE.draw(g);
            AIPADDLE.draw(g);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Score: " + playerScore, 10, 40);
        } else {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over", getWidth() / 2 - 100, getHeight() / 2);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1280, 720);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning) {
            moveBall();
            AIPADDLE.move();
            handlePaddleMovement(); // Handles paddle movement based on key state
            repaint();
        }
    }

    private void handlePaddleMovement() {
        if (upPressed) {
            PLAYERPADDLE.moveUp();
        }
        if (downPressed) {
            PLAYERPADDLE.moveDown();
        }
    }

    private void resetGame() {
        x = 300 + RANDOM.nextInt(201);
        y = 300 + RANDOM.nextInt(101);
        resetBallSpeed();
        PLAYERPADDLE.y = 350;
        AIPADDLE.y = 350;
        gameRunning = true;
        timer.start();
    }

    private void resetBallSpeed() {
        xSpeed = (int) ((-(RANDOM.nextInt(3) + 5)) * gameLevel);
        ySpeed = (int) ((RANDOM.nextBoolean() ? 1 : -1) * (RANDOM.nextInt(3) + 5) * gameLevel);
    }

    private void moveBall() {
        x += xSpeed;
        y += ySpeed;
        handleCollisions();
    }

    private void handleCollisions() {
        if (x <= 0 || x >= getWidth() - DIAMETER) {
            xSpeed = -xSpeed;
        }
        if (y <= 0 || y >= getHeight() - DIAMETER) {
            ySpeed = -ySpeed;
        }
        handlePaddleCollision();
    }

    private void handlePaddleCollision() {
        if (x <= PLAYERPADDLE.x + PLAYERPADDLE.width && y + DIAMETER >= PLAYERPADDLE.y && y <= PLAYERPADDLE.y + PLAYERPADDLE.height) {
            xSpeed = -xSpeed;
        } else if (x + DIAMETER >= AIPADDLE.x && y + DIAMETER >= AIPADDLE.y && y <= AIPADDLE.y + AIPADDLE.height) {
            xSpeed = -xSpeed;
        } else if (x <= 0) {
            gameRunning = false;
            timer.stop();
        } else if (x >= getWidth() - DIAMETER) {
            playerScore += 10;
            System.out.println("Score: " + playerScore);
            gameLevel += 0.1;
            resetGame();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}