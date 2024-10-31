import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ball Game");
        GameScreen game = new GameScreen();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

<<<<<<< HEAD
=======
    private static void timerRefresh(PongGame game) {
        int DELAY = 33;

        Timer timerRefresh = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                game.gameLogic();

                game.repaint();

            }
        });

        timerRefresh.start();
    }
}
>>>>>>> 0f7507fd06bc90d36da7414f760b9b57d350e2ff
