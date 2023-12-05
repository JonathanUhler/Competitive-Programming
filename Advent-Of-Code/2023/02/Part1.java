import java.util.*;
import java.io.File;


class Round {

	private int numRed;
	private int numGreen;
	private int numBlue;


	public Round(String def) {
		for (String colorDef : def.split(", ")) {
			int amount = Integer.parseInt(colorDef.split(" ")[0]);
			String color = colorDef.split(" ")[1];
			switch (color) {
			case "red" -> this.numRed = amount;
			case "green" -> this.numGreen = amount;
			case "blue" -> this.numBlue = amount;
			}
		}
	}


	public boolean isPossible(int r, int g, int b) {
		return (this.numRed <= r &&
				this.numGreen <= g &&
				this.numBlue <= b);
	}

}


class Game {

	private int id;
	private List<Round> rounds;


	public Game(String def) {
		this.id = Integer.parseInt(def.split(" ")[1].replace(":", ""));
		this.rounds = new ArrayList<>();
		String roundsDef = def.split(": ")[1];
		for (String roundDef : roundsDef.split("; "))
			this.rounds.add(new Round(roundDef));
	}


	public int getId() {
		return this.id;
	}


	public boolean isPossible(int r, int g, int b) {
		for (Round round : this.rounds) {
			if (!round.isPossible(r, g, b))
				return false;
		}
		return true;
	}

}


public class Part1 {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));
		List<Game> games = new ArrayList<>();
		while (s.hasNextLine())
			games.add(new Game(s.nextLine()));
		int ans = 0;
		for (Game game : games) {
			if (game.isPossible(12, 13, 14))
				ans += game.getId();
		}
		System.out.println("ANSWER: " + ans);
	}

}
