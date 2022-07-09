package game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class DuckThread implements Runnable {
	// AudioClip shot = getAudioClip(getCodeBase(), "bounce.au");
	public static double xwhenhit;
	public static double ywhenhit;
	public static double xalive;
	public static double yalive;
	public static Point p;

	// setBackground (Color.blue);

	// Laden des Audioclips

	public boolean contains(Rectangle r, Point p) {
		return (r.getX() < p.getX() && r.getY() < p.getY()
				&& r.getX() + r.getWidth() > p.getX() && r.getY()
				+ r.getHeight() > p.getY());
	}

	public DuckThread(Duck aDuck, Component aComponent) {
		duck = aDuck;
		component = aComponent;
		component.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				if (Stats.NumberShots < 3 && duck.getIsHit() != 1) {
					p = e.getPoint();
					Stats.NumberShots++;
					xalive = duck.getX();
					yalive = duck.getY();
					Point duckPoint = new Point(duck.getX() - 10, duck.getY());
					Dimension duckDimension = new Dimension(75, 75);
					Rectangle duckHitBox = new Rectangle(duckPoint,
							duckDimension);
					if (contains(duckHitBox, p)) {
						Stats.NumberShots = 0;
						// shot.play();
						xwhenhit = p.getX();
						ywhenhit = p.getY();
						Stats.difficulty += .05;
						Stats.duckcount++;
						Stats.duckskilled++;
						duck.setHit(1);
					}
				}

			}

			public void mouseClicked(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});
	}

	public void run() {
		try {
			/* /while(duck.getIsHit()!=1){/ */for (;;) {
				// If statement for dog to appear
				if (duck.getIsHit() == 1 && Stats.DOG > 0) {
					component.repaint();
					Thread.sleep(10);
				}
				/**
				 * If statement for duck to fly away after you shoot all your
				 * bullets
				 */
				if (Stats.NumberShots == Stats.chancestoshoot && Stats.DOG == 0) {// &&duck.getIsHit()!=1){
					System.out.println("YAY");
					duck.setDX(0);
					duck.setDY(0);
					duck.setY(DuckThread.yalive - count);
					duck.setX(DuckThread.xalive);
					count += 10;
					component.repaint();
					Thread.sleep(DELAYALIVE + 35);
				}
				// After duck is shot he falls to the ground
				if (duck.getIsHit() == 1
						&& Stats.NumberShots < Stats.chancestoshoot
						&& Stats.DOG == 0) {
					duck.setDX(0);
					duck.setDY(0);
					duck.setX(DuckThread.xwhenhit);
					duck.setY(DuckThread.ywhenhit + count);
					duck.move(component.getWidth(), component.getHeight());
					count += 10;
					component.repaint();
					Thread.sleep(DELAYDEAD);
				}
				// Duck flying across screen code
				if (duck.getIsHit() == 0
						&& Stats.NumberShots < Stats.chancestoshoot
						&& Stats.DOG == 0) {
					duck.move(component.getWidth(), component.getHeight());
					component.repaint();
					Thread.sleep(DELAYALIVE);
				}
			}
		} catch (InterruptedException e) {
		}
	}

	private Duck duck;
	public static int count = 0;
	public static Component component;
	public static final int DELAYALIVE = 5;
	public static final int DELAYDEAD = 60;

	public int getWidth() {
		return component.getWidth();
	}

	public int getHeight() {
		return component.getHeight();
	}
}
