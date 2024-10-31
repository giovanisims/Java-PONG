import java.awt.*;

public class Ball {
    private int x;
    private int y;
    private int cx;
    private int cy;
    private int size;
    private int speed;
    private Color color;

    public Ball(int x, int y, int cx, int cy, int speed, Color color, int size) {
        this.x = x;
        this.y = y;
        this.cx = cx;
        this.cy = cy;
        this.speed = speed;
        this.color = color;
        this.size = size;
    }

    public void paint(Graphics g) {

        g.setColor(color);

        g.fillOval(x, y, size, size);
    }

    public void moveBall() {
        x += cx;
        y += cy;
    }

    public void bounceOffEdges(int top, int bottom){

        //if the y value is at the bottom of the screen
        if (y > bottom - size){
            reverseY();
        }
        //if y value is at top of screen
        else if(y < top){
            reverseY();
        }

        //if x value is at left or right side
        //hard-coded values, we will delete this section later
        if(x < 0){
            reverseX();
        }
        else if(x > 640 - size){
            reverseX();
        }

    }

    private void reverseX() {
        cx = cx * -1;
    }

    private void reverseY() {
        cy = cy * -1;
    }
}