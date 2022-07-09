package game;

import java.io.*;
import java.util.Vector;

class HighScore {
	@SuppressWarnings("deprecation")
	public static void FileScore(String username) {
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
			new PrintStream(fout).println(username + " : "
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
