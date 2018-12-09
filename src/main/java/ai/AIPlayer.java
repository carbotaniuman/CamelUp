package ai;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import ai.actions.AIAction;
import ai.actions.LoseBetAction;
import ai.actions.RollAction;
import ai.actions.RoundBetAction;
import ai.actions.WinBetAction;
import game.Die;
import game.Pyramid;
import gamestate.GameState;
import gamestate.Player;
import gamestate.Track;
import immutable.Camel;

public class AIPlayer extends Player {
	private GameState g;

	public AIPlayer(String n, List<Color> colors, GameState g) {
		super(n, colors);
		this.g = g;
	}
	
	public AIAction getAction() {
		double eValue = 1;
		AIAction bestAction = new RollAction();

		HashMap<Camel, Integer> roundWin = new HashMap<>();
		HashMap<Camel, Integer> roundSec = new HashMap<>();

		for (int i = 0; i < 1000; i++) {
			GameSimulator rs = new GameSimulator(g.getTrack(), g.getPyramid());
			while (rs.canSimulateRound()) {
				rs.moveCamel();
			}
			List<Camel> rank = rs.getCamelRankings();
			roundWin.put(rank.get(0), roundWin.getOrDefault(rank.get(0), 0) + 1);
			roundSec.put(rank.get(1), roundSec.getOrDefault(rank.get(1), 0) + 1);
		}

//		Map<Color, TreeSet<RoundBettingCard>>
		for (Camel c : roundWin.keySet()) {
			int roundVMax = g.getRoundBets().get(c.getColor()).first().getPoints();

			double chanceFirst = roundWin.get(c) / 1000.0;
			double chanceSecond = roundSec.get(c) / 1000.0;
			double chanceElse = 1 - chanceFirst - chanceSecond;

			double roundV = roundVMax * chanceFirst + 1 * chanceSecond - 1 * chanceElse;
			if (roundV > eValue) {
				eValue = roundV;
				bestAction = new RoundBetAction(c.getColor());
			}
		}

		HashMap<Camel, Integer> gameWin = new HashMap<>();
		HashMap<Camel, Integer> gameLose = new HashMap<>();

		for (int i = 0; i < 1000; i++) {
			GameSimulator rs = new GameSimulator(g.getTrack(), g.getPyramid());
			while (rs.canSimulateGame()) {
				while (rs.canSimulateRound()) {
					rs.moveCamel();
				}
				rs.nextRound();
			}

			List<Camel> rank = rs.getCamelRankings();
			gameWin.put(rank.get(0), roundWin.getOrDefault(rank.get(0), 0) + 1);
			gameLose.put(rank.get(rs.getCamelRankings().size() - 1),
					roundSec.getOrDefault(rank.get(rs.getCamelRankings().size() - 1), 0) + 1);
		}

		for (Camel c : gameWin.keySet()) {
			double chanceWin = gameWin.get(c) / 1000.0;
			double chanceLose = gameLose.get(c) / 1000.0;

			double chanceFirstWinPlace = (1.0 / 625.0) * Math.pow(g.getWinBets().size() - 25, 2);
			double chanceFirstLosePlace = (1.0 / 625.0) * Math.pow(g.getLoseBets().size() - 25, 2);

			double winEV = chanceFirstWinPlace * chanceWin * 8 - 1 * (1 - chanceWin);
			if (winEV > eValue) {
				eValue = winEV;
				bestAction = new WinBetAction(c.getColor());
			}
			
			double loseEv = chanceFirstLosePlace * chanceLose * 8 - 1 * (1 - chanceLose);
			if (loseEv > eValue) {
				eValue = loseEv;
				bestAction = new LoseBetAction(c.getColor());
			}
		}

		return bestAction;
	}

	private static class GameSimulator {
		private Track track;
		private Pyramid pyramid;

		public GameSimulator(Track t, Pyramid p) {
			track = new AITrack(t);
			pyramid = new Pyramid(p);
		}

		public void moveCamel() {
			Die d = pyramid.getRandomDie();

			track.moveCamel(d.getColor(), d.getLastRoll());
		}

		public boolean canSimulateRound() {
			return !pyramid.areAllDiceRolled();
		}

		public boolean canSimulateGame() {
			return !track.hasCamelWon();
		}

		public List<Camel> getCamelRankings() {
			return track.getCamelRankings();
		}

		public void nextRound() {
			track.removeAllDesertCards();
			pyramid.resetDice();
		}
	}
}
