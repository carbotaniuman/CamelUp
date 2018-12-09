package ai;

import java.awt.Color;

import gamestate.GameState;

public class LoseBetAction implements IAIAction {
	private Color c;
	
	public LoseBetAction(Color c) {
		this.c = c;
	}

	@Override
	public void act(GameState g) {
		g.placeLoseBet(c);
	}
}
