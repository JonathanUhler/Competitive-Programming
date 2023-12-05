import java.util.*;


public class Part2 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Map<String, Integer> sizes = new HashMap<>();
		LinkedList<String> stack = new LinkedList<>();
		while (s.hasNextLine()) {
			String cmd = s.nextLine();
			if (cmd.startsWith("$ ls") || cmd.startsWith("dir"))
				continue;

			if (cmd.startsWith("$ cd")) {
				String arg = cmd.split(" ")[2];
				if (arg.equals(".."))
					stack.pollLast();
				else {
					String path = arg;
					if (stack.size() > 0)
						path = stack.peekLast() + "_" + arg;
					stack.add(path);
				}
			}
			else {
				int size = Integer.parseInt(cmd.split(" ")[0]);
				String file = cmd.split(" ")[1];
				for (String path : stack) {
					if (!sizes.containsKey(path))
						sizes.put(path, 0);
					sizes.put(path, sizes.get(path) + size);
				}
			}
		}

		int rootSize = sizes.get("/");
		int totalSize = 70000000;
		int neededSize = 30000000;
		int needToFree = neededSize - (totalSize - rootSize);
		System.out.println(needToFree);
		int freedSize = totalSize;
		for (String dir : sizes.keySet()) {
			int dirSize = sizes.get(dir);
			if (dirSize >= needToFree && dirSize < freedSize)
				freedSize = dirSize;
		}
		System.out.println("ANSWER: " + freedSize);
	}

}
