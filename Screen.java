import javax.swing.*;
import java.awt.*;

public abstract class Screen extends JPanel {
    protected static final int WINDOW_WIDTH = 1280;
    protected static final int WINDOW_HEIGHT = 720;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    }
}