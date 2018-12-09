package ai;

import java.awt.Color;

import gamestate.GameState;

public class WinBetAction implements IAIAction {
	private Color c;
	
	public WinBetAction(Color c) {
		this.c = c;
	}

	@Override
	public void act(GameState g) {
		g.placeWinBet(c);
	}
}
