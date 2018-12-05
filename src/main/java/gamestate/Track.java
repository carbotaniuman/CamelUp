package gamestate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import immutable.Camel;
import immutable.DesertCard;

public class Track {
	private final Tile[] tiles;
	private final Map<Camel, Integer> camelsPos;

	public Track(int size, ArrayList<Camel> camels) // CHANGES passed in arraylist of camels to intilize in the
	{	
		tiles = new Tile[size];
		for(int i = 0; i < tiles.length; i++) {
			tiles[i] = new Tile();
		}
		
		camelsPos = new HashMap<Camel, Integer>();
		for (int i = 0; i < camels.size(); i++)
			camelsPos.put(camels.get(i), 0);
	}

	public void moveCamel(Camel c, int rolled) // CHANGES dont need oldPos and newPos because we can pass in siply why
												// was rolled for that camel
	{
		int oldPos = camelsPos.getOrDefault(c, -1);
		tiles[oldPos].removeCamel(c);
		camelsPos.put(c, oldPos + rolled);
		tiles[oldPos + rolled].addCamel(c);
	}

	public int getCamelPos(Camel c) {
		return camelsPos.getOrDefault(c, -1);
	}
	
	public boolean canPlaceCard(int tileNum) {
		if(tileNum == 0) 
			return false;
		
		if(!tiles[tileNum].getCamels().isEmpty()) 
			return false;
		
		if(!tiles[tileNum - 1].getCamels().isEmpty()) 
			return false;
		
		if(tileNum == 16)
			return true;
		else return tiles[tileNum + 1].getCamels().isEmpty();
	}

	public void placeDesertCard(DesertCard d, int tileNum) {
		tiles[tileNum].addDesertCard(d);
	}

	public void removeAllDesertCards() {
		for (int i = 0; i < tiles.length; i++)
			tiles[i].removeDesertCard();
	}
}
