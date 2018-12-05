package gamestate;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

import com.google.common.collect.ImmutableList;

import game.Die;
import game.Pyramid;
import immutable.Camel;
import immutable.RaceBettingCard;
import immutable.RoundBettingCard;

public class GameState {
	public final static List<Color> CAMELCOLORS = ImmutableList.of(Color.WHITE, Color.ORANGE, Color.YELLOW, Color.GREEN,
			new Color(51, 153, 255));
	private final static String[] names = { "KarleEngels1820", "Evile Vinciente", "A Confucius", "Prince Zuko",
			"Madame Bob Lee" };
	private final ArrayList<Camel> camels;
	private final ArrayList<Player> players;
	private final Queue<RaceBettingCard> winBets;
	private final Queue<RaceBettingCard> loseBets;
	private final Map<Color, TreeSet<RoundBettingCard>> roundBets;
	private final Track track;
	private final Pyramid pyramid;

	private Player curPlayer;
	private int curPlayerIndex;
	private long turnIndex;

	public GameState() {
		camels = new ArrayList<Camel>();
		for (int i = 0; i < CAMELCOLORS.size(); i++)
			camels.add(new Camel(CAMELCOLORS.get(i)));
		players = new ArrayList<Player>();
		for (int i = 0; i < 5; i++)
			players.add(new Player(names[i], CAMELCOLORS));
		winBets = new ArrayDeque<>();
		loseBets = new ArrayDeque<>();
		roundBets = new HashMap<>();
		for (int i = 0; i < 5; i++) {
			TreeSet<RoundBettingCard> tree = new TreeSet<RoundBettingCard>();
			tree.add(new RoundBettingCard(CAMELCOLORS.get(i), 5));
			tree.add(new RoundBettingCard(CAMELCOLORS.get(i), 3));
			tree.add(new RoundBettingCard(CAMELCOLORS.get(i), 2));
			roundBets.put(CAMELCOLORS.get(i), tree);
		}

		track = new Track(16, camels);
		curPlayer = players.get(0);
		curPlayerIndex = 0;
		pyramid = new Pyramid(CAMELCOLORS);
	}

	// Accessors
	public Pyramid getPyramid() {
		return pyramid;
	}

	public boolean isCurPlayer(Player p) {
		return p == curPlayer;
	}

	public void commitTurn() {
		if (pyramid.areAllDiceRolled()) {
			pyramid.resetDice();
			track.removeAllDesertCards();
		}

		curPlayerIndex = (curPlayerIndex + 1) % 5;
		curPlayer = players.get(curPlayerIndex);

		for (Player p : players) {
			p.removeDesertCard();
		}
		turnIndex++;
	}
	
	public long getTurnIndex() {
		return turnIndex;
	}

	private void endGame() {

	}

	// Commit Turn Methods
	public void moveCamel() {
		curPlayer.setMoney(curPlayer.getMoney() + 1);
		Die d = pyramid.getDie((int) (Math.random() * pyramid.getNumNotRolledDice()));
		int index = -1;
		for (int i = 0; i < camels.size(); i++) {
			if (camels.get(i).getColor().equals(d.getColor())) {
				index = i;
				break;
			}
		}
		track.moveCamel(camels.get(index), d.getLastRoll());
		this.commitTurn();
	}

	public void placeWinBet(RaceBettingCard c) {
		if (curPlayer.getRaceBets().contains(c)) {
			winBets.add(c);
			this.commitTurn();
		}
	}

	public void placeLoseBet(RaceBettingCard c) {
		if (curPlayer.getRaceBets().contains(c)) {
			loseBets.add(c);
			this.commitTurn();
		}
	}

	public void placeRoundBet(Color c) {
		if (roundBets.containsKey(c)) {
			Player p = this.curPlayer;
			int index = -1;
			for (int i = 0; i < players.size(); i++) // change if no name for player
			{
				if (p.getName().equals(players.get(i).getName())) {
					index = i;
					break;
				}
			}
			players.get(index).addRoundBet(roundBets.get(c).last());

			this.commitTurn();
		}
	}

	public void placeDesertCard(boolean isOasis, int tileNum) {
		if (track.canPlaceCard(tileNum)) {
			curPlayer.setDesertCard(isOasis);
			track.placeDesertCard(curPlayer.getDesertCard().get(), tileNum);
		}
	}
}