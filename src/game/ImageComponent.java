package game;

import javax.swing.*;
import java.awt.*;

class ImageComponent extends JComponent {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String image_name;
    private int width = 430;
    private int height = 547;

    public void draw() {
        repaint();
    }

    public void setDimensions(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public void setImageName(String imageName) {
        this.image_name = "etc/images/" + imageName;
    }

    public void paintComponent(Graphics g) {

        ImageIcon temp = new ImageIcon(image_name);
        g.drawImage(temp.getImage(), 0, 0, this.width, this.height, this);


    }
}