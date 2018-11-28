package game;

import java.util.ArrayList;
import java.util.Iterator;

public class Pyramid {
	private ArrayList<Die> notRolledDice;
	private ArrayList<Die> rolledDice;
	public Pyramid() {
		resetDice();
	}

	public int getDieVal(int i) {
		int ret = notRolledDice.get(i).getLastRoll();
		rolledDice.add(notRolledDice.get(i));
		notRolledDice.remove(i);
		return ret;
	}

	public boolean areAllDiceRolled() {
		Iterator<Die> iter = notRolledDice.iterator();
		//return notRolledDice.stream().map(d -> d.getIfRolled()).filter(n -> n == false).count() == 0;
		while (iter.hasNext())
			if (iter.next().getIfRolled() == false)
				return false;
		return true;

	}

	public void resetDice() {
		Iterator<Die> iter = notRolledDice.iterator();
		while (iter.hasNext()) 
			iter.next().roll();
	}
	public int getNumNotRolledDice()
	{
		return notRolledDice.size();
	}
}