package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Pyramid {
	private ArrayList<Die> notRolledDice;
	private ArrayList<Die> rolledDice;

	public Pyramid(List<Color> colors) {
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