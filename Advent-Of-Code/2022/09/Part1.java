import java.util.*;
import java.awt.Point;


public class Part1 {

	private static boolean touching(Point h, Point t) {
		int dx = Math.abs(h.x - t.x);
		int dy = Math.abs(h.y - t.y);
		return dx <= 1 && dy <= 1;
	}
	

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Point h = new Point(0, 0);
		Point t = new Point(0, 0);
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

				// Move tail
				tVisited.add(new Point(t.x, t.y)); // Log position
				if (touching(h, t))
					continue;
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
				
				t.translate(deltaX, deltaY);
			}
		}
		System.out.println("ANSWER: " + tVisited.size());
	}

}
