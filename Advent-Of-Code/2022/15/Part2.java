import java.util.*;
import java.io.File;


class Sensor {

	private int sx;
	private int sy;
	private int bx;
	private int by;
	

	public Sensor(int sx, int sy, int bx, int by) {
		this.sx = sx;
		this.sy = sy;
		this.bx = bx;
		this.by = by;
	}


	public int getX() {
		return this.sx;
	}


	public int getY() {
		return this.sy;
	}


	public int getCoverageRadius() {
		return Math.abs(this.sx - this.bx) + Math.abs(this.sy - this.by);
	}


	public int getCoverageInRow(int row) {
		int dy = Math.abs(this.sy - row);
		int coverage = 2 * this.getCoverageRadius() + 1;
		int coverageInRow = coverage - 2 * dy;
		return Math.max(coverageInRow, 0);
	}


	public int getCoverageStart(int row) {
		int dy = Math.abs(this.sy - row);
		int radius = this.getCoverageRadius();
		int start = this.sx - radius + dy;
		if (dy > radius)
			return Integer.MIN_VALUE; // No coverage for this row
		return start;
	}

}


class Interval {

	public int min;
	public int max;


	public Interval(int min, int max) {
		this.min = min;
		this.max = max;
	}


	public boolean chains(Interval other) {
		return other.max + 1 >= this.min;
	}


	public int compareTo(Interval other) {
		if (other.min < this.min)
			return 1;
		else if (this.min < other.min)
			return -1;
		else {
			if (other.max < this.max)
				return 1;
			else if (this.max < other.max)
				return -1;
		}
		return 0;
	}


	@Override
	public String toString() {
		return "[" + this.min + ", " + this.max + "]";
	}

}


public class Part2 {

	private static void binaryInsertion(Interval interval, List<Interval> intervals) {
		int start = 0;
		int end = intervals.size() - 1;
		while (start <= end) {
			int middle = (start + end) / 2;
			Interval middleInterval = intervals.get(middle);
			if (interval.compareTo(middleInterval) < 0)
				end = middle - 1;
			else if (interval.compareTo(middleInterval) > 0)
				start = middle + 1;
			else
				break;
		}
		intervals.add(start, interval);
	}


	private static void merge(List<Interval> intervals) {
		Stack<Interval> stack = new Stack<>();
		stack.push(intervals.get(0));

		for (int i = 1; i < intervals.size(); i++) {
			Interval top = stack.peek();
			Interval current = intervals.get(i);
			if (top.max < current.min)
				stack.push(current);
			else if (top.max < current.max)
				top.max = current.max;
		}

		intervals.clear();
		while (!stack.isEmpty())
			intervals.add(0, stack.pop());
	}
	

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));
		List<Sensor> sensors = new ArrayList<>();
		while (s.hasNextLine()) {
			String def = s.nextLine();
			String sxStr = def.split(" ")[2].split("=")[1];
			String syStr = def.split(" ")[3].split("=")[1];
			String bxStr = def.split(" ")[8].split("=")[1];
			String byStr = def.split(" ")[9].split("=")[1];
			int sx = Integer.parseInt(sxStr.replaceAll(",", ""));
			int sy = Integer.parseInt(syStr.replaceAll(":", ""));
			int bx = Integer.parseInt(bxStr.replaceAll(",", ""));
			int by = Integer.parseInt(byStr);
			sensors.add(new Sensor(sx, sy, bx, by));
		}

		int size = 4_000_000;
		for (int row = 0; row <= size; row++) {
			if (row % 10 == 0)
				System.out.println("Working: " + row + "/" + size);

			List<Interval> intervals = new ArrayList<>(sensors.size());
			for (Sensor sensor : sensors) {
				int coverage = sensor.getCoverageInRow(row);
				if (coverage <= 0)
					continue;
				int start = sensor.getCoverageStart(row);
				Interval interval = new Interval(start, start + coverage);
				binaryInsertion(interval, intervals);
			}

			merge(intervals);
			if (intervals.size() > 1) {
				System.out.println(intervals);
				long ans = (long) 4000000 * (long) intervals.get(0).max + (long) row;
				System.out.println("ANSWER: " + ans);
				return;
			}
		}
	}

}
