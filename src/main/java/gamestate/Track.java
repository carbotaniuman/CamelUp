package gamestate;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import game.Camel;
import immutable.DesertCard;

public class Track 
{
	private Tile[] tiles;
	private Map<Camel, Integer> camelsPos;

	public Track(int size, ArrayList<Camel> camels) // CHANGES passed in arraylist of camels to intilize in the
	{												// beggining of track
		tiles = new Tile[size];
		camelsPos = new TreeMap<Camel, Integer>();

		for (int i = 0; i < camels.size(); i++)
			camelsPos.put(camels.get(i), 0);
	}

	public void moveCamel(Camel c, int rolled) // CHANGES dont need oldPos and newPos because we can pass in siply why was rolled for that camel
	{
		int oldPos = camelsPos.getOrDefault(c, -1);
		tiles[oldPos].removeCamel(c);
		camelsPos.put(c, oldPos + rolled);
		tiles[oldPos + rolled].addCamel(c);
	}

	public int getCamelPos(Camel c) 
	{
		return camelsPos.getOrDefault(c, -1);
	}
	public void placeDesertCard ( DesertCard d, int tileNum )
	{
		tiles[tileNum].addDesertCard(d);
	}
	public void removeAllDesertCards()
	{
		for(int i = 0; i < tiles.length; i++)
			tiles[i].removeDesertCard();
	}
}
