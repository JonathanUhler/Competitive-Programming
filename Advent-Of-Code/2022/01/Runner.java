/**
 * https://adventofcode.com/2022/day/1
 */


import java.util.*;


public class Runner {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		List<Integer> calories = new ArrayList<>();
		int index = 0;
		while (s.hasNextLine()) {
			String calStr = s.nextLine();
			if (calStr.equals("[END]"))
				break;
			if (calStr.equals(""))
				index++;
			else {
				if (calories.size() <= index)
					calories.add(0);
				calories.set(index, calories.get(index) + Integer.valueOf(calStr));
			}
		}

		Collections.sort(calories);
		Collections.reverse(calories);

		int answer = 0;
		for (int i = 0; i < 3; i++)
			answer += calories.get(i);

		System.out.println("ANSWER: " + answer);
	}

}
