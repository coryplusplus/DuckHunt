package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel {
    public JPanel gamePanel = new JPanel();
    public JButton start = new JButton("PLAY");
    public JButton exit = new JButton("EXIT");
    public JFrame introFrame = new JFrame("Duck Hunt");
    public JTextField userName = new JTextField("USERNAME");
    public JFrame gameFrame;
    public static final int DEFAULT_WIDTH = 1;
    public static final int DEFAULT_HEIGHT = 1;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;


    public JPanel getPanel()
    {
        return gamePanel;
    }
    public GamePanel(JFrame gameFrame)
    {
        this.gameFrame = gameFrame;
    }


    public  void introSetup() {
        start.setForeground(Color.black);
        exit.setForeground(Color.black);
        gamePanel.setLayout(new GridLayout(3, 1));
        gamePanel.add(userName);
        gamePanel.add(start);
        gamePanel.add(exit);
        setupIntroListeners();
    }

    public void setupIntroListeners() {
        setupStartListener(this);
        setupExitListener(this);
    }

    private static void setupStartListener(GamePanel gamePanel) {
        gamePanel.start.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent evt) {
                gamePanel.introFrame.dispose();
                gamePanel.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gamePanel.gameFrame.setPreferredSize(new Dimension(800, 750));
                gamePanel.gameFrame.setResizable(false);
                final MainPanel panel = new MainPanel(gamePanel.gameFrame,gamePanel.userName.getText());
                panel.setPreferredSize(new Dimension(DEFAULT_WIDTH,
                        DEFAULT_HEIGHT));
                gamePanel.gameFrame.add(panel);// , BorderLayout.CENTER);
                Duck d = new Duck();
                panel.add(d);
                Runnable r = new DuckThread(d, gamePanel.gameFrame);
                Thread t = new Thread(r);
                t.start();
                gamePanel.gameFrame.setCursor(Cursor.CROSSHAIR_CURSOR);
                gamePanel.gameFrame.pack();
                gamePanel.gameFrame.setVisible(true);
            }
        });
    }

    private static void setupExitListener(GamePanel gamePanel) {
        gamePanel.exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                gamePanel.introFrame.dispose();
            }
        });
    }

}
