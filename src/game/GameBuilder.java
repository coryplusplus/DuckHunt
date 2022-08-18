package game;

import javax.swing.*;
import java.awt.*;

public class GameBuilder {

    public GameController mainGameController;
    public GameFrame gameFrame;
    public TitleFrame titleFrame;


    public GameBuilder(String gameTitle)
    {
        this.gameFrame = new GameFrame(gameTitle);
        this.titleFrame =  new TitleFrame(gameTitle);
        this.mainGameController = new GameController(titleFrame,gameFrame);
        setup();

    }

    public  void setup() {
        mainGameController.introSetup();

    }}
