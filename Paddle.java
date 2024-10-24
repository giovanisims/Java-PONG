import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class Paddle extends JComponent {
    private Game game;
    private Color color;
    private int x = 0;
    private int y = 50;
    private int height = 90;
    private int width = 10;

    Paddle(Color color, Game game){
        this.color = color;
        this.game = game;
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fillOval(x,y,width,height);
    }

    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP: { y -= 5; break;}
            case KeyEvent.VK_DOWN:{ y += 5; break;}
        }
    }

}
