package gamestate;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.Timer;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;

import ai.AIPlayer;
import ai.actions.AIAction;
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
	private boolean gameStarted, gameEnded;
	private boolean isSleeping;

	private final Set<GameListener> listeners;

	public GameState(boolean... b) {
		camels = new ArrayList<Camel>();
		for (int i = 0; i < CAMELCOLORS.size(); i++) {
			camels.add(new Camel(CAMELCOLORS.get(i)));
		}

		players = new ArrayList<Player>();

		for (int i = 0; i < b.length; i++) {
			if (b[i]) {
				players.add(new AIPlayer(names[i], CAMELCOLORS, this));
			} else {
				players.add(new Player(names[i], CAMELCOLORS));
			}
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

		listeners = new LinkedHashSet<>();
	}

	public void startGame() {
		gameStarted = true;
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				processAITurn();
			}
		});
		timer.setRepeats(false);
		timer.start();
	}

	public void addTurnListener(GameListener tl) {
		listeners.add(tl);
	}

	private void processAITurn() {
		if (curPlayer instanceof AIPlayer) {
			AIAction act = ((AIPlayer) curPlayer).getAction();
			long turn = turnIndex;
			act.act(this);
			if (turnIndex != turn + 1) {
				throw new AssertionError("AI Played on another person's turn");
			}
		}
	}

	public boolean isSleeping() {
		return isSleeping;
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

	public boolean isGameStarted() {
		return gameStarted;
	}

	private void commitTurn() {
		if (pyramid.areAllDiceRolled() || track.hasCamelWon()) {
			pyramid.resetDice();
			track.removeAllDesertCards();
			for (Player p : players) {
				p.removeDesertCard();
			}
			
			for (Player p : players) {
				int winCount = 0;
				int loseCount = 0;
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
				tally += p.getRollCards();
				p.resetRollCards();
				p.clearRoundBets();
				if (track.hasCamelWon()) {
					gameEnded = true;
					int[] amount = { 8, 5, 3, 2, 1 };
					for (RaceBettingCard r : winBets) {
						if (r.getColor().equals(getCamelRankings().get(0).getColor())) {
							if (r.getPlayer().equals(p)) {
								tally += amount[winCount];
							}
							
							if (winCount + 1 != amount.length) {
								winCount++;
							}
						} else if(r.getPlayer().equals(p)) {
							tally--;
						}
					}
					
					for (RaceBettingCard r : loseBets) {
						if (r.getColor().equals(getCamelRankings().get(getCamelRankings().size() - 1).getColor())) {
							if (r.getPlayer().equals(p)) {
								tally += amount[loseCount];
							}
							
							if (loseCount + 1 != amount.length) {	
								loseCount++;
							}
						} else if(r.getPlayer().equals(p)) {
							tally--;
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

		turnIndex++;

		for (GameListener tl : listeners) {
			tl.gameChanged();
		}

		isSleeping = true;
		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				curPlayerIndex = (curPlayerIndex + 1) % players.size();
				curPlayer = players.get(curPlayerIndex);

				for (GameListener tl : listeners) {
					tl.gameChanged();
				}

				if (!gameEnded) {
					Timer timer = new Timer(1000, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							processAITurn();
						}
					});
					timer.setRepeats(false);
					timer.start();
				}
				isSleeping = false;
			}
		});
		timer.setRepeats(false);
		timer.start();

	}

	public List<Camel> getCamelRankings() {
		return track.getCamelRankings();
	}

	// Commit Turn Methods
	public void moveCamel() {
		if (!gameStarted) {
			throw new IllegalStateException("Game has not started");
		}
		if (gameEnded) {
			throw new IllegalStateException("Game has ended");
		}

		curPlayer.giveRollCard();
		Die d = pyramid.getRandomDie();

		track.moveCamel(d.getColor(), d.getLastRoll());

		this.commitTurn();
	}

	public void placeWinBet(Color c) {
		if (!gameStarted) {
			throw new IllegalStateException("Game has not started");
		}
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
		if (!gameStarted) {
			throw new IllegalStateException("Game has not started");
		}
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
		if (!gameStarted) {
			throw new IllegalStateException("Game has not started");
		}
		if (gameEnded) {
			throw new IllegalStateException("Game has ended");
		}

		if (roundBets.containsKey(c) && !roundBets.get(c).isEmpty()) {
			curPlayer.addRoundBet(roundBets.get(c).pollFirst());

			this.commitTurn();
		}
	}

	public void placeDesertCard(boolean isOasis, int tileNum) {
		if (!gameStarted) {
			throw new IllegalStateException("Game has not started");
		}
		if (gameEnded) {
			throw new IllegalStateException("Game has ended");
		}

		if (track.canPlaceCard(tileNum) || (curPlayer.getDesertCard().isPresent()
				&& track.canMoveCard(curPlayer.getDesertCard().get().getTile(), tileNum))) {
			Optional<DesertCard> old = curPlayer.getDesertCard();
			curPlayer.setDesertCard(isOasis, tileNum);
			track.placeDesertCard(old, curPlayer.getDesertCard().get(), tileNum);

			this.commitTurn();
		}
	}

	public void flipDesertCard(int tileNum) {
		if (!gameStarted) {
			throw new IllegalStateException("Game has not started");
		}
		if (gameEnded) {
			throw new IllegalStateException("Game has ended");
		}

		if (curPlayer.getDesertCard().isPresent()) {
			Optional<DesertCard> old = curPlayer.getDesertCard();
			curPlayer.setDesertCard(old.get().getMoveNum() != 1, tileNum);
			track.placeDesertCard(curPlayer.getDesertCard(), curPlayer.getDesertCard().get(), tileNum);

			this.commitTurn();
		}
	}
}