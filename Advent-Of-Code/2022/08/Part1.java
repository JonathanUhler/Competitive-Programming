import java.util.*;


public class Part1 {

	private static boolean visible(int[][] forest, int r, int c) {
		int size = forest.length;
		if (r == 0 || r == size - 1 || c == 0 || c == size - 1)
			return true;

		boolean visible = false;
		for (int dir = -2; dir < 2; dir++) {
			boolean visibleInDir = true;
			int height = forest[r][c];
			int checkr = r;
			int checkc = c;
			int deltar = dir % 2;       // none, -1,   none, 1
			int deltac = (dir + 1) % 2; // -1,   none, 1,    none
			checkr += deltar;
			checkc += deltac;

			while (checkr >= 0 && checkr < size && checkc >= 0 && checkc < size) {
				int currentHeight = forest[checkr][checkc];
				if (currentHeight >= height) {
					visibleInDir = false;
					break;
				}
				checkr += deltar;
				checkc += deltac;
			}

			visible |= visibleInDir;
		}
		return visible;
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

		int numVisible = 0;
		for (r = 0; r < forest.length; r++) {
			for (int c = 0; c < forest[r].length; c++) {
				if (visible(forest, r, c))
					numVisible++;
			}
		}
		System.out.println("ANSWER: " + numVisible);
	}

}
