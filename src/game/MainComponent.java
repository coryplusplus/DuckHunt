package game;

import javax.swing.*;
import java.awt.*;

class MainComponent extends JComponent {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String Image;

	public void draw(String image) {
		Image = image;
		repaint();
	}

	public void paintComponent(Graphics g) {
		if (Image.equals("intro")) {
			ImageIcon temp = new ImageIcon("etc/images/" + Image + ".jpg");
			g.drawImage(temp.getImage(), 0, 0, 430, 547, this);
		}
		if (Image.equals("gamescreen")) {
			ImageIcon temp = new ImageIcon("etc/images/" + Image + ".PNG");
			g.drawImage(temp.getImage(), 0, 0, 600, 550, this);
		}

	}
}
