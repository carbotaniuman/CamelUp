package game;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

import gamestate.GameState;

public class Die {

	private final Color camelColor;
	private boolean ifRolled;
	private int lastRoll;

	public Die(Color c) {
		camelColor = c;
	}
	
	//AI Constructor
	public Die(Die d) {
		camelColor = d.camelColor;
		ifRolled = d.ifRolled;
		lastRoll = d.lastRoll;
	}

	public void roll() {
		lastRoll = ThreadLocalRandom.current().nextInt(1, 4);
		ifRolled = true;
	}
	
	public Color getColor() {
		return camelColor;
	}

	public int getLastRoll() {
		return lastRoll;
	}

	public boolean getIfRolled() {
		return ifRolled;
	}
	public void resetIfRolled()
	{
		ifRolled = false;
	}
}
