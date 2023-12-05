import java.util.*;
import java.io.File;


class Monkey {

	private int numInspections;
	private LinkedList<Integer> items;
	private String operation;
	private int testNumber;
	private int trueMonkey;
	private int falseMonkey;


	public Monkey(String[] algorithm) {
		this.numInspections = 0;
		this.items = new LinkedList<>();
		for (String line : algorithm) {
			line = line.trim();
			if (line.startsWith("Starting items: ")) {
				String[] itemsSplit = line.substring(16).split(", ");
				for (String item : itemsSplit)
					this.items.add(Integer.parseInt(item));
			}
			else if (line.startsWith("Operation: new = "))
				this.operation = line.substring(17).replaceAll("old", "x");
			else if (line.startsWith("Test: divisible by "))
				this.testNumber = Integer.parseInt(line.split(" ")[3]);
			else if (line.startsWith("If true: throw to monkey "))
				this.trueMonkey = Integer.parseInt(line.split(" ")[5]);
			else if (line.startsWith("If false: throw to monkey "))
				this.falseMonkey = Integer.parseInt(line.split(" ")[5]);
			else
				throw new IllegalArgumentException("Unknown algorithm step: " + line);
		}
	}


	public int apply(int x) {
		int y = 0;
		String operator = "+"; // Will start by loading the first value into y
		int operand = 0;
		String[] symbols = this.operation.split(" ");
		for (String symbol : symbols) {
			switch (symbol) {
			case "+":
			case "-":
			case "*":
			case "/":
				operator = symbol;
				continue;
			case "x":
				operand = x;
				break;
			default:
				operand = Integer.parseInt(symbol);
				break;
			}

			switch (operator) {
			case "+" -> y += operand;
			case "-" -> y -= operand;
			case "*" -> y *= operand;
			case "/" -> y /= operand;
			}
		}
		return y;
	}


	public boolean hasItemToInspect() {
		return this.items.size() > 0;
	}


	public int inspect() {
		this.numInspections++;

		int item = this.items.pollFirst();
		item = this.apply(item);
		item /= 3;
		return item;
	}


	public int throwsTo(int item) {
		if (item % this.testNumber == 0)
			return trueMonkey;
		return falseMonkey;
	}


	public void give(int item) {
		this.items.add(item);
	}


	public int getNumInspections() {
		return this.numInspections;
	}

}


public class Part1 {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));

		// Create list of monkeys
		List<Monkey> monkeys = new ArrayList<>();
		while (s.hasNextLine()) {
			String[] algorithm = new String[5];
			s.nextLine(); // Clear monkey number
			for (int i = 0; i < algorithm.length; i++)
				algorithm[i] = s.nextLine();
			if (s.hasNextLine())
				s.nextLine(); // Clear blank line
			monkeys.add(new Monkey(algorithm));
		}

		// Simulate turns
		for (int round = 0; round < 20; round++) {
			for (Monkey m : monkeys) {
				while (m.hasItemToInspect()) {
					int item = m.inspect();
					int dest = m.throwsTo(item);
					monkeys.get(dest).give(item);
				}
			}
		}

		// Calculate answer
		List<Integer> inspections = new ArrayList<>();
		for (Monkey m : monkeys)
			inspections.add(m.getNumInspections());
		Collections.sort(inspections);
		Collections.reverse(inspections);
		System.out.println("ANSWER: " + (inspections.get(0) * inspections.get(1)));
	}

}
