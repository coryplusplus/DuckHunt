package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//This class is a controller which has all components registered. Listeners are setup in this class.


public class GameController {
    //This is the main panel



    //These frames are passed in from the GameBuilder
    public TitleFrame titleFrame;
    public JFrame gameFrame;

    public static final int DEFAULT_WIDTH = 1;
    public static final int DEFAULT_HEIGHT = 1;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;


    public GameController(TitleFrame titleFrame, JFrame gameFrame)
    {
        this.titleFrame = titleFrame;
        this.gameFrame = gameFrame;
    }


    public  void introSetup() {
        setupIntroListeners();
    }

    public void setupIntroListeners() {
        setupStartListener(this);
        setupExitListener(this);
    }


    private static void setupStartListener(GameController gameController) {


        gameController.titleFrame.getButtonPanel().start.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent evt) {
                gameController.titleFrame.dispose();
                gameController.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameController.gameFrame.setPreferredSize(new Dimension(800, 750));
                gameController.gameFrame.setResizable(false);
                final GamePanelStepFunction panel = new GamePanelStepFunction(gameController.gameFrame, gameController.titleFrame.getButtonPanel().userName.getText());
                panel.setPreferredSize(new Dimension(DEFAULT_WIDTH,
                        DEFAULT_HEIGHT));
                gameController.gameFrame.add(panel);// , BorderLayout.CENTER);
                Duck d = new Duck();
                panel.add(d);
                Runnable r = new DuckThread(d, gameController.gameFrame);
                Thread t = new Thread(r);
                t.start();
                gameController.gameFrame.setCursor(Cursor.CROSSHAIR_CURSOR);
                gameController.gameFrame.pack();
                gameController.gameFrame.setVisible(true);
            }
        });
    }

    private static void setupExitListener(GameController gameController) {
        gameController.titleFrame.getButtonPanel().exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                gameController.titleFrame.dispose();
            }
        });
    }

}
