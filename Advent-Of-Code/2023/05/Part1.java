import java.util.*;
import java.io.File;


class Range {

	private long src;
	private long dest;
	private long len;
	

	public Range(long src, long dest, long len) {
		this.src = src;
		this.dest = dest;
		this.len = len;
	}


	public boolean contains(long n) {
		return n >= this.src && n < this.src + this.len;
	}


	public long getDest(long n) {
		if (!this.contains(n))
			return Long.MIN_VALUE;

		long delta = n - this.src;
		return this.dest + delta;
	}

}


public class Part1 {

	private static long get(List<Range> map, long src) {
		for (Range range : map) {
			if (range.contains(src))
				return range.getDest(src);
		}
		return src;
	}
	

	private static List<Range> makeMap(String mapDef) {
		List<Range> ranges = new ArrayList<>();
		for (String entryDef : mapDef.split("\n")) {
			long dest = Long.parseLong(entryDef.split(" ")[0]);
			long src = Long.parseLong(entryDef.split(" ")[1]);
			long len = Long.parseLong(entryDef.split(" ")[2]);
			ranges.add(new Range(src, dest, len));
		}
		return ranges;
	}
	

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));
		String[] seedsDef = null;
		String mapDef = "";
		Map<String, List<Range>> maps = new HashMap<>();
		while (s.hasNextLine()) {
			if (seedsDef == null) {
				seedsDef = s.nextLine().split(": ")[1].split(" ");
				s.nextLine();
			}
			
			String line = s.nextLine();
			if (line.length() == 0 || !s.hasNextLine()) {
				String mapName = mapDef.split("\n")[0].split(" ")[0];
				mapDef = mapDef.split(":\n")[1];
				maps.put(mapName, makeMap(mapDef));
				mapDef = "";
				continue;
			}

			mapDef += line + "\n";
		}

		long ans = Long.MAX_VALUE;
		for (String seedDef : seedsDef) {
			long seed = Long.parseLong(seedDef);
			long soil = get(maps.get("seed-to-soil"), seed);
			long fertilizer = get(maps.get("soil-to-fertilizer"), soil);
			long water = get(maps.get("fertilizer-to-water"), fertilizer);
			long light = get(maps.get("water-to-light"), water);
			long temperature = get(maps.get("light-to-temperature"), light);
			long humidity = get(maps.get("temperature-to-humidity"), temperature);
			long location = get(maps.get("humidity-to-location"), humidity);
			if (location < ans)
				ans = location;
		}
		
		System.out.println("ANSWER: " + ans);
	}

}
