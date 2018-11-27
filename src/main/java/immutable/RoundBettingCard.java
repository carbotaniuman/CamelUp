package immutable;

import java.awt.*;

public class RoundBettingCard {

    private Color camelColor;
    private int points;

    public RoundBettingCard(Color c, int p) {
        setCamelColor(c);
        setPoints(p);
    }

    public Color getCamelColor() {
        return camelColor;
    }

    public void setCamelColor(Color camelColor) {
        this.camelColor = camelColor;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
