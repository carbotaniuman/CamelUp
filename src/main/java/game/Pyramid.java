package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Pyramid {
	private final List<Die> notRolledDice;
	private final List<Die> rolledDice;

	private Die lastDie;

	public Pyramid(List<Color> colors) {
		if (colors.size() < 1) {
			throw new IllegalArgumentException("At least one die needed");
		}
		notRolledDice = new ArrayList<Die>();
		rolledDice = new ArrayList<Die>();
		for (Color c : colors)
			notRolledDice.add(new Die(c));
		resetDice();
	}

	public Pyramid(Pyramid p) {
		notRolledDice = p.notRolledDice.stream().map(Die::new).collect(Collectors.toList());
		rolledDice = p.rolledDice.stream().map(Die::new).collect(Collectors.toList());
	}

	public Die getRandomDie() {
		lastDie = notRolledDice.get(ThreadLocalRandom.current().nextInt(notRolledDice.size()));
		rolledDice.add(lastDie);
		notRolledDice.remove(lastDie);
		return lastDie;
	}

	public Die getLastDie() {
		return lastDie;
	}

	public boolean areAllDiceRolled() {
		return notRolledDice.isEmpty();
	}

	public void resetDice() {
		notRolledDice.addAll(rolledDice);
		rolledDice.clear();
		notRolledDice.forEach(Die::roll);
	}

	public List<Die> getDiceNotRolled() {
		return notRolledDice;
	}

	public List<Die> getRolledDice() {
		return rolledDice;
	}
}