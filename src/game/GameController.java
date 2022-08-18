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
    public GameFrame gameFrame;

    public static final int DEFAULT_WIDTH = 1;
    public static final int DEFAULT_HEIGHT = 1;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;


    public GameController(TitleFrame titleFrame, GameFrame gameFrame)
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
                final GamePanelStepFunction stepFunctionPanel = new GamePanelStepFunction(gameController.gameFrame, gameController.titleFrame.getButtonPanel().userName.getText());
                stepFunctionPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH,
                        DEFAULT_HEIGHT));
                gameController.gameFrame.add(stepFunctionPanel);// , BorderLayout.CENTER);
                Duck d = new Duck();
                stepFunctionPanel.add(d);
                Runnable r = new DuckThread(d, gameController.gameFrame);
                Thread t = new Thread(r);
                t.start();

                gameController.gameFrame.showPanel();
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
