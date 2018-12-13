import java.awt.Color;
import java.util.Collection;
import java.util.Scanner;

import game.Die;
import gamestate.GameState;

public class TextRunner {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		GameState game = new GameState(false, false, false, false, false);
		while (!game.isGameEnded()) {
			long turnIndex = game.getTurnIndex();
			System.out.println("Track:\n" + game.getTrack() + "\n");
			System.out.println("Camel Rankings:\n" + game.getCamelRankings() + "\n");
			System.out.println("Played Win Bets:\n" + game.getWinBets() + "\n");
			System.out.println("Played Lose Bets:\n" + game.getLoseBets() + "\n");
			System.out.println("Players:\n" + collectionToString(game.getPlayers()));
			System.out.println(game.getCurPlayer().getName() + "'s Turn:");
			do {
				System.out.println("A: Place Round Bet\tB: Place Race Bet\tC: Roll Die\tD: Place Desert Card");
				System.out.print("Input: ");
				String choice = scanner.nextLine().trim();
				if (choice.equalsIgnoreCase("a")) {
					System.out.print("What color camel are you betting on? ");
					Color c = GameState.COLORBIMAP.get(scanner.nextLine().toLowerCase());
					game.placeRoundBet(c);
				} else if (choice.equalsIgnoreCase("b")) {
					System.out.print("Win or Lose: ");
					String bet = scanner.nextLine();
					System.out.print("Bet Color: ");
					Color c = GameState.COLORBIMAP.get(scanner.nextLine().toLowerCase());
					if (bet.equalsIgnoreCase("win")) {
						game.placeWinBet(c);
					} else if (bet.equalsIgnoreCase("lose")) {
						game.placeLoseBet(c);
					}
				} else if (choice.equalsIgnoreCase("c")) {
					game.moveCamel();
					Die d = game.getPyramid().getLastDie();
					String color = toUpperCase(GameState.COLORBIMAP.inverse().get(d.getColor()));
					System.out.println(color + " rolled a " + d.getLastRoll());
				} else if (choice.equalsIgnoreCase("d")) {
					System.out.print("+1 or -1: ");
					boolean isOasis = scanner.nextInt() == 1;
					System.out.print("Tile Number: ");
					int tileNum = scanner.nextInt();
					scanner.nextLine();
					game.placeDesertCard(isOasis, tileNum);
				}

				if (turnIndex == game.getTurnIndex()) {
					System.out.println("Invalid Input!");
				}
			} while (turnIndex == game.getTurnIndex());
			System.out.println();
		}
		scanner.close();
	}

	public static String toUpperCase(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	public static String collectionToString(Collection<?> c) {
		StringBuilder sb = new StringBuilder();

		for (Object o : c) {
			sb.append(o.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
