package gamestate;

import java.util.ArrayList;
import java.util.Optional;

import game.Camel;
import immutable.DesertCard;

public class Tile 
{
	private ArrayList<Camel> camels;
	private Optional<DesertCard> trapCard;

	public Tile() 
	{
		super();
		camels = new ArrayList<>();
		trapCard = Optional.empty();
	}

	public boolean addDesertCard(DesertCard d)	// CHANGES added a addDesertCard method because we have no way of adding
	{											// a desert card...
		if (trapCard.isPresent())
			throw new IllegalStateException("DesertCard non existent");
		trapCard = Optional.of(d);
		return true;
	}
	public void addCamel(Camel c) 
	{
		camels.add(c);
	}

	public void removeCamel(Camel c) 
	{
		camels.remove(c);
	}
	public Optional<DesertCard> getDesertCard() 
	{
		return trapCard;
	}
	public void removeDesertCard()
	{
		trapCard = Optional.empty();
	}
}
