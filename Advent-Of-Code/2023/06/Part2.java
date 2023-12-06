import java.util.*;
import java.io.File;


public class Part2 {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));
		long time = Long.parseLong(s.nextLine().split(":\\s+")[1].replaceAll("\\s+", ""));
		long dist = Long.parseLong(s.nextLine().split(":\\s+")[1].replaceAll("\\s+", ""));
		int waysToWin = 0;
		for (long hold = 0; hold <= time; hold++) {
			long dv = hold;
			long dt = time - hold;
			long dx = dv * dt;
			if (dx > dist)
				waysToWin++;
		}
		System.out.println("ANSWER: " + waysToWin);
	}

}
