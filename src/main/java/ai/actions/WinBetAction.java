package ai.actions;

import java.awt.Color;

import gamestate.GameState;

public class WinBetAction implements AIAction {
	private Color c;
	
	public WinBetAction(Color c) {
		this.c = c;
	}

	@Override
	public void act(GameState g) {
		g.placeWinBet(c);
	}
}
