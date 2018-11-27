package game;

import java.awt.Color;
import java.util.Random;

public class Die {

	private Random rand;
	private Color camelColor;
	private boolean ifRolled;
	private int lastRoll;

	public Die(Color c) {
		camelColor = c;
		rand = new Random();
	}

	public Die(Color c, int seed) {
		camelColor = c;
		rand = new Random(seed);
	}

	public void roll() {
		lastRoll = rand.nextInt(3) + 1;
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
}
