package ai.actions;

import java.awt.Color;

import gamestate.GameState;

public class LoseBetAction implements AIAction {
	private Color c;
	
	public LoseBetAction(Color c) {
		this.c = c;
	}

	@Override
	public void act(GameState g) {
		g.placeLoseBet(c);
	}
	
	public String toString() {
		return "LoseBet: " + GameState.COLORBIMAP.inverse().get(c);
	}
}
