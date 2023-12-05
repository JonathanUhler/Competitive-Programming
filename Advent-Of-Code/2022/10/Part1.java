import java.util.*;
import java.io.File;


public class Part1 {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));
		int cycle = 0;
		int check = 20;
		int x = 1;
		int signal = 0;
		while (s.hasNextLine()) {
			String cmd = s.nextLine();
			if (cmd.startsWith("noop"))
				cycle++;
			else {
				cycle += 2;
				if (cycle >= check) {
					signal += x * check;
					check += 40;
				}
				int dx = Integer.parseInt(cmd.split(" ")[1]);
				x += dx;
			}
		}
		System.out.println("ANSWER: " + signal);
	}

}
