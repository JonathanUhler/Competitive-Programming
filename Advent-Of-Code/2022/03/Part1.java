import java.util.*;


public class Part1 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int sumPriority = 0;
		while (s.hasNextLine()) {
			String sack = s.nextLine();
			Set<Character> comp1 = new HashSet<>();
			Set<Character> comp2 = new HashSet<>();
			for (int i = 0; i < sack.length(); i++) {
				char c = sack.charAt(i);
				if (i < sack.length() / 2)
					comp1.add(c);
				else
					comp2.add(c);
			}

			comp1.retainAll(comp2);
			char duplicate = comp1.iterator().next();

			int priority = (int) duplicate;
			if (priority >= 97)
				priority -= 96;
			else
				priority += (-64 + 26);

			sumPriority += priority;
		}

		System.out.println("ANSWER: " + sumPriority);
	}

}
