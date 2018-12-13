package ai.actions;

import gamestate.GameState;

public class RollAction implements AIAction {

	@Override
	public void act(GameState g) {
		g.moveCamel();
	}

	public String toString() {
		return "Roll";
	}
}
