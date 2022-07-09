package game;

import javax.swing.*;
import java.awt.*;

public class GameBuilder {

    public GamePanel mainGamePanel;
    public static JFrame gameFrame;
    public JFrame introFrame;


    public GameBuilder()
    {
        this.gameFrame = new JFrame("Duck Hunt");
        this.introFrame =  new JFrame("Duck Hunt");
        this.mainGamePanel = new GamePanel(gameFrame);
        buildIntro();

    }

    public  void buildIntro() {
        mainGamePanel.introSetup();
        introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        introFrame.setPreferredSize(new Dimension(600, 550));
        JPanel content = (JPanel) introFrame.getContentPane();
        content.setBackground(Color.black);
        content.setLayout(new FlowLayout());

        final MainComponent picture = new MainComponent();
        picture.setOpaque(true);
        picture.setPreferredSize(new Dimension(500, 500));
        String introscreen = "intro";
        picture.draw(introscreen);

        content.add(picture);
        content.add(mainGamePanel.getPanel());
        introFrame.pack();
        introFrame.setVisible(true);

    }}
