import java.awt.*;

class AIPaddle extends Paddle {
    private int speed = windowHeight / 50;

    public AIPaddle(int x, int y, int width, int height, int windowHeight) {
        super(x, y, width, height, windowHeight);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void move() {
        y += speed;
        if (y <= 0 || y >= windowHeight - height) {
            speed = -speed;
        }
    }
}