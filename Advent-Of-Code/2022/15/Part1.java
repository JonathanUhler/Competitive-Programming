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


public class Part1 {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));
		int row = 2000000;
		List<Sensor> sensors = new ArrayList<>();
		Set<Integer> beaconXInRow = new HashSet<>();
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

			if (by == row)
				beaconXInRow.add(bx);
		}

		Set<Integer> covered = new HashSet<>();
		for (Sensor sensor : sensors) {
			int coverage = sensor.getCoverageInRow(row);
			if (coverage <= 0)
				continue;
			int start = sensor.getCoverageStart(row);
			for (int i = start; i < start + coverage; i++) {
				if (beaconXInRow.contains(i))
					continue;
				covered.add(i);
			}
		}
		
		System.out.println("ANSWER: " + covered.size());
	}

}
