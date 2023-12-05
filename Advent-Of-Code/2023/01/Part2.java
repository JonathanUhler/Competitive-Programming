import java.util.*;
import java.io.File;


public class Part2 {

	public static int phraseToInt(String string) {
		if (string.startsWith("zero")) return 0;
		if (string.startsWith("one")) return 1;
		if (string.startsWith("two")) return 2;
		if (string.startsWith("three")) return 3;
		if (string.startsWith("four")) return 4;
		if (string.startsWith("five")) return 5;
		if (string.startsWith("six")) return 6;
		if (string.startsWith("seven")) return 7;
		if (string.startsWith("eight")) return 8;
		if (string.startsWith("nine")) return 9;
		return Integer.MIN_VALUE;
	}
	
	
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));
		int ans = 0;
		while (s.hasNextLine()) {
			String line = s.nextLine();
			LinkedList<Integer> ints = new LinkedList<>();
			for (int c = 0; c < line.length(); c++) {
				String sub = line.substring(c);
				if (Character.isDigit(sub.charAt(0)))
					ints.add(Integer.parseInt(Character.toString(sub.charAt(0))));
				else if (phraseToInt(sub) >= 0)
					ints.add(phraseToInt(sub));
			}
			ans += Integer.parseInt(Integer.toString(ints.getFirst()) +
									Integer.toString(ints.getLast()));
		}
		System.out.println("ANSWER: " + ans);
	}

}
