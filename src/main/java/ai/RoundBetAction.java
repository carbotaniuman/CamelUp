package ai;

import java.awt.Color;

import gamestate.GameState;

public class RoundBetAction implements IAIAction {
	private Color c;
	
	public RoundBetAction(Color c) {
		this.c = c;
	}

	@Override
	public void act(GameState g) {
		g.placeRoundBet(c);
	}
}
