package ai.actions;

import gamestate.GameState;

@FunctionalInterface
public interface AIAction {
	void act(GameState g);
}
