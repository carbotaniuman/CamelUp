package game;

import java.util.ArrayList;
import java.util.Iterator;

public class Pyramid {
	private ArrayList<Die> dice;

	public Pyramid() {
		resetDice();
	}

	public Die getDie(int i) {
		return dice.get(i);
	}

	public boolean areAllDiceRolled() {
		Iterator<Die> iter = dice.iterator();
//		return dice.stream().map(d -> d.getIfRolled()).filter(n -> n == false).count() == 0;
		while (iter.hasNext()) {
			if (iter.next().getIfRolled() == false)
				return false;
		}
		return true;

	}

	public void resetDice() {
		Iterator<Die> iter = dice.iterator();
		while (iter.hasNext()) {
			iter.next().roll();
		}
	}
}