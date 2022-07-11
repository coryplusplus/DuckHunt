package game;

import javax.swing.*;
import java.awt.*;

public class TitleButtonPanel extends JPanel {

    public JButton start = new JButton("PLAY");
    public JButton exit = new JButton("EXIT");
    public JTextField userName = new JTextField("USERNAME");


    public TitleButtonPanel()
    {
        start.setForeground(Color.black);
        exit.setForeground(Color.black);
        setLayout(new GridLayout(3, 1));
        add(userName);
        add(start);
        add(exit);

    }
}
