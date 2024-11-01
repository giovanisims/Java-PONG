import java.awt.*;

class PlayerPaddle extends Paddle {
    private final int speed = windowHeight / 50;

    public PlayerPaddle(int x, int y, int width, int height, int windowHeight) {
        super(x, y, width, height, windowHeight);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void move() {}

    public void moveUp() {
        if (y > 0) {
            y -= speed;
        }
    }

    public void moveDown() {
        if (y < windowHeight - height) {
            y += speed;
        }
    }
}