import java.util.*;
import java.io.File;


class N {

	private int n;
	private int r;
	private int c;
	private int len;
	

	private N(int n, int r, int c, int len) {
		this.n = n;
		this.r = r;
		this.c = c;
		this.len = len;
	}
	

	public static N getInstance(int r, int c, char[][] schem) {
		if (r < 0 || r >= schem.length || c < 0 || c >= schem[r].length)
			return null;
		if (!Character.isDigit(schem[r][c]))
			return null;

		while (c - 1 >= 0 && Character.isDigit(schem[r][c - 1]))
			c--;
		int cStart = c;

		String n = "";
		while (c < schem[r].length && Character.isDigit(schem[r][c])) {
			n += Character.toString(schem[r][c]);
			c++;
		}
		
		if (n.length() == 0)
			return null;
		return new N(Integer.parseInt(n), r, cStart, n.length());
	}


	public int getNumber() {
		return this.n;
	}


	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}


	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof N))
			return false;

		N num = (N) o;
		return this.n == num.n && this.r == num.r && this.c == num.c && this.len == num.len;
	}


	@Override
	public String toString() {
		return this.n + " @ (" + this.r + ", " + this.c + ") for " + this.len;
	}

}


public class Part2 {

	private static int[][] dirs = new int[][] {
		{-1, -1},
		{-1, 0},
		{-1, 1},
		{0, -1},
		{0, 1},
		{1, -1},
		{1, 0},
		{1, 1}
	};


	private static Set<N> getAdjacent(int r, int c, char[][] schem) {
		Set<N> s = new HashSet<>();
		for (int[] dir : dirs) {
			int dr = r + dir[0];
			int dc = c + dir[1];
			N n = N.getInstance(dr, dc, schem);
			if (n == null)
				continue;
			s.add(n);
		}
		System.out.println(r + ", " + c + ":\t" + s);
		return s;
	}
	

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));
		int size = 140;
		char[][] schem = new char[size][size];
		int line = 0;
		while (s.hasNextLine()) {
			schem[line] = s.nextLine().toCharArray();
			line++;
		}

		int ans = 0;
		for (int r = 0; r < schem.length; r++) {
			for (int c = 0; c < schem[r].length; c++) {
				char ch = schem[r][c];
				if (ch != '*')
					continue;
				Set<N> parts = getAdjacent(r, c, schem);
				if (parts.size() != 2)
					continue;
				int gearRatio = 1;
				for (N n : parts)
					gearRatio *= n.getNumber();
				ans += gearRatio;
			}
		}
		System.out.println("ANSWER: " + ans);
	}

}
