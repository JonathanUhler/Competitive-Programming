import java.util.*;
import java.io.File;


public class Part1 {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));
		String[] times = s.nextLine().split(":\\s+")[1].split("\\s+");
		String[] dists = s.nextLine().split(":\\s+")[1].split("\\s+");
		int marginOfError = 1;
		for (int i = 0; i < times.length; i++) {
			int time = Integer.parseInt(times[i]);
			int dist = Integer.parseInt(dists[i]);
			int waysToWin = 0;
			for (int hold = 0; hold <= time; hold++) {
				int dv = hold;
				int dt = time - hold;
				int dx = dv * dt;
				if (dx > dist)
					waysToWin++;
			}
			marginOfError *= waysToWin;
		}
		System.out.println("ANSWER: " + marginOfError);
	}

}
