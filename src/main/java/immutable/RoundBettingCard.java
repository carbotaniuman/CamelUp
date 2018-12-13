package immutable;

import java.awt.Color;

import gamestate.GameState;

public class RoundBettingCard implements Comparable<RoundBettingCard> {
	private final Color color;
	private final int points;

	public RoundBettingCard(Color c, int p) {
		color = c;
		points = p;
	}

	public Color getColor() {
		return color;
	}

	public int getPoints() {
		return points;
	}

	public String toString() {
		return "RoundBettingCard: " + points + " " + GameState.COLORBIMAP.inverse().get(color);
	}

	@Override
	public int compareTo(RoundBettingCard rbc) {
		return Integer.compare(rbc.getPoints(), points);
	}
}
