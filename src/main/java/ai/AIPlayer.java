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
import immutable.RaceBettingCard;

public class AIPlayer extends Player {
	private GameState g;

	public AIPlayer(String n, List<Color> colors, GameState g) {
		super(n, colors);
		this.g = g;
	}

	public AIAction getAction() {
		double eValue = 1;
		AIAction bestAction = new RollAction();

		HashMap<Color, Integer> roundWin = new HashMap<>();
		HashMap<Color, Integer> roundSec = new HashMap<>();

		for (int i = 0; i < 1000; i++) {
			GameSimulator rs = new GameSimulator(g.getTrack(), g.getPyramid());
			while (rs.canSimulateRound()) {
				rs.moveCamel();
			}
			List<Camel> rank = rs.getCamelRankings();
			roundWin.put(rank.get(0).getColor(), roundWin.getOrDefault(rank.get(0).getColor(), 0) + 1);
			roundSec.put(rank.get(1).getColor(), roundSec.getOrDefault(rank.get(1).getColor(), 0) + 1);
		}
		
		for (Color c : roundWin.keySet()) {
			if (!g.getRoundBets().get(c).isEmpty()) {
				int roundVMax = g.getRoundBets().get(c).first().getPoints();

				double chanceFirst = roundWin.getOrDefault(c, 0) / 1000.0;
				double chanceSecond = roundSec.getOrDefault(c, 0) / 1000.0;
				double chanceElse = 1 - chanceFirst - chanceSecond;
				double roundV = (roundVMax * chanceFirst + 1 * chanceSecond - 1 * chanceElse)
						* dieRolledRoundMult(g.getPyramid().getRolledDice().size());
				if (roundV > eValue) {
					eValue = roundV;
					bestAction = new RoundBetAction(c);
				}
			}
		}
		
		HashMap<Color, Integer> gameWin = new HashMap<>();
		HashMap<Color, Integer> gameLose = new HashMap<>();

		for (int i = 0; i < 1000; i++) {
			GameSimulator rs = new GameSimulator(g.getTrack(), g.getPyramid());
			while (rs.canSimulateGame()) {
				while (rs.canSimulateRound()) {
					rs.moveCamel();
				}
				rs.nextRound();
			}

			List<Camel> rank = rs.getCamelRankings();
			gameWin.put(rank.get(0).getColor(), roundWin.getOrDefault(rank.get(0).getColor(), 0) + 1);
			gameLose.put(rank.get(rs.getCamelRankings().size() - 1).getColor(),
					roundSec.getOrDefault(rank.get(rs.getCamelRankings().size() - 1).getColor(), 0) + 1);
		}
		
		for (RaceBettingCard rbc : getRaceBets()) {
			Color c = rbc.getColor();
			double chanceWin = gameWin.getOrDefault(c, 0) / 1000.0;
			double chanceLose = gameLose.getOrDefault(c, 0) / 1000.0;

			double chanceFirstWinPlace = (1.0 / 625.0) * Math.pow(g.getWinBets().size() - 25, 2);
			double chanceFirstLosePlace = (1.0 / 625.0) * Math.pow(g.getLoseBets().size() - 25, 2);
			
			double winEV = (chanceFirstWinPlace * chanceWin * 8
					- 1 * (1 - chanceWin)) * raceWinMult(g.getTrack().getCamelPos(g.getCamelRankings().get(0)));
			if (winEV > eValue) {
				eValue = winEV;
				bestAction = new WinBetAction(c);
			}

			double loseEv = (chanceFirstLosePlace * chanceLose * 8 - 1 * (1 - chanceLose))
					* raceLoseMult(g.getTrack().getCamelPos(g.getCamelRankings().get(g.getCamelRankings().size() - 2)),
							g.getTrack().getCamelPos(g.getCamelRankings().get(g.getCamelRankings().size() - 1)));
			if (loseEv > eValue) {
				eValue = loseEv;
				bestAction = new LoseBetAction(c);
			}
		}
		return bestAction;
	}

	public double dieRolledRoundMult(int i) {
		switch (i) {
		case 0:
			return 0.25;
		case 1:
			return 0.7;
		case 2:
			return 0.8;
		case 3:
			return 0.95;
		case 4:
			return 0.9;
		case 5:
			return 1;
		default:
			throw new IllegalArgumentException("0 - 5 dice rolled");
		}
	}

	public double raceWinMult(int firstCamelPos) {
		return Math.pow((firstCamelPos - 4)/ 9.0, 2);
	}

	public double raceLoseMult(int secondToLastCamelPos, int lastCamelPos) {
		return Math.pow(Math.min(1, (secondToLastCamelPos - lastCamelPos) / 6.0), 2);
	}

	private static class GameSimulator {
		private AITrack track;
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
