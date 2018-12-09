package ai;

import gamestate.GameState;

public class RollAction implements IAIAction {

	@Override
	public void act(GameState g) {
		g.moveCamel();
	}
}
