package gamestate;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;

import game.Die;
import game.Pyramid;
import immutable.Camel;
import immutable.RaceBettingCard;
import immutable.RoundBettingCard;

public class GameState {
	
	public final static String[] names = { "KarleEngels1820", "Evile Vinciente", "A Confucius", "Prince Zuko",
			"Madame Bob Lee" };

	public final static ImmutableBiMap<String, Color> COLORBIMAP = ImmutableBiMap.of("blue", new Color(51, 153, 255),
			"green", Color.green, "yellow", Color.yellow, "white", Color.white, "orange", Color.orange);
	public final static List<Color> CAMELCOLORS = ImmutableList.copyOf(COLORBIMAP.values());
	
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
		for (int i = 0; i < CAMELCOLORS.size(); i++) {
			camels.add(new Camel(CAMELCOLORS.get(i)));
		}
		
		players = new ArrayList<Player>();

		for (String name : names) {
			players.add(new Player(name, CAMELCOLORS));
		}

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
		curPlayer = players.get(curPlayerIndex);
		pyramid = new Pyramid(CAMELCOLORS);
	}

	// Accessors
	public Pyramid getPyramid() {
		return pyramid;
	}

	public Track getTrack() {
		return track;
	}

	public Queue<RaceBettingCard> getWinBets() {
		return winBets;
	}

	public Queue<RaceBettingCard> getLoseBets() {
		return loseBets;
	}

	public long getTurnIndex() {
		return turnIndex;
	}

	public Player getCurPlayer() {
		return curPlayer;
	}

	public void setCurPlayer(Player curPlayer) {
		this.curPlayer = curPlayer;
	}

	private void commitTurn() {
		if (pyramid.areAllDiceRolled()) {
			pyramid.resetDice();
			track.removeAllDesertCards();
			for (Player p : players) {
				p.removeDesertCard();
			}
		}

		curPlayerIndex = (curPlayerIndex + 1) % 5;
		curPlayer = players.get(curPlayerIndex);

		turnIndex++;
	}

	private void checkAndEndGame() {

	}

	// Commit Turn Methods
	public void moveCamel() {
		curPlayer.setMoney(curPlayer.getMoney() + 1);
		Die d = pyramid.getRandomDie();
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

	public void placeWinBet(Color c) {
		for (RaceBettingCard rbc : curPlayer.getRaceBets()) {
			if (rbc.getColor().equals(c)) {
				curPlayer.removeRaceBet(rbc);
				winBets.add(rbc);
				this.commitTurn();
				break;
			}
		}
	}

	public void placeLoseBet(Color c) {
		for (RaceBettingCard rbc : curPlayer.getRaceBets()) {
			if (rbc.getColor().equals(c)) {
				curPlayer.removeRaceBet(rbc);
				loseBets.add(rbc);
				this.commitTurn();
				break;
			}
		}
	}

	public void placeRoundBet(Color c) {
		if (roundBets.containsKey(c) && !roundBets.get(c).isEmpty()) {
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

	public boolean isGameEnded() {
		return false;
	}
}