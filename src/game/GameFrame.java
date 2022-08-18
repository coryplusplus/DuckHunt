package game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame(String title)
    {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 750));
        this.setResizable(false);

    }

    public void showPanel()
    {
        this.setCursor(Cursor.CROSSHAIR_CURSOR);
        this.pack();
        this.setVisible(true);

    }
}
