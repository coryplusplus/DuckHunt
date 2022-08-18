package game;

import javax.swing.*;
import java.awt.*;

class TitleFrame extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public TitleButtonPanel buttonPanel = new TitleButtonPanel();

    public TitleButtonPanel getButtonPanel()
    {
        return buttonPanel;
    }

    public TitleFrame(String gameTitle)
    {
        this.setTitle(gameTitle);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 550));

        JPanel content = (JPanel) getContentPane();
        content.setBackground(Color.black);
        content.setLayout(new FlowLayout());


        final ImageComponent titleScreenImage = new ImageComponent();
        titleScreenImage.setOpaque(true);
        titleScreenImage.setPreferredSize(new Dimension(500, 500));
        titleScreenImage.setImageName("intro.jpg");
        titleScreenImage.draw();

        content.add(titleScreenImage);
        content.add(getButtonPanel());
        pack();
        setVisible(true);



    }
    public void draw() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        ImageIcon temp = new ImageIcon("etc/images/intro.jpg");
        g.drawImage(temp.getImage(), 0, 0, 430, 547, this);


    }
}
