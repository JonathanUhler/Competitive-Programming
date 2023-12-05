import java.util.*;
import java.io.File;


public class Part2 {

	private static void print(int x, int crt) {
		if (crt == 0)
			System.out.println();
		if (Math.abs(crt - x) <= 1)
			System.out.print("#");
		else
			System.out.print(".");
	}
	

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));
		int cycle = 0;
		int width = 40;
		int x = 1;
		while (s.hasNextLine()) {
			String cmd = s.nextLine();
			print(x, cycle % width);
			cycle++;
			if (cmd.startsWith("addx")) {
				print(x, cycle % width);
				cycle++;
				int dx = Integer.parseInt(cmd.split(" ")[1]);
				x += dx;
			}
		}
	}

}
