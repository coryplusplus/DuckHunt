package game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class DuckHunt {
	public static int StartGame = 0;
	public static JFrame game = new JFrame("Duck Hunt");
	public static JPanel gamecontent = (JPanel) game.getContentPane();
	public static JTextField username = new JTextField("USERNAME");
	public static ImageIcon duck1 = new ImageIcon(Constants.DUCK1);
	public static ImageIcon duck2 = new ImageIcon(Constants.DUCK2);
	public static JPanel introScreen = new JPanel();
	public static JButton Start = new JButton("PLAY");
	public static JButton Exit = new JButton("EXIT");
	public static JFrame introFrame = new JFrame("Duck Hunt");

	private static void setupStartListener() {
		Start.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent evt) {
				introFrame.dispose();
				game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				game.setPreferredSize(new Dimension(800, 750));
				game.setResizable(false);
				final MyPanel panel = new MyPanel();
				panel.setPreferredSize(new Dimension(DEFAULT_WIDTH,
						DEFAULT_HEIGHT));
				game.add(panel);// , BorderLayout.CENTER);
				Duck d = new Duck();
				panel.add(d);
				Runnable r = new DuckThread(d, game);
				Thread t = new Thread(r);
				t.start();
				game.setCursor(Cursor.CROSSHAIR_CURSOR);
				game.pack();
				game.setVisible(true);
			}
		});
	}

	private static void setupExitListener() {
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				introFrame.dispose();
			}
		});
	}

	private static void setupIntroListeners() {
		setupStartListener();
		setupExitListener();
	}

	private static void introSetup() {
		Start.setForeground(Color.black);
		Exit.setForeground(Color.black);
		introScreen.setLayout(new GridLayout(3, 1));
		introScreen.add(username);
		introScreen.add(Start);
		introScreen.add(Exit);
		setupIntroListeners();
	}

	private static void buildIntro() {
		introSetup();
		introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		introFrame.setPreferredSize(new Dimension(600, 550));
		JPanel content = (JPanel) introFrame.getContentPane();
		content.setBackground(Color.black);
		content.setLayout(new FlowLayout());

		final MyComponent picture = new MyComponent();
		picture.setOpaque(true);
		picture.setPreferredSize(new Dimension(500, 500));
		String introscreen = "intro";
		picture.draw(introscreen);

		content.add(picture);
		content.add(introScreen);
		introFrame.pack();
		introFrame.setVisible(true);

	}

	public static void main(String[] args) {
		// AudioClip au;
		// URL codeBase = new URL
		// ("http://vgmusic.com//music//console/nintendo/nes/Duck_Hunt.mid");
		// au = getAudioClip(codeBase);
		buildIntro();

	}

	public static final int DEFAULT_WIDTH = 1;
	public static final int DEFAULT_HEIGHT = 1;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;
}

class MyComponent extends JComponent {
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

class Duck {
	public Duck() {
		Random generator = new Random();
		new Color(generator.nextInt(255), generator.nextInt(255),
				generator.nextInt(255));
		;
		XSIZE = 85;
		YSIZE = 85;
		x = generator.nextInt(600);
		y = generator.nextInt(500);
	}

	public void move(int width, int height) {
		x += dx;
		y += dy;
		if (x < 0) {
			x = 0;
			dx = -dx;
		}
		if (x + XSIZE >= width) {
			x = width - XSIZE;
			dx = -dx;
		}
		if (y < 0) {
			y = 0;
			dy = -dy;
		}
		if (y + YSIZE >= height - 200) {
			y = height - 200 - YSIZE;
			dy = -dy;
		}
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public void setX(double z) {
		x = z;
	}

	public void setY(double z) {
		y = z;
	}

	public int getXSIZE() {
		return XSIZE;
	}

	public int getYSIZE() {
		return YSIZE;
	}

	public int getIsHit() {
		return IsHit;
	}

	public void setHit(int x) {
		IsHit = x;
	}

	public int getDX() {
		return (int) dx;
	}

	public int getDY() {
		return (int) dy;
	}

	public void setDX(double x) {
		dx = x;
	}

	public void setDY(double x) {
		dy = x;
	}

	private int XSIZE;
	private int YSIZE;
	// determines if the duck has been hit or not
	private int IsHit;

	private double x = 0;
	private double y = 0;
	private double dx = 1;
	private double dy = 1;
}

class MyPanel extends JPanel {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	public static HashMap<Integer, String> shotMap = new HashMap<Integer, String>();
	public static HashMap<Integer, String> hitMap = new HashMap<Integer, String>();
	public static HashMap<Integer, String> roundMap = new HashMap<Integer, String>();
	public static HashMap<Integer, String> scoreMap = new HashMap<Integer, String>();

	public MyPanel() {
		buildUIMaps();
	}

	public static void buildUIMaps() {
		shotMap.put(0, Constants.SHOTS_3);
		shotMap.put(1, Constants.SHOTS_2);
		shotMap.put(2, Constants.SHOTS_1);
		shotMap.put(3, Constants.SHOTS_0);

		hitMap.put(0, Constants.HIT_0);
		hitMap.put(1, Constants.HIT_1);
		hitMap.put(2, Constants.HIT_2);
		hitMap.put(3, Constants.HIT_3);
		hitMap.put(4, Constants.HIT_4);
		hitMap.put(5, Constants.HIT_5);
		hitMap.put(6, Constants.HIT_6);
		hitMap.put(7, Constants.HIT_7);
		hitMap.put(8, Constants.HIT_8);
		hitMap.put(9, Constants.HIT_9);
		hitMap.put(10, Constants.HIT_10);

		roundMap.put(1, Constants.ROUND_1);
		roundMap.put(2, Constants.ROUND_2);
		roundMap.put(3, Constants.ROUND_3);

		scoreMap.put(0, Constants.SCORE_0);
		scoreMap.put(1, Constants.SCORE_10);
		scoreMap.put(2, Constants.SCORE_20);
		scoreMap.put(3, Constants.SCORE_30);
		scoreMap.put(4, Constants.SCORE_40);
		scoreMap.put(5, Constants.SCORE_50);
		scoreMap.put(6, Constants.SCORE_60);
		scoreMap.put(7, Constants.SCORE_70);
		scoreMap.put(8, Constants.SCORE_80);
		scoreMap.put(9, Constants.SCORE_90);
		scoreMap.put(10, Constants.SCORE_100);
		scoreMap.put(11, Constants.SCORE_200);
		scoreMap.put(12, Constants.SCORE_300);
		scoreMap.put(13, Constants.SCORE_400);
		scoreMap.put(14, Constants.SCORE_500);
		scoreMap.put(15, Constants.SCORE_600);
		scoreMap.put(16, Constants.SCORE_700);
		scoreMap.put(17, Constants.SCORE_800);
		scoreMap.put(18, Constants.SCORE_900);
		scoreMap.put(19, Constants.SCORE_1000);
		scoreMap.put(20, Constants.SCORE_2000);
		scoreMap.put(21, Constants.SCORE_3000);
		scoreMap.put(22, Constants.SCORE_4000);
		scoreMap.put(23, Constants.SCORE_5000);
		scoreMap.put(24, Constants.SCORE_6000);
		scoreMap.put(25, Constants.SCORE_7000);
		scoreMap.put(26, Constants.SCORE_8000);
		scoreMap.put(27, Constants.SCORE_9000);
		scoreMap.put(28, Constants.SCORE_10000);

	}

	public void add(Duck b) {
		ducks.add(b);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon screen = new ImageIcon();
		if (Stats.NumberShots == Stats.chancestoshoot && Stats.DOG == 0)
			screen = new ImageIcon("etc/images/gamescreenpink.PNG");
		else
			screen = new ImageIcon("etc/images/gamescreen.PNG");
		g.drawImage(screen.getImage(), 0, 0, 800, 750, this);

		if (Stats.showIntro) {
			ImageIcon dog = new ImageIcon();
			dog = new ImageIcon(Constants.dogWalking);
			g.drawImage(dog.getImage(), Stats.DOGX, 450, 200, 200, this);
			Stats.DOGX += 1;
			if (Stats.DOGX > 300) {
				Stats.DOGX = 0;
				Stats.showIntro = false;
			}

		} else {
			for (Duck b : ducks) {
				Random generator = new Random();
				ImageIcon bullets = new ImageIcon();
				ImageIcon duckcount = new ImageIcon();
				ImageIcon round = new ImageIcon();
				ImageIcon score = new ImageIcon();

				bullets = new ImageIcon(shotMap.get(Stats.NumberShots));
				duckcount = new ImageIcon(hitMap.get(Stats.duckcount));
				round = new ImageIcon(roundMap.get(Stats.round));
				score = new ImageIcon(scoreMap.get(Stats.duckskilled));
				Stats.score = Stats.duckskilled * 10;

				if (Stats.NumberShots == 3) {
					if (Stats.rotate == 0)
						bullets = new ImageIcon(Constants.SHOTS_0);
					else {
						bullets = new ImageIcon("noshot.jpg");
					}
					Stats.setrotate();
				}
				if (Stats.duckcount == 10) {
					duckcount = new ImageIcon(Constants.HIT_10);
					Stats.duckcount = 0;
					Stats.round++;
					Stats.showIntro = true;
					if (Stats.round > 3) {
						DuckHunt.game.dispose();
						HighScore.FileScore();
					}
				}

				g.drawImage(bullets.getImage(), 20, 625, 90, 70, this);
				g.drawImage(round.getImage(), 30, 600, 50, 23, this);
				g.drawImage(duckcount.getImage(), 280, 630, 224, 54, this);
				g.drawImage(score.getImage(), 600, 630, 104, 54, this);
				ImageIcon duck = new ImageIcon("etc/images/duckflying.GIF");
				// prints the dog to the screen
				if (b.getIsHit() == 1 && Stats.DOG > 0) {
					ImageIcon kill = new ImageIcon("etc/images/kill1.gif");
					g.drawImage(kill.getImage(), 360, 400, 100, 100, this);
					// integer in the if statement determines how long dog
					// appears
					// for
					if (Stats.DOG < 175) {
						Stats.DOG++; 
					} else {
						b.setHit(0);
						Stats.DOG = 0;
						b.setDX(Stats.difficulty);
						b.setDY(Stats.difficulty);
						b.setX(generator.nextInt(400));
						b.setY(generator.nextInt(5));
						DuckThread.count = 0;
					}
				}
				// prints the duck flying across the screen
				if (b.getIsHit() != 1
						&& Stats.NumberShots < Stats.chancestoshoot
						&& Stats.DOG == 0) {
					g.drawImage(duck.getImage(), b.getX(), b.getY(),
							b.getXSIZE(), b.getYSIZE(), this);
				}
				// prints the duck falling to the ground
				if (b.getIsHit() == 1
						&& Stats.NumberShots < Stats.chancestoshoot
						&& Stats.DOG == 0) {
					if (b.getY() < 450) {
						ImageIcon falling = new ImageIcon(
								"etc/images/duckfall.GIF");
						System.out.println(Stats.DOG);
						g.drawImage(falling.getImage(), b.getX() - 10,
								b.getY() - 10, b.getXSIZE(), b.getYSIZE(), this);
					} else {
						Stats.DOG++;
					}
				}
				// prints the duck flying away because out of bullets.
				if (Stats.NumberShots == Stats.chancestoshoot && Stats.DOG == 0) {// &&b.getIsHit()!=1){
					if (b.getY() > 0) {
						{
							ImageIcon flyaway = new ImageIcon(
									"etc/images/backofduck.GIF");
							// ImageIcon flyawaytext = new
							// ImageIcon("FLYAWAY.JPG");
							// g.drawImage(flyawaytext.getImage(),350,250,150,75,this);
							g.drawImage(flyaway.getImage(), b.getX(), b.getY(),
									b.getXSIZE(), b.getYSIZE(), this);
						}
					} else {
						Stats.NumberShots = 0;
						b.setDX(Stats.difficulty);
						b.setDY(Stats.difficulty);
						b.setX(generator.nextInt(400));
						b.setY(generator.nextInt(5));
						DuckThread.count = 0;
						Stats.duckcount++;
						Stats.difficulty += .05;

					}

				}

			}
		}
	}

	private ArrayList<Duck> ducks = new ArrayList<Duck>();
}

class Stats {
	public static int score = 0;
	public static int NumberShots = 0;
	public static int chancestoshoot = 3;
	public static int DOG = 0;
	public static int DOGX = 0;
	public static double difficulty = 1;
	public static int duckcount = 0;
	public static int duckskilled = 0;
	public static int round = 1;
	public static int rotate = 0;
	public static boolean showIntro = true;

	public static void setrotate() {
		if (rotate == 0)
			rotate = 1;
		if (rotate == 1)
			rotate = 0;
	}
}

class HighScore {
	@SuppressWarnings("deprecation")
	public static void FileScore() {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Vector<String> lines = new Vector();
		FileOutputStream fout;

		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream("High_Scores.txt");

			// Convert our input stream to a
			// DataInputStream
			DataInputStream in = new DataInputStream(fstream);

			// Continue to read lines while
			// there are still some left to read
			while (in.available() != 0) {
				// Print file line to screen
				lines.add(in.readLine());
				// System.out.println (in.readLine());
			}

			in.close();
		} catch (Exception e) {
			System.err.println("File input error");
		}

		try {
			// Open an output stream
			fout = new FileOutputStream("High_Scores.txt");

			// Print a line of text
			new PrintStream(fout).println(DuckHunt.username.getText() + " : "
					+ Stats.score);
			for (int z = 0; z < lines.size(); z++) {
				new PrintStream(fout).println(lines.elementAt(z));
			}

			// Close our output stream
			fout.close();
		}
		// Catches any error conditions
		catch (IOException e) {
			System.err.println("Unable to write to file");
			System.exit(-1);
		}
	}

}
