import java.util.*;
import java.io.File;
import java.awt.Point;


class Line {

	private Point[] points;
	

	public Line(String struct) {
		String[] points = struct.split(" -> ");
		this.points = new Point[points.length];
		for (int i = 0; i < this.points.length; i++) {
			String point = points[i];
			int x = Integer.parseInt(point.split(",")[0]);
			int y = Integer.parseInt(point.split(",")[1]);
			this.points[i] = new Point(x, y);
		}
	}


	public Point getMinBound() {
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		for (Point p : points) {
			if (p.x < minX)
				minX = p.x;
			if (p.y < minY)
				minY = p.y;
		}
		return new Point(minX, minY);
	}


	public Point getMaxBound() {
		int maxX = 0;
		int maxY = 0;
		for (Point p : points) {
			if (p.x > maxX)
				maxX = p.x;
			if (p.y > maxY)
				maxY = p.y;
		}
		return new Point(maxX, maxY);
	}


	public void normalize(int dx, int dy) {
		for (Point p : this.points)
			p.translate(dx, dy);
	}


	public void draw(int[][] map) {
		for (int p = 0; p < this.points.length - 1; p++) {
			Point p1 = this.points[p];
			Point p2 = this.points[p + 1];
			int minX = Math.min(p1.x, p2.x);
			int maxX = Math.max(p1.x, p2.x);
			int minY = Math.min(p1.y, p2.y);
			int maxY = Math.max(p1.y, p2.y);
			for (int y = minY; y <= maxY; y++) {
				for (int x = minX; x <= maxX; x++) {
					map[y][x] = Integer.MAX_VALUE;
				}
			}
		}
	}

}


public class Part1 {

	private static Point minBound(List<Line> lines) {
		int minX = Integer.MAX_VALUE;
		for (Line line : lines) {
			Point minBound = line.getMinBound();
			if (minBound.x < minX)
				minX = minBound.x;
		}
		return new Point(minX, 0);
	}


	private static Point maxBound(List<Line> lines) {
		int maxX = 0;
		int maxY = 0;
		for (Line line : lines) {
			Point maxBound = line.getMaxBound();
			if (maxBound.x > maxX)
				maxX = maxBound.x;
			if (maxBound.y > maxY)
				maxY = maxBound.y;
		}
		return new Point(maxX, maxY);
	}
	
	
	private static boolean simulate(int[][] map, int originX, int originY) {
		int sandX = originX;
		int sandY = originY;
		boolean canMove = true;
		while (canMove) {
			if (sandY < 0 || sandY + 1 >= map.length)
				return false;
			if (map[sandY + 1][sandX] == 0) {
				sandY++;
				continue;
			}

			if (sandX - 1 < 0)
				return false;
			if (map[sandY + 1][sandX - 1] == 0) {
				sandX--;
				sandY++;
				continue;
			}

			if (sandX + 1 >= map[originY].length)
				return false;
			if (map[sandY + 1][sandX + 1] == 0) {
				sandX++;
				sandY++;
				continue;
			}

			canMove = false;
		}
		map[sandY][sandX] = 1;
		return true;
	}
	

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));

		// Define initial structure
		List<Line> lines = new ArrayList<>();
		while (s.hasNextLine()) {
			String struct = s.nextLine();
			lines.add(new Line(struct));
		}

		// Shift all points so (0,0) is the top-left position
		Point minBound = minBound(lines);
		for (Line line : lines)
			line.normalize(-minBound.x, -minBound.y);

		// Add all points to a common map
		Point maxBound = maxBound(lines);
		int[][] map = new int[maxBound.y + 1][maxBound.x + 1];
		for (Line line : lines)
			line.draw(map);

		// Simulate the sand
		int ans = 0;
		while (simulate(map, 500 - minBound.x, 0)) {
			ans++;
			print(map);
		}
		
		System.out.println("ANSWER: " + ans);
	}


	private static void print(int[][] map) {
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				int n = map[y][x];
				switch (n) {
				case 0 -> System.out.print(".");
				case Integer.MAX_VALUE -> System.out.print("#");
				default -> System.out.print("o");
				}
			}
			System.out.println();
		}
	}

}
