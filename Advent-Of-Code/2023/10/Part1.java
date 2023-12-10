import java.util.*;
import java.io.File;
import java.awt.Point;


public class Part1 {

	private static Map<Character, Point> directions = Map.of('N', new Point(0, -1),
															 'E', new Point(1, 0),
															 'S', new Point(0, 1),
															 'W', new Point(-1, 0));
	

	private static boolean validConnection(char curr, char next, char dir) {
		Set<Character> n = Set.of('|', '7', 'F');
		Set<Character> e = Set.of('-', 'J', '7');
		Set<Character> s = Set.of('|', 'L', 'J');
		Set<Character> w = Set.of('-', 'L', 'F');
		
		switch (curr) {
		case '|':
			return (dir == 'N' && n.contains(next)) || (dir == 'S' && s.contains(next));
		case '-':
			return (dir == 'E' && e.contains(next)) || (dir == 'W' && w.contains(next));
		case 'L':
			return (dir == 'N' && n.contains(next)) || (dir == 'E' && e.contains(next));
		case 'J':
			return (dir == 'N' && n.contains(next)) || (dir == 'W' && w.contains(next));
		case '7':
			return (dir == 'S' && s.contains(next)) || (dir == 'W' && w.contains(next));
		case 'F':
			return (dir == 'S' && s.contains(next)) || (dir == 'E' && e.contains(next));
		case 'S':
			return ((dir == 'N' && n.contains(next)) ||
					(dir == 'E' && e.contains(next)) ||
					(dir == 'S' && s.contains(next)) ||
					(dir == 'W' && w.contains(next)));
		}
		return false;
	}
	

	private static Point next(char[][] map, Point curr, Set<Point> visited) {
		for (Character dir : directions.keySet()) {
			Point ds = directions.get(dir);
			Point next = new Point(curr.x + ds.x, curr.y + ds.y);
			if (next.y < 0 || next.y > map.length || next.x < 0 || next.x > map[next.y].length)
				continue;
			if (visited.contains(next))
				continue;

			char c = map[next.y][next.x];
			if (!validConnection(map[curr.y][curr.x], c, dir))
				continue;
			return next;
		}
		return null;
	}
	

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));
		int size = 140;
		char[][] map = new char[size][size];
		Point curr = null;
		for (int i = 0; i < size; i++) {
			String line = s.nextLine();
			if (line.contains("S"))
				curr = new Point(line.indexOf("S"), i);
			map[i] = line.toCharArray();
		}

		Set<Point> visited = new HashSet<>();
		while (curr != null) {
			visited.add(curr);
			curr = next(map, curr, visited);
		}
		
		System.out.println("ANSWER: " + visited.size() / 2);
	}

}
