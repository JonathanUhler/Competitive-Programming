import java.util.*;
import java.io.File;


class Card {

	private String def;
	private int number;
	private List<Integer> winning;
	private List<Integer> drawn;
	

	public Card(String def) {
		this.def = def;
		this.number = Integer.parseInt(def.split(":")[0].split("\\s+")[1]);
		this.winning = new ArrayList<>();
		this.drawn = new ArrayList<>();
		
		def = def.replaceAll("\\s+", " ");
		def = def.split(": ")[1];
		String[] winningStr = def.split("\\ \\|\\ ")[0].split(" ");
		String[] drawnStr = def.split("\\ \\|\\ ")[1].split(" ");

		for (String s : winningStr)
			this.winning.add(Integer.parseInt(s.trim()));
		for (String s : drawnStr)
			this.drawn.add(Integer.parseInt(s.trim()));
	}


	public Card copy() {
		return new Card(this.def);
	}


	public int getNumber() {
		return this.number;
	}


	public int getMatches() {
		int matches = 0;
		for (int n : this.drawn) {
			if (this.winning.contains(n))
				matches++;
		}
		return matches;
	}

}


public class Part2 {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));
		List<Card> cards = new ArrayList<>();
		while (s.hasNextLine())
			cards.add(new Card(s.nextLine()));
		for (int c = 0; c < cards.size(); c++) {
			Card card = cards.get(c);
			int matches = card.getMatches();
			int number = card.getNumber();
			if (c % 100000 == 0)
				System.out.println(c + " / " + cards.size());
			for (int i = number; i < number + matches; i++)
				cards.add(cards.get(i).copy());
		}
		System.out.println("ANSWER: " + cards.size());
	}

}
