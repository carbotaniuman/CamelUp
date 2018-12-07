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
	private final ArrayList<RaceBettingCard> raceBets;
	private final ArrayList<RoundBettingCard> roundBets;
	private Optional<DesertCard> op;
	private final String name;

	private int money, rollCards;

	public Player(String n, List<Color> colors) {
		raceBets = new ArrayList<>();
		for (int i = 0; i < 5; i++)
			raceBets.add(new RaceBettingCard(GameState.CAMELCOLORS.get(i), this));
		roundBets = new ArrayList<>();
		op = Optional.empty();
		name = n;
	}

	public void removeDesertCard() {
		op = Optional.empty();
	}

	public Optional<DesertCard> getDesertCard() {
		return op;
	}

	public void setDesertCard(boolean isOasis) {
		op = Optional.of(new DesertCard(this, isOasis));
	}

	public void addRoundBet(RoundBettingCard c) {
		roundBets.add(c);
	}
	
	public int getRollCards() {
		return rollCards;
	}
	
	public void giveRollCard() {
		rollCards++;
		money++;
	}
	
	public void resetRollCards() {
		rollCards = 0;
	}

	public void setMoney(int i) {
		if (i < 0)
			throw new IllegalArgumentException("Money cannot be negative");
		money = i;
	}

	public List<RaceBettingCard> getRaceBets() {
		return Collections.unmodifiableList(raceBets);
	}

	public List<RoundBettingCard> getRoundBets() {
		return Collections.unmodifiableList(roundBets);
	}

	public int getMoney() {
		return money;
	}

	public void clearRoundBets() {
		roundBets.clear();
	}

	public void removeRaceBet(RaceBettingCard r) {
		raceBets.remove(r);
	}

	public String getName() {
		return name;
	}
}