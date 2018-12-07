package immutable;

import java.awt.Color;

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

	@Override
	public int compareTo(RoundBettingCard rbc) {
		return Integer.compare(rbc.getPoints(), points);
	}
}
