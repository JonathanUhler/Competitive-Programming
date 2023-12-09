import java.util.*;
import java.io.File;


public class Part2 {

	private static int[] cast(String[] arr) {
		int[] iarr = new int[arr.length];
		for (int i = 0; i < arr.length; i++)
			iarr[i] = Integer.parseInt(arr[i]);
		return iarr;
	}
	

	private static boolean zeros(int[] arr) {
		int sum = 0;
		for (int n : arr)
			sum |= n;
		return sum == 0;
	}
	

	private static int extrapolate(int[] arr) {
		if (zeros(arr))
			return 0;
		int[] next = new int[arr.length - 1];
		for (int i = 0; i < arr.length - 1; i++)
			next[i] = arr[i + 1] - arr[i];
		return arr[0] - extrapolate(next);
	}
	

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));
		int ans = 0;
		while (s.hasNextLine())
			ans += extrapolate(cast(s.nextLine().split(" ")));
		System.out.println("ANSWER: " + ans);
	}

}
