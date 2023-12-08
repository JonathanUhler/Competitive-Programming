import java.util.*;
import java.io.File;


class Node {

	private String id;
	private Node left;
	private Node right;
	

	public Node(String id) {
		this.id = id;
		this.left = null;
		this.right = null;
	}


	public String getId() {
		return this.id;
	}


	public Node getLeft() {
		return this.left;
	}


	public Node getRight() {
		return this.right;
	}


	public void setLeft(Node left) {
		this.left = left;
	}


	public void setRight(Node right) {
		this.right = right;
	}


	@Override
	public String toString() {
		return this.id + " = " +
			"(" + (this.left != null ? this.left.id : "<null>") + ", " +
			(this.right != null ? this.right.id : "<null>") + ")";
	}

}


public class Part1 {

	private static Node getNode(Map<String, Node> nodes, String id) {
		if (nodes.containsKey(id))
			return nodes.get(id);
		return new Node(id);
	}
	

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));

		// Set up
		String directions = s.nextLine();
		s.nextLine();
		Map<String, Node> nodes = new HashMap<>();
		while (s.hasNextLine()) {
			String def = s.nextLine();
			def = def.replaceAll("\\(", "").replaceAll("\\)", "");
			String currId = def.split(" = ")[0];
			String leftId = def.split(" = ")[1].split(", ")[0];
			String rightId = def.split(" = ")[1].split(", ")[1];
			Node curr = getNode(nodes, currId);
			nodes.put(currId, curr);
			Node left = getNode(nodes, leftId);
			nodes.put(leftId, left);
			Node right = getNode(nodes, rightId);
			nodes.put(rightId, right);
			curr.setLeft(left);
			curr.setRight(right);
		}

		// Compute
		Node curr = nodes.get("AAA");
		int steps = 0;
		while (!curr.getId().equals("ZZZ")) {
			char instruction = directions.charAt(steps % directions.length());
			switch (instruction) {
			case 'L' -> curr = curr.getLeft();
			case 'R' -> curr = curr.getRight();
			}
			steps++;
		}

		// Return
		System.out.println("ANSWER: " + steps);
	}

}
