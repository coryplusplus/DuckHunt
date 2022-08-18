package game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class GamePanelStepFunction extends JPanel {
	/**
		 *
		 */
	private static final long serialVersionUID = 1L;
	public static HashMap<Integer, String> shotMap = new HashMap<Integer, String>();
	public static HashMap<Integer, String> hitMap = new HashMap<Integer, String>();
	public static HashMap<Integer, String> roundMap = new HashMap<Integer, String>();
	public static HashMap<Integer, String> scoreMap = new HashMap<Integer, String>();
	public JFrame game;
	public String username;

	public GamePanelStepFunction(JFrame gameFrame, String username) {
		this.game = gameFrame;
		this.username = username;
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

	public void add(Duck duck)
	{
		ducks.add(duck);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon screen = new ImageIcon();
		if (GameState.NumberShots == GameState.chancestoshoot && GameState.DOG == 0)
			screen = new ImageIcon("etc/images/gamescreenpink.PNG");
		else
			screen = new ImageIcon("etc/images/gamescreen.PNG");
		g.drawImage(screen.getImage(), 0, 0, 800, 750, this);

		if (GameState.showIntro) {
			ImageIcon dog = new ImageIcon();
			dog = new ImageIcon(Constants.dogWalking);
			g.drawImage(dog.getImage(), GameState.DOGX, 450, 200, 200, this);
			GameState.DOGX += 1;
			if (GameState.DOGX > 300) {
				GameState.DOGX = 0;
				GameState.showIntro = false;
			}

		} else {
			for (Duck b : ducks) {
				Random generator = new Random();
				ImageIcon bullets = new ImageIcon();
				ImageIcon duckcount = new ImageIcon();
				ImageIcon round = new ImageIcon();
				ImageIcon score = new ImageIcon();

				bullets = new ImageIcon(shotMap.get(GameState.NumberShots));
				duckcount = new ImageIcon(hitMap.get(GameState.duckcount));
				round = new ImageIcon(roundMap.get(GameState.round));
				score = new ImageIcon(scoreMap.get(GameState.duckskilled));
				GameState.score = GameState.duckskilled * 10;

				if (GameState.NumberShots == 3) {
					if (GameState.rotate == 0)
						bullets = new ImageIcon(Constants.SHOTS_0);
					else {
						bullets = new ImageIcon("noshot.jpg");
					}
					GameState.setrotate();
				}
				if (GameState.duckcount == 10) {
					duckcount = new ImageIcon(Constants.HIT_10);
					GameState.duckcount = 0;
					GameState.round++;
					GameState.showIntro = true;
					if (GameState.round > 3) {
						this.game.dispose();
						HighScore.FileScore(username);
					}
				}

				g.drawImage(bullets.getImage(), 20, 625, 90, 70, this);
				g.drawImage(round.getImage(), 30, 600, 50, 23, this);
				g.drawImage(duckcount.getImage(), 280, 630, 224, 54, this);
				g.drawImage(score.getImage(), 600, 630, 104, 54, this);
				ImageIcon duck = new ImageIcon("etc/images/duckflying.GIF");
				// prints the dog to the screen
				if (b.getIsHit() == 1 && GameState.DOG > 0) {
					ImageIcon kill = new ImageIcon("etc/images/kill1.gif");
					g.drawImage(kill.getImage(), 360, 400, 100, 100, this);
					// integer in the if statement determines how long dog
					// appears
					// for
					if (GameState.DOG < 175) {
						GameState.DOG++;
					} else {
						b.setHit(0);
						GameState.DOG = 0;
						b.setDX(GameState.difficulty);
						b.setDY(GameState.difficulty);
						b.setX(generator.nextInt(400));
						b.setY(generator.nextInt(5));
						DuckThread.count = 0;
					}
				}
				// prints the duck flying across the screen
				if (b.getIsHit() != 1
						&& GameState.NumberShots < GameState.chancestoshoot
						&& GameState.DOG == 0) {
					g.drawImage(duck.getImage(), b.getX(), b.getY(),
							b.getXSIZE(), b.getYSIZE(), this);
				}
				// prints the duck falling to the ground
				if (b.getIsHit() == 1
						&& GameState.NumberShots < GameState.chancestoshoot
						&& GameState.DOG == 0) {
					if (b.getY() < 450) {
						ImageIcon falling = new ImageIcon(
								"etc/images/duckfall.GIF");
						System.out.println(GameState.DOG);
						g.drawImage(falling.getImage(), b.getX() - 10,
								b.getY() - 10, b.getXSIZE(), b.getYSIZE(), this);
					} else {
						GameState.DOG++;
					}
				}
				// prints the duck flying away because out of bullets.
				if (GameState.NumberShots == GameState.chancestoshoot && GameState.DOG == 0) {// &&b.getIsHit()!=1){
					if (b.getY() > 0) {
						{
							ImageIcon flyaway = new ImageIcon(
									"etc/images/backofduck.GIF");
							g.drawImage(flyaway.getImage(), b.getX(), b.getY(),
									b.getXSIZE(), b.getYSIZE(), this);
						}
					} else {
						GameState.NumberShots = 0;
						b.setDX(GameState.difficulty);
						b.setDY(GameState.difficulty);
						b.setX(generator.nextInt(400));
						b.setY(generator.nextInt(5));
						DuckThread.count = 0;
						GameState.duckcount++;
						GameState.difficulty += .05;

					}

				}

			}
		}
	}

	private ArrayList<Duck> ducks = new ArrayList<Duck>();
}
