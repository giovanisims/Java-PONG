import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuScreen extends Screen {
    public MenuScreen(JFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Pong Game", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 50));
        add(title, BorderLayout.NORTH);

        PlayerData playerData = new PlayerData();
        ArrayList<Player> top5 = playerData.getTop5();

        JPanel top5Panel = new JPanel();
        top5Panel.setLayout(new BoxLayout(top5Panel, BoxLayout.Y_AXIS));
        for (Player player : top5) {
            JLabel playerLabel = new JLabel(player.name + ": " + player.highscore);
            playerLabel.setFont(new Font("Arial", Font.PLAIN, 60));
            top5Panel.add(playerLabel);
        }

        // For some reason the panel needs a wrapper
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(top5Panel);

        add(wrapperPanel, BorderLayout.CENTER);

        JButton startButton = getStartButton(frame);
        add(startButton, BorderLayout.SOUTH);
    }

    private static JButton getStartButton(JFrame frame) {
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 30));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                GameScreen gameScreen = new GameScreen(frame);
                frame.add(gameScreen);
                frame.revalidate();
                frame.repaint();

                gameScreen.requestFocusInWindow(); // Needed for arrow keys to work
            }
        });
        return startButton;
    }
}