import java.util.*;
import java.io.File;


class Monkey {

	public static int supermodulo;
	

	private long numInspections;
	private LinkedList<Long> items;
	private String operation;
	private long testNumber;
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
					this.items.add(Long.parseLong(item));
			}
			else if (line.startsWith("Operation: new = "))
				this.operation = line.substring(17).replaceAll("old", "x");
			else if (line.startsWith("Test: divisible by "))
				this.testNumber = Long.parseLong(line.split(" ")[3]);
			else if (line.startsWith("If true: throw to monkey "))
				this.trueMonkey = Integer.parseInt(line.split(" ")[5]);
			else if (line.startsWith("If false: throw to monkey "))
				this.falseMonkey = Integer.parseInt(line.split(" ")[5]);
			else
				throw new IllegalArgumentException("Unknown algorithm step: " + line);
		}
	}


	public long apply(long x) {
		long y = 0;
		String operator = "+"; // Will start by loading the first value into y
		long operand = 0;
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


	public long getTestNumber() {
		return this.testNumber;
	}


	public boolean hasItemToInspect() {
		return this.items.size() > 0;
	}


	public long inspect() {
		this.numInspections++;

		long item = this.items.pollFirst();
		item = this.apply(item);
		return item % Monkey.supermodulo;
	}


	public int throwsTo(long item) {
		if (item % this.testNumber == 0)
			return trueMonkey;
		return falseMonkey;
	}


	public void give(long item) {
		this.items.add(item);
	}


	public long getNumInspections() {
		return this.numInspections;
	}

}


public class Part2 {

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

		// Source: https://www.reddit.com/r/adventofcode/comments/zih7gf/2022_day_11_part_2_what_does_it_mean_find_another/
		Monkey.supermodulo = 1;
		for (Monkey m : monkeys)
			Monkey.supermodulo *= m.getTestNumber();
		System.out.println(Monkey.supermodulo);

		// Simulate turns
		for (int round = 0; round < 10000; round++) {
			for (Monkey m : monkeys) {
				while (m.hasItemToInspect()) {
					long item = m.inspect();
					int dest = m.throwsTo(item);
					monkeys.get(dest).give(item);
				}
			}
			if (round % 1000 == 999 || round == 19) {
				System.out.println("== After round " + (round+1) + " ==");
				for (int m = 0; m < monkeys.size(); m++)
					System.out.println("Monkey " + m + " inspected items " +
									   monkeys.get(m).getNumInspections() + " times.");
			}
		}

		// Calculate answer
		List<Long> inspections = new ArrayList<>();
		for (Monkey m : monkeys)
			inspections.add(m.getNumInspections());
		Collections.sort(inspections);
		Collections.reverse(inspections);
		System.out.println("ANSWER: " + (inspections.get(0) * inspections.get(1)));
	}

}
