package immutable;

import java.awt.Color;

import gamestate.Player;

public class RaceBettingCard {
	private Color color;
	private Player myPlayer;

	public RaceBettingCard(Color color, Player myPlayer) {
		super();
		this.color = color;
		this.myPlayer = myPlayer;
	}

	public Color getColor() {
		return color;
	}

	public Player getMyPlayer() {
		return myPlayer;
	}

}
