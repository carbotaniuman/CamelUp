package gamestate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import immutable.Camel;
import immutable.DesertCard;

public class Tile {
	private final ArrayList<Camel> camels;
	private Optional<DesertCard> trapCard;

	public Tile() {
		super();
		camels = new ArrayList<>();
		trapCard = Optional.empty();
	}

	public boolean addDesertCard(DesertCard d) {
		if (trapCard.isPresent())
			throw new IllegalStateException("DesertCard exists!");
		trapCard = Optional.of(d);
		return true;
	}

	public void addCamel(Camel c) {
		camels.add(c);
	}
	
	public void addCamelTop(Camel c) {
		camels.add(0, c);
	}
	
	public void addCamels(ArrayList<Camel> c) {
		camels.addAll(c);
	}
	
	public void addCamelsTop(ArrayList<Camel> c) {
		camels.addAll(0, c);
	}

	public void removeCamel(Camel c) {
		camels.remove(c);
	}
	
	public List<Camel> getCamels() {
		return Collections.unmodifiableList(camels);
	}

	public Optional<DesertCard> getDesertCard() {
		return trapCard;
	}

	public void removeDesertCard() {
		trapCard = Optional.empty();
	}
	
	//-1 no exist, else 0 is top of stack
	public int getCamelPos(Camel c) {
		return camels.indexOf(c);
	}
}
