import java.util.*;
import java.io.File;
import java.awt.Point;


public class Part1 {

	private static Set<Point> adjacent(int[][] map, int r, int c) {
		Set<Point> points = new HashSet<>();
		for (int dir = 0; dir < 4; dir++) {
			// Adjacent point
			int ar = r + ((dir - 1) % 2);
			int ac = c + ((dir - 2) % 2);

			if (ar < 0 || ar >= map.length || ac < 0 || ac >= map[r].length)
				continue;

			int currentVal = map[r][c];
			int adjacentVal = map[ar][ac];
			if (adjacentVal > currentVal + 1)
				continue;

			points.add(new Point(ar, ac));
		}
		return points;
	}


	private static int shortestPath(int[][] map, Point start, Point end) {
		Set<Point> visited = new HashSet<>();
		Map<Point, Integer> distances = new HashMap<>();

		// Init distances (all +inf except start, which has distance 0 to itself)
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				distances.put(new Point(r, c), Integer.MAX_VALUE);
			}
		}
		distances.put(start, 0);

		// Go until no nodes left to visit
		Point current = start;
		while (current != null) {
			Set<Point> edges = adjacent(map, current.x, current.y);
			int startToCurrent = distances.get(current);
			for (Point edge : edges) {
				if (visited.contains(edge))
					continue;

				int currentToEdge = 1; // All distances between valid steps are 1
				int startToEdge = distances.get(edge);

				// Check for better path
				if (startToCurrent + currentToEdge < startToEdge)
					distances.put(edge, startToCurrent + currentToEdge);
			}

			visited.add(current);

			// Update current with closest node
			current = null;
			int minDist = Integer.MAX_VALUE;
			for (Point node : distances.keySet()) {
				int distToNode = distances.get(node);
				if (distToNode < minDist && !visited.contains(node)) {
					current = node;
					minDist = distToNode;
				}
			}
		}
		
		return distances.get(end);
	}
	

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));

		// Build structure
		List<List<Integer>> mapList = new ArrayList<>();
		Point start = null;
		Point end = null;
		while (s.hasNextLine()) {
			String rowStr = s.nextLine();
			List<Integer> row = new ArrayList<>();
			for (char c : rowStr.toCharArray()) {
				switch (c) {
				case 'S' -> {
					row.add(0);
					start = new Point(mapList.size(), rowStr.indexOf(c));
				}
				case 'E' -> {
					row.add(27);
					end = new Point(mapList.size(), rowStr.indexOf(c));
				}
				default -> row.add((int) c - 96);
				}
			}
			mapList.add(row);
		}

		// Convert to regular array
		int[][] map = new int[mapList.size()][mapList.get(0).size()];
		for (int r = 0; r < mapList.size(); r++)
			map[r] = mapList.get(r).stream().mapToInt(i -> i).toArray();

		// Search for shortest path
		System.out.println("start=" + start + ", end=" + end);
		int steps = shortestPath(map, start, end);
		System.out.println("ANSWER: " + steps);
	}

}
