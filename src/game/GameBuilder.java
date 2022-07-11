package game;

import javax.swing.*;
import java.awt.*;

public class GameBuilder {

    public GameController mainGameController;
    public static JFrame gameFrame;
    public TitleFrame titleFrame;


    public GameBuilder()
    {
        this.gameFrame = new JFrame("Duck Hunt");
        this.titleFrame =  new TitleFrame();
        this.mainGameController = new GameController(titleFrame,gameFrame);
        setup();

    }

    public  void setup() {
        mainGameController.introSetup();

    }}
