package ai.actions;

import java.awt.Color;

import gamestate.GameState;

public class RoundBetAction implements AIAction {
	private Color c;
	
	public RoundBetAction(Color c) {
		this.c = c;
	}

	@Override
	public void act(GameState g) {
		g.placeRoundBet(c);
	}
	
	public String toString() {
		return "RoundBet: " + GameState.COLORBIMAP.inverse().get(c);
	}
}
