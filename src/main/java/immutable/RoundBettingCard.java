package immutable;

import java.awt.*;

public class RoundBettingCard {

    private final Color camelColor;
    private final int points;

    public RoundBettingCard(Color c, int p) {
    	camelColor = c;
    	points = p;
    }

    public Color getCamelColor() {
        return camelColor;
    }

    public int getPoints() {
        return points;
    }
}
