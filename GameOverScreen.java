import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScreen extends JPanel {
    private final JTextField nameField;
    private final int playerScore;

    public GameOverScreen(JFrame frame, int playerScore) {
        this.playerScore = playerScore;
        setLayout(new BorderLayout());

        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 50));
        add(gameOverLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Enter your name (4 characters): ");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        inputPanel.add(nameLabel);

        nameField = new JTextField(4);
        nameField.setFont(new Font("Arial", Font.PLAIN, 20));
        inputPanel.add(nameField);

        add(inputPanel, BorderLayout.CENTER);

        JLabel scoreLabel = new JLabel(getPlayerScoreText(), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        inputPanel.add(scoreLabel);

        JPanel buttonPanel = getButtonPanel(frame);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel getButtonPanel(JFrame frame) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Arial", Font.BOLD, 30));
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (savePlayerData()) {
                    System.exit(0);
                }
            }
        });
        buttonPanel.add(quitButton);

        JButton retryButton = getRetryButton(frame);
        buttonPanel.add(retryButton);
        return buttonPanel;
    }

    private JButton getRetryButton(JFrame frame) {
        JButton retryButton = new JButton("Retry");
        retryButton.setFont(new Font("Arial", Font.BOLD, 30));
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (savePlayerData()) {
                    frame.getContentPane().removeAll();
                    MenuScreen menuScreen = new MenuScreen(frame);
                    frame.add(menuScreen);
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });
        return retryButton;
    }

    private boolean savePlayerData() {
        String playerName = nameField.getText();
        if (playerName.length() == 4) {
            Player player = new Player(playerName, playerScore);
            PlayerData playerData = new PlayerData();
            playerData.savePlayerData(player);
        } else {
            JOptionPane.showMessageDialog(this, "Name must be 4 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private String getPlayerScoreText() {
        return "Score: " + playerScore;
    }

    public String getPlayerName() {
        return nameField.getText();
    }
}