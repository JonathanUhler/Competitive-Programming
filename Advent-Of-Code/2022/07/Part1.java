import java.util.*;


public class Part1 {

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

		int sum100k = 0;
		for (int size : sizes.values()) {
			if (size <= 100000)
				sum100k += size;
		}
		System.out.println("ANSWER: " + sum100k);
	}

}


/*
public class Part1 {

	private static int getSize(Map<String, Object> dir) {
		int size = 0;
		for (String sub : dir.keySet()) {
			if (sub.equals(".."))
				continue;

			if (sub.equals("[FILES]")) {
				Map<String, Integer> files = (Map<String, Integer>) dir.get(sub);
				for (int fileSize : files.values())
					size += fileSize;
			}
			else {
				size += getSize((Map<String, Object>) dir.get(sub));
			}
		}
		return size;
	}


	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		// Start of a hacky tree
		Map<String, Object> root = new HashMap<>();
		Map<String, Map<String, Object>> dirs = new HashMap<>();
		dirs.put("/", root);
		Map<String, Object> cwd = root;

		// Processing
		while (s.hasNextLine()) {
			String str = s.nextLine();
			// Process cd
			if (str.startsWith("$ cd")) {
				String arg = str.split(" ")[2];
				if (arg.equals("/"))
					cwd = root;
				else
					cwd = (Map<String, Object>) cwd.get(arg);
			}
			// Process ls
			else if (str.startsWith("$ ls")) {} // NOP, contents processed in else block
			// Process output of ls (files and dirs)
			else {
				String type = str.split(" ")[0];
				String id = str.split(" ")[1];
				if (type.equals("dir")) {
					// Connec the new "node"
					Map<String, Object> dir = new HashMap<>();
					cwd.put(id, dir);
					dir.put("..", cwd);
					dirs.put(id, dir); // Save as a flattened list of directories
				}
				else {
					if (!cwd.containsKey("[FILES]"))
						cwd.put("[FILES]", new HashMap<>());
					Map<String, Object> files = (Map<String, Object>) cwd.get("[FILES]");
					files.put(id, Integer.parseInt(type));
				}
			}
		}

		// Searching
		int sum100k = 0;
		Map<String, Integer> sizes = new HashMap<>();
		for (String name : dirs.keySet()) {
			System.out.println("Size of: " + name);
			Map<String, Object> dir = dirs.get(name);
			int size = getSize(dir);
			sizes.put(name, size);

			if (size <= 100000)
				sum100k += size;
		}
		System.out.println("------");
		draw("/", root, 0);
		System.out.println("ANSWER: " + sum100k);
	}


	private static void draw(String dirName, Map<String, Object> dirContents, int indent) {
		String indents = "";
		for (int i = 0; i < indent; i++)
			indents += "  ";
		System.out.println(indents + "- " + dirName +
						   " (dir, " + getSize(dirContents) + ")");

		for (String sub : dirContents.keySet()) {
			if (sub.equals(".."))
				continue;

			if (sub.equals("[FILES]")) {
				Map<String, Integer> files = (Map<String, Integer>) dirContents.get(sub);
				for (String file : files.keySet())
					System.out.println(indents + "  - " + file +
									   "(file, size=" + files.get(file) + ")");
			}
			else {
				draw(sub, (Map<String, Object>) dirContents.get(sub), indent + 1);
			}
		}
	}

}
*/
