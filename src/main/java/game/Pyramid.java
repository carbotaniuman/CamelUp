package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class Pyramid {
	private static final Color[] diceColors = { Color.WHITE, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE };
	private ArrayList<Die> notRolledDice;
	private ArrayList<Die> rolledDice;

	public Pyramid() {
		notRolledDice = new ArrayList<Die>();
		rolledDice = new ArrayList<Die>();
		for (int i = 0; i < diceColors.length; i++)
			notRolledDice.add(new Die(diceColors[i]));
		resetDice();
	}

	public Die getDie(int i) {
		Die ret = notRolledDice.get(i);
		rolledDice.add(notRolledDice.get(i));
		notRolledDice.remove(i);
		return ret;
	}

	public boolean areAllDiceRolled() {
		Iterator<Die> iter = notRolledDice.iterator();
		// return notRolledDice.stream().map(d -> d.getIfRolled()).filter(n -> n ==
		// false).count() == 0;
		while (iter.hasNext())
			if (iter.next().getIfRolled() == false)
				return false;
		return true;
	}

	public void resetDice() {
		int i = 0;
		while (rolledDice.size() > 0)
			notRolledDice.add(rolledDice.get(i++));
		Iterator<Die> iter = notRolledDice.iterator();
		while (iter.hasNext())
			iter.next().roll();
	}

	public int getNumNotRolledDice() {
		return notRolledDice.size();
	}
}