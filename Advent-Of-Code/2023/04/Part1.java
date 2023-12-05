import java.util.*;
import java.io.File;


class Card {

	private List<Integer> winning;
	private List<Integer> drawn;
	

	public Card(String def) {
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


	public int getPoints() {
		int matches = 0;
		for (int n : this.drawn) {
			if (this.winning.contains(n))
				matches++;
		}
		if (matches == 0)
			return 0;
		return (int) Math.pow(2, matches - 1);
	}

}


public class Part1 {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));
		List<Card> cards = new ArrayList<>();
		while (s.hasNextLine())
			cards.add(new Card(s.nextLine()));
		int ans = 0;
		for (Card card : cards)
			ans += card.getPoints();
		System.out.println("ANSWER: " + ans);
	}

}
