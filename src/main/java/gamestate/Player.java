package gamestate;

import immutable.DesertCard;
import immutable.RaceBettingCard;
import immutable.RoundBettingCard;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Player {
	private ArrayList<RaceBettingCard> raceBets;
	private Optional<DesertCard> op;
	private ArrayList<RoundBettingCard> roundBets;
	private int money;
	private String name; // what we use to differentiate players is subject to change and prob should
							// change cause we need to do something that works for graphics and txt

	// Constructor
	public Player(String n, List<Color> colors) {
		raceBets = new ArrayList<>();
		roundBets = new ArrayList<>();
		op = Optional.empty();
		money = 0;
		name = n;
	}

	// hasDesertCard
	public boolean hasDesertCard() {
		return op.isPresent();
	}

	public void removeDesertCard() {
		op = Optional.empty();
	}

	// getDesertCard
	public Optional<DesertCard> getDesertCard() {
		return op;
	}

	public void setDesertCard(boolean isOasis) {
		op = Optional.of(new DesertCard(this, isOasis));
	}

	// addRoundBet
	public void addRoundBet(RoundBettingCard c) {
		roundBets.add(c);
	}

	// setMoney
	public void setMoney(int i) {
		if (i < 0)
			throw new IllegalArgumentException("Money cannot be negative");
		money = i;
	}

	// getRaceBets
	public List<RaceBettingCard> getRaceBets() {
		return Collections.unmodifiableList(raceBets);
	}

	// getRoundBets
	public List<RoundBettingCard> getRoundBets() {
		return Collections.unmodifiableList(roundBets);
	}

	// getMoney
	public int getMoney() {
		return money;
	}

	// clearRoundBets
	public void clearRoundBets() {
		roundBets.clear();
	}

	// get playerName **SUBJECT TO CHANGE**
	public String getName() {
		return name;
	}
}