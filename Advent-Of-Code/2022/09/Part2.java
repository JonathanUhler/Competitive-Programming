import java.util.*;
import java.awt.Point;


public class Part2 {

	private static boolean touching(Point h, Point t) {
		int dx = Math.abs(h.x - t.x);
		int dy = Math.abs(h.y - t.y);
		return dx <= 1 && dy <= 1;
	}


	private static Point vector(Point h, Point t) {
		int distX = h.x - t.x;
		int distY = h.y - t.y;
		int deltaX = 0;
		int deltaY = 0;
		// Move one step straight towards head for same row or same column
		if (distX == 0)
			deltaY = distY / Math.abs(distY);
		else if (distY == 0)
			deltaX = distX / Math.abs(distX);
		// Move one step diagonally
		else {
			if (Math.abs(distX) > 1) { // Majority move in x direction
				deltaX = distX / Math.abs(distX);
				deltaY = distY; // Move in-line with the head row
			}
			else if (Math.abs(distY) > 1) { // Majority move in y direction
				deltaX = distX; // Move under/above head on the head col
				deltaY = distY / Math.abs(distY);
			}
		}
		return new Point(deltaX, deltaY); // Vector to translate by to move t towards h
	}
	

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Point h = new Point(0, 0);
		Point[] rope = new Point[10];
		rope[0] = h;
		for (int i = 1; i < rope.length; i++)
			rope[i] = new Point(0, 0);
		Set<Point> tVisited = new HashSet<>();
		while (s.hasNextLine()) {
			String cmd = s.nextLine();
			String dir = cmd.split(" ")[0];
			int qty = Integer.parseInt(cmd.split(" ")[1]);

			for (int i = 0; i < qty; i++) {
				switch (dir) { // Pts in cartesian plane, so up ==> y++, down ==> y--
				case "U" -> h.translate(0, 1);
				case "D" -> h.translate(0, -1);
				case "L" -> h.translate(-1, 0);
				case "R" -> h.translate(1, 0);
				}

				// Move rope
				for (int k = 1; k < rope.length; k++) {
					Point lead = rope[k - 1];
					Point knot = rope[k];
					if (k == rope.length - 1)
						tVisited.add(new Point(knot.x, knot.y)); // Log position
					while (!touching(lead, knot)) {
						Point vector = vector(lead, knot);
						knot.translate(vector.x, vector.y);
					}
				}
			}

			draw(rope, 26, 21);
			System.out.println();
		}
		System.out.println("ANSWER: " + tVisited.size());
	}


	private static void draw(Point[] rope, int width, int height) {
		for (int y = -height; y < height; y++) {
			for (int x = -width; x < width; x++) {
				if (Arrays.asList(rope).contains(new Point(x, y)))
					System.out.print(Arrays.asList(rope).indexOf(new Point(x, y)));
				else if (x == 0 && y == 0)
					System.out.print("s");
				else
					System.out.print(".");
			}
			System.out.println();
		}
	}

}
