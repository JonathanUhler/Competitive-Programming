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


	public int[] getSet() {
		return new int[] {this.numRed, this.numGreen, this.numBlue};
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


	public int[] getPossibleSet() {
		int r = 0;
		int g = 0;
		int b = 0;
		for (Round round : this.rounds) {
			int[] roundSet = round.getSet();
			if (roundSet[0] > r)
				r = roundSet[0];
			if (roundSet[1] > g)
				g = roundSet[1];
			if (roundSet[2] > b)
				b = roundSet[2];
		}
		return new int[] {r, g, b};
	}


	public int getPower() {
		int[] possibleSet = this.getPossibleSet();
		return possibleSet[0] * possibleSet[1] * possibleSet[2];
	}

}


public class Part2 {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("input.txt"));
		List<Game> games = new ArrayList<>();
		while (s.hasNextLine())
			games.add(new Game(s.nextLine()));
		int ans = 0;
		for (Game game : games)
			ans += game.getPower();
		System.out.println("ANSWER: " + ans);
	}

}
