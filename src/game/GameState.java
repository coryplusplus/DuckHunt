package game;

class GameState {
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
