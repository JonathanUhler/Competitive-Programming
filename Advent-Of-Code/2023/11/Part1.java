import java.util.*;
import java.io.File;
import java.awt.Point;
import java.util.Map.Entry;


public class Part1 {

	private static Set<Integer> emptyRows(char[][] map) {
		Set<Integer> rows = new HashSet<>();
		for (int r = 0; r < map.length; r++) {
			boolean empty = true;
			for (int c = 0; c < map[r].length; c++) {
				if (map[r][c] == '#') {
					empty = false;
					break;
				}
			}
			if (empty)
				rows.add(r);
		}
		return rows;
	}


	private static Set<Integer> emptyCols(char[][] map) {
		Set<Integer> cols = new HashSet<>();
		for (int c = 0; c < map[0].length; c++) {
			boolean empty = true;
			for (int r = 0; r < map.length; r++) {
				if (map[r][c] == '#') {
					empty = false;
					break;
				}
			}
			if (empty)
				cols.add(c);
		}
		return cols;
	}


	private static int numPassed(int n, Set<Integer> s) {
		int passed = 0;
		for (int m : s)
			if (n > m)
				passed++;
		return passed;
	}


	private static char[][] expand(char[][] map, Set<Integer> rows, Set<Integer> cols) {
		char[][] newMap = new char[map.length + rows.size()][map.length + cols.size()];
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				newMap[r + numPassed(r, rows)][c + numPassed(c, cols)] = map[r][c];
			}
		}
		return newMap;
	}


	private static List<Point> galaxies(char[][] map) {
		List<Point> s = new ArrayList<>();
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if (map[r][c] == '#')
					s.add(new Point(c, r));
			}
		}
		return s;
	}


	private static List<Entry<Point, Point>> paths(List<Point> galaxies) {
		List<Entry<Point, Point>> p = new ArrayList<>();
		for (int i = 0; i < galaxies.size(); i++) {
			for (int j = i + 1; j < galaxies.size(); j++) {
				p.add(Map.entry(galaxies.get(i), galaxies.get(j)));
			}
		}
		return p;
	}
	

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));
		int size = 140;
		char[][] map = new char[size][size];
		for (int i = 0; i < size; i++)
			map[i] = s.nextLine().toCharArray();
		map = expand(map, emptyRows(map), emptyCols(map));

		List<Entry<Point, Point>> paths = paths(galaxies(map));
		int sum = 0;
		for (Entry<Point, Point> path : paths) {
			Point a = path.getKey();
			Point b = path.getValue();
			int dx = Math.abs(a.x - b.x);
			int dy = Math.abs(a.y - b.y);
			sum += dx + dy;
		}
		System.out.println("ANSWER: " + sum);
	}

}
