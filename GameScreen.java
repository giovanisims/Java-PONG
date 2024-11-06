import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
class GameScreen extends Screen implements ActionListener, KeyListener {
    private static final int DIAMETER = 30, TIMER_DELAY = 30;
    private int x = 399, y = 399, xSpeed, ySpeed, playerScore = 0;
    private boolean gameRunning = true, upPressed = false, downPressed = false;
    private double gameLevel = 1;
    private final Timer timer;
    private final Random RANDOM;
    private final PlayerPaddle PLAYERPADDLE;
    private final AIPaddle AIPADDLE;
    private final JFrame frame;
    int windowWidth = getPreferredSize().width, windowHeight = getPreferredSize().height;
    double bounceMultiplier = 1;

    public GameScreen(JFrame frame) {
        this.frame = frame;
        RANDOM = new Random();
        resetBallSpeed();

        timer = new Timer(TIMER_DELAY, this);
        timer.start();
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
            g.drawString(getPlayerScoreText(), getPreferredSize().width / 2 - 80, 40);
        }
    }

    public String getPlayerScoreText() {
        return "Score: " + playerScore;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning) {
            moveBall();
            AIPADDLE.move();
            handlePaddleMovement();
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
        if (x <= 0) {
            gameRunning = false;
            timer.stop();
            showGameOverScreen();
        } else if (x >= getWidth() - DIAMETER) {
            playerScore += 10;
            System.out.println("Score: " + playerScore);
            gameLevel += 0.3;
            bounceMultiplier = 0.5;
            resetGame();
        } else if (x <= PLAYERPADDLE.x + PLAYERPADDLE.width && y + DIAMETER >= PLAYERPADDLE.y && y <= PLAYERPADDLE.y + PLAYERPADDLE.height) {
            xSpeed = -xSpeed;
            bounceMultiplier += bounceMultiplier;
            xSpeed *= bounceMultiplier;
            ySpeed *= bounceMultiplier;
        } else if (x + DIAMETER >= AIPADDLE.x && y + DIAMETER >= AIPADDLE.y && y <= AIPADDLE.y + AIPADDLE.height) {
            xSpeed = -xSpeed;
            bounceMultiplier += bounceMultiplier;
            xSpeed *= bounceMultiplier;
            ySpeed *= bounceMultiplier;
        }

        if (y <= 0 || y >= getHeight() - DIAMETER) {
            ySpeed = -ySpeed;
            bounceMultiplier += bounceMultiplier;
            xSpeed *= bounceMultiplier;
            ySpeed *= bounceMultiplier;
        }
    }

    private void showGameOverScreen() {
        SwingUtilities.invokeLater(() -> {
            frame.getContentPane().removeAll();
            GameOverScreen gameOverScreen = new GameOverScreen(frame, playerScore);
            frame.add(gameOverScreen);
            frame.revalidate();
            frame.repaint();
        });
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