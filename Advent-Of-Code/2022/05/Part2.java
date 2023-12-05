import java.util.*;


public class Part2 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		// Build structure
		LinkedList<Character>[] stacks = null;
		while (s.hasNextLine()) {
			String str = s.nextLine();
			// Exit building phase
			if (str.equals(""))
				break;

			// Init structure if needed
			if (stacks == null) {
				int rows = (str.length() + 1) / 4; // "[N] " for each box, +1 for trailing space
				stacks = new LinkedList[rows];
				for (int i = 0; i < rows; i++)
					stacks[i] = new LinkedList<>();
			}

			// Build phase
			for (int i = 1; i < str.length(); i += 4) { // Get each letter in the box
				int col = (i - 1) / 4;
				char c = str.charAt(i);
				if (!Character.isAlphabetic(c))
					continue;
				// Structure is built top-down, so put the box last in the linked list
				stacks[col].addLast(c);
			}
		}

		// Make moves
		while (s.hasNextLine()) {
			String str = s.nextLine();
			int n = Integer.parseInt(str.split(" ")[1]);
			int start = Integer.parseInt(str.split(" ")[3]) - 1;
			int end = Integer.parseInt(str.split(" ")[5]) - 1;

			for (int i = 0; i < n; i++) {
				char c = stacks[start].pop();
				stacks[end].add(i, c);
			}
		}

		// Return answer
		String answer = "";
		for (LinkedList<Character> stack : stacks)
			answer += stack.pop();
		System.out.println("ANSWER: " + answer);
	}

}
