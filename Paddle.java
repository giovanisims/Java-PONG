import java.awt.*;

public class Paddle {

    private int height, x, y, speed;
    private Color color;

    static final int PADDLE_WIDTH = 15;

    /**
     * A paddle is a rectangle/block that can move up and down
     * @param x the x position to start drawing the paddle
     * @param y the y position to start drawing the paddle
     * @param height the paddle height
     * @param speed the amount the paddle may move per frame
     * @param color the paddle color
     */

    public Paddle(int x, int y, int height, int speed, Color color) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.speed = speed;
        this.color = color;
    }

    public void paint(Graphics g) {

        g.setColor(color);
        g.fillRect(x, y, PADDLE_WIDTH, height);
    }

    public void moveTowards(int moveToY) {

        int centerY = y + height / 2;

        if(centerY > moveToY){
            //move the paddle up by the speed
            y -= speed;
        }

        if(centerY < moveToY){
            //move the paddle down by speed
            y += speed;
        }

    }
}
