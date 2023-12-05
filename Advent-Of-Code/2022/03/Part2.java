import java.util.*;


public class Part2 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int sumPriority = 0;
		while (s.hasNextLine()) {
			String sack1 = s.nextLine();
			String sack2 = s.nextLine();
			String sack3 = s.nextLine();
			Set<Character> set1 = new HashSet<>();
			Set<Character> set2 = new HashSet<>();
			Set<Character> set3 = new HashSet<>();
			for (char c : sack1.toCharArray())
				set1.add(c);
			for (char c : sack2.toCharArray())
				set2.add(c);
			for (char c : sack3.toCharArray())
				set3.add(c);

			set1.retainAll(set2);
			set1.retainAll(set3);
			char badge = set1.iterator().next();

			int priority = (int) badge;
			if (priority >= 97)
				priority -= 96;
			else
				priority += (-64 + 26);

			sumPriority += priority;
		}

		System.out.println("ANSWER: " + sumPriority);
	}

}
