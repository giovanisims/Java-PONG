import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class ListenerKey implements KeyListener {
    private Paddle paddle;
    private PaddleRival paddleRival;

    ListenerKey(Paddle paddle,PaddleRival paddleRival){
        this.paddle = paddle;
        this.paddleRival = paddleRival;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e){
        paddle.keyPressed(e);
        paddleRival.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
