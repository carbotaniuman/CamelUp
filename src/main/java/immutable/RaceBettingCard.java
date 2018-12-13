package immutable;

import java.awt.Color;

import gamestate.GameState;
import gamestate.Player;

public class RaceBettingCard {
	private final Color color;
	private final Player myPlayer;

	public RaceBettingCard(Color color, Player myPlayer) {
		this.color = color;
		this.myPlayer = myPlayer;
	}

	public Color getColor() {
		return color;
	}

	public Player getPlayer() {
		return myPlayer;
	}

	public String toString() {
		return "RaceBettingCard: " + myPlayer.getName() + " " + GameState.COLORBIMAP.inverse().get(color);
	}
}
