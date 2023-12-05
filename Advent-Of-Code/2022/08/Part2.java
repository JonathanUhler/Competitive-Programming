import java.util.*;


public class Part2 {

	private static int scenicScore(int[][] forest, int r, int c) {
		int size = forest.length;
		int score = 1;
		for (int dir = -2; dir < 2; dir++) {
			int scoreForDir = 0;
			int height = forest[r][c];
			int checkr = r;
			int checkc = c;
			int deltar = dir % 2;       // none, -1,   none, 1
			int deltac = (dir + 1) % 2; // -1,   none, 1,    none
			checkr += deltar;
			checkc += deltac;

			while (checkr >= 0 && checkr < size && checkc >= 0 && checkc < size) {
				int currentHeight = forest[checkr][checkc];
				scoreForDir++;
				if (currentHeight >= height)
					break;
				checkr += deltar;
				checkc += deltac;
			}
			score *= scoreForDir;
		}
		return score;
	}
	

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int[][] forest = null;
		int r = 0;
		while (s.hasNextLine()) {
			String row = s.nextLine();
			if (forest == null) {
				int size = row.length();
				forest = new int[size][size];
			}

			for (int c = 0; c < forest.length; c++)
				forest[r][c] = Character.getNumericValue(row.charAt(c));
			r++;
		}

		int maxScore = 0;
		for (r = 0; r < forest.length; r++) {
			for (int c = 0; c < forest[r].length; c++) {
				int currentScore = scenicScore(forest, r, c);
				if (currentScore > maxScore)
					maxScore = currentScore;
			}
		}
		System.out.println("ANSWER: " + maxScore);
	}

}
