import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

abstract class Paddle {
    protected int x, y, width, height;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void draw(Graphics g);
    public abstract void move();
}

class PlayerPaddle extends Paddle {
    public PlayerPaddle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void move() {
        // Não utilizado
    }

    public void moveUp() {
        if (y > 0) {
            y -= 45;
        }
    }

    public void moveDown() {
        if (y < 800 - height) {
            y += 45;
        }
    }
}

class AIPaddle extends Paddle {
    private int speed = 15;
    public AIPaddle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void move() {
        // Movimento automático
        y += speed;
        if (y <= 0 || y >= 800 - height) {
            speed = -speed;
        }
    }
}

public class GameScreen extends JPanel implements ActionListener, KeyListener {
    private int x = 399, y = 399, diameter = 30;
    private int xSpeed, ySpeed;
    private Timer timer;
    private boolean gameRunning = true;
    private Random random;
    private PlayerPaddle playerPaddle;
    private AIPaddle aiPaddle;
    private int playerScore = 0;

    public GameScreen() {
        random = new Random();
        xSpeed = random.nextInt(7,12) + 1;
        ySpeed = random.nextInt(7,12) + 1;
        timer = new Timer(30, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);

        playerPaddle = new PlayerPaddle(50, 350, 10, 100);
        aiPaddle = new AIPaddle(740, 350, 10, 100);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameRunning) {
            g.setColor(Color.RED);
            g.fillOval(x, y, diameter, diameter);
            playerPaddle.draw(g);
            aiPaddle.draw(g);

            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Pontuação:" + playerScore, 10, 30);
        } else {
            g.setColor(Color.BLACK);
            g.drawString("Game Over", getWidth() / 2 - 30, getHeight() / 2);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning) {
            moveBall();
            playerPaddle.move();
            aiPaddle.move();
            repaint();
        }
    }

    private void resetGame() {
        x = random.nextInt(300, 500);
        y = random.nextInt(300, 400);
        if(random.nextBoolean()) {
            xSpeed = random.nextInt(7,12) + 1;
        } else {
            xSpeed = -random.nextInt(7,12) + 1;
        }
        
        ySpeed = random.nextInt(7,12) + 1;
        playerPaddle = new PlayerPaddle(50, 350, 10, 100);
        aiPaddle = new AIPaddle(740, 350, 10, 100);
        gameRunning = true;
        timer.start();
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
        if (x <= playerPaddle.x + playerPaddle.width && y + diameter >= playerPaddle.y && y <= playerPaddle.y + playerPaddle.height) {
            xSpeed = -xSpeed;
        } else if (x + diameter >= aiPaddle.x && y + diameter >= aiPaddle.y && y <= aiPaddle.y + aiPaddle.height) {
            xSpeed = -xSpeed;
        } else if (x <= 0) {
            gameRunning = false;
            timer.stop();
        } else if (x >= getWidth() - diameter) {
            playerScore += 10;
            System.out.println("Score: " + playerScore);
            resetGame();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            playerPaddle.moveUp();
        }
        if (key == KeyEvent.VK_DOWN) {
            playerPaddle.moveDown();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Não utilizado
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Não utilizado
    }

}