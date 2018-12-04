package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Pyramid {
	private final ArrayList<Die> notRolledDice;
	private final ArrayList<Die> rolledDice;

	public Pyramid(List<Color> colors) {
		if(colors.size() < 1) {
			throw new IllegalArgumentException("At least one die needed");
		}
		notRolledDice = new ArrayList<Die>();
		rolledDice = new ArrayList<Die>();
		for (Color c : colors)
			notRolledDice.add(new Die(c));
		resetDice();
	}

	public Die getDie(int i) {
		Die ret = notRolledDice.get(i);
		rolledDice.add(notRolledDice.get(i));
		notRolledDice.remove(i);
		return ret;
	}

	public boolean areAllDiceRolled() {
		return notRolledDice.isEmpty();
	}

	public void resetDice() {
		notRolledDice.addAll(rolledDice);
		rolledDice.clear();
		notRolledDice.forEach(Die::roll);
	}

	public int getNumNotRolledDice() {
		return notRolledDice.size();
	}
}