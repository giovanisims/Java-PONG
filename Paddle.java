import java.awt.*;

abstract class Paddle {
    protected int x, y, width, height;
    protected int windowHeight;

    public Paddle(int x, int y, int width, int height, int windowHeight) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.windowHeight = windowHeight;
    }

    public abstract void draw(Graphics g);

    public abstract void move();
}