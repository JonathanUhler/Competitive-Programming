import java.util.*;


public class Part2 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int points = 0;
		while (s.hasNextLine()) {
			String str = s.nextLine();
			char oppChar = str.split(" ")[0].charAt(0);
			int opp = (int) oppChar - 65;
			char planChar = str.split(" ")[1].charAt(0);
			int plan = (int) planChar - 88;

			int res = -1;
			switch (plan) {
			case 0 -> res = (opp - 1) % 3; // lose
			case 1 -> res = opp; // draw
			case 2 -> res = (opp + 1) % 3; // win
			}
			if (res < 0)
				res += 3;
			
			int resPts = res + 1;

			int winPts = 0;
			if (opp == res)
				winPts = 3;
			else if (res == (opp + 1) % 3)
				winPts = 6;

			points += (winPts + resPts);
		}

		System.out.println("ANSWER: " + points);
	}

}
