import java.util.*;


public class Part2 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int overlaps = 0;
		while (s.hasNextLine()) {
			String str = s.nextLine();
			String p1 = str.split(",")[0];
			String p2 = str.split(",")[1];
			int p1l = Integer.parseInt(p1.split("-")[0]);
			int p1h = Integer.parseInt(p1.split("-")[1]);
			int p2l = Integer.parseInt(p2.split("-")[0]);
			int p2h = Integer.parseInt(p2.split("-")[1]);

			Set<Integer> set1 = new HashSet<>();
			Set<Integer> set2 = new HashSet<>();

			for (int n = p1l; n <= p1h; n++)
				set1.add(n);
			for (int n = p2l; n <= p2h; n++)
				set2.add(n);

			if (!Collections.disjoint(set1, set2))
				overlaps++;
		}

		System.out.println("ANSWER: " + overlaps);
	}

}
