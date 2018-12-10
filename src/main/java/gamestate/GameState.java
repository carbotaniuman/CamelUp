package gamestate;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.TreeSet;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;

import game.Die;
import game.Pyramid;
import immutable.Camel;
import immutable.DesertCard;
import immutable.RaceBettingCard;
import immutable.RoundBettingCard;

public class GameState {

	public final static String[] names = { "KarleEngels18", "Evile Vinciente", "A Confucius", "Prince Zuko",
			"Madame Bob Lee", "Sg2themax", "DaLegend27", "Chrometor" };

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
	private boolean gameEnded;

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
			tree.add(new RoundBettingCard(CAMELCOLORS.get(i), 1));
			roundBets.put(CAMELCOLORS.get(i), tree);
		}

		track = new Track(16, camels);
		curPlayer = players.get(curPlayerIndex);
		pyramid = new Pyramid(CAMELCOLORS);
	}

	public Pyramid getPyramid() {
		return pyramid;
	}

	public Track getTrack() {
		return track;
	}

	public Map<Color, TreeSet<RoundBettingCard>> getRoundBets() {
		return Collections.unmodifiableMap(roundBets);
	}

	public Queue<RaceBettingCard> getWinBets() {
		return new ArrayDeque<>(winBets);
	}

	public Queue<RaceBettingCard> getLoseBets() {
		return new ArrayDeque<>(loseBets);
	}

	public long getTurnIndex() {
		return turnIndex;
	}

	public Player getCurPlayer() {
		return curPlayer;
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	public boolean isGameEnded() {
		return gameEnded;
	}

	private void commitTurn() {
		if (pyramid.areAllDiceRolled()) {
			pyramid.resetDice();
			track.removeAllDesertCards();
			for (Player p : players) {
				p.removeDesertCard();
			}
			for (Player p : players) {
				int tally = 0;
				for (RoundBettingCard rbc : p.getRoundBets()) {
					if (rbc.getColor().equals(getCamelRankings().get(0).getColor())) {
						tally += rbc.getPoints();
					} else if (rbc.getColor().equals(getCamelRankings().get(1).getColor())) {
						tally += 1;
					} else {
						tally -= 1;
					}
					roundBets.get(rbc.getColor()).add(rbc);
				}
				p.clearRoundBets();
				if (track.hasCamelWon()) {
					gameEnded = true;
					int[] amount = { 5, 3, 2, 1 };
					int count = 0;
					for (RaceBettingCard r : this.winBets) {
						if (r.getColor().equals(this.getCamelRankings().get(0).getColor())) {
							tally = amount[count];
							if(count + 1 != amount.length) {
								count++;
							}
						}
					}
				}
				if (p.getMoney() + tally < 0) {
					p.setMoney(0);
				} else {
					p.setMoney(p.getMoney() + tally);
				}
			}
		}

		curPlayerIndex = (curPlayerIndex + 1) % players.size();
		curPlayer = players.get(curPlayerIndex);

		turnIndex++;

		
	}

	public List<Camel> getCamelRankings() {
		return track.getCamelRankings();
	}

	// Commit Turn Methods
	public void moveCamel() {
		if (gameEnded) {
			throw new IllegalStateException("Game has ended");
		}

		curPlayer.giveRollCard();
		Die d = pyramid.getRandomDie();

		track.moveCamel(d.getColor(), d.getLastRoll());
		this.commitTurn();
	}

	public void placeWinBet(Color c) {
		if (gameEnded) {
			throw new IllegalStateException("Game has ended");
		}

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
		if (gameEnded) {
			throw new IllegalStateException("Game has ended");
		}

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
		if (gameEnded) {
			throw new IllegalStateException("Game has ended");
		}

		if (roundBets.containsKey(c) && !roundBets.get(c).isEmpty()) {
			curPlayer.addRoundBet(roundBets.get(c).pollFirst());

			this.commitTurn();
		}
	}

	public void placeDesertCard(boolean isOasis, int tileNum) {
		if (gameEnded) {
			throw new IllegalStateException("Game has ended");
		}

		System.out.println(tileNum + " " + track.canPlaceCard(tileNum));
		if (track.canPlaceCard(tileNum)) {
			Optional<DesertCard> old = curPlayer.getDesertCard();
			curPlayer.setDesertCard(isOasis, tileNum);
			track.placeDesertCard(old, curPlayer.getDesertCard().get(), tileNum);

			this.commitTurn();
		}
	}
}