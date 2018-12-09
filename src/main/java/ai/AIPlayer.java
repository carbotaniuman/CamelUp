package ai;

import java.awt.Color;
import java.util.List;

import gamestate.GameState;
import gamestate.Player;

public class AIPlayer extends Player {
	private GameState g;
	public AIPlayer(String n, List<Color> colors, GameState g) {
		super(n, colors);
		this.g = g;
	}
	
	public void processMove() {
		
	}
	
	private void getAction() {
		
	}
}
