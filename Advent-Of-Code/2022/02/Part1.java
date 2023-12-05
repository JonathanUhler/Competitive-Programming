import java.util.*;


public class Part1 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int points = 0;
		while (s.hasNextLine()) {
			String str = s.nextLine();
			char oppChar = str.split(" ")[0].charAt(0);
			int opp = (int) oppChar - 65;
			char resChar = str.split(" ")[1].charAt(0);
			int res = (int) resChar - 88;
			
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
