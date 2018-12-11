package gamestate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import ai.LockableList;
import immutable.Camel;
import immutable.DesertCard;

public class Tile {
	private final LockableList<Camel> camels;
	private Optional<DesertCard> trapCard;

	public Tile() {
		camels = new LockableList<>(new ArrayList<>());
		trapCard = Optional.empty();
	}

	// AI Constructor
	public Tile(Tile t) {
		camels = new LockableList<>(new ArrayList<>(t.camels));
		trapCard = t.trapCard;
	}

	public void addDesertCard(DesertCard d) {
		if (trapCard.isPresent())
			throw new IllegalStateException("DesertCard exists!");
		trapCard = Optional.of(d);
	}

	public void addCamelTop(Camel c) {
		camels.add(0, c);
	}

	public void addCamelBot(Camel c) {
		camels.add(c);
	}

	public void addCamelsTop(List<Camel> c) {
		camels.addAll(0, c);
	}

	public void addCamelsBot(List<Camel> c) {
		camels.addAll(c);
	}

	public void removeCamel(Camel c) {
		camels.remove(c);
	}

	public void removeCamels(List<Camel> c) {
		camels.removeAll(c);
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

	// -1 no exist, else 0 is top of stack
	public int getCamelStackPos(Camel c) {
		return camels.indexOf(c);
	}

	public String toString() {
		if (trapCard.isPresent()) {
			return "[" + trapCard.get().toString() + "]";
		} else {
			return camels.toString();
		}
	}
}
