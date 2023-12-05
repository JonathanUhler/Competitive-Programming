import java.util.*;
import java.io.File;


public class Part1 {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));
		int ans = 0;
		while (s.hasNextLine()) {
			String line = s.nextLine();
			line = line.replaceAll("[a-z]+", "");
			char first = line.charAt(0);
			char last = line.charAt(line.length() - 1);
			ans += Integer.parseInt(first + "" + last);
		}
		System.out.println("ANSWER: " + ans);
	}

}
