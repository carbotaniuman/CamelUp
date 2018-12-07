package gamestate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import immutable.Camel;
import immutable.DesertCard;

public class Track {
	private final Tile[] tiles;
	private final Map<Camel, Integer> camelsPos;

	public Track(int size, ArrayList<Camel> camels) {
		tiles = new Tile[size];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new Tile();
		}

		camelsPos = new HashMap<Camel, Integer>();
		for (Camel c : camels) {
			int startPos = ThreadLocalRandom.current().nextInt(1, 4);
			camelsPos.put(c, startPos);
			tiles[startPos].addCamelBot(c);
		}
	}

	public void moveCamel(Camel c, int rolled) {
		int oldPos = camelsPos.get(c);
		int cPosOnTile = tiles[oldPos].getCamelPos(c);
		List<Camel> list = tiles[oldPos].getCamels().subList(0, cPosOnTile + 1);

		Tile newTile = tiles[(oldPos + rolled) % 16];

		if (newTile.getDesertCard().isPresent()) {
			Player trapper = newTile.getDesertCard().get().getPlayer();
			trapper.setMoney(trapper.getMoney() + 1);
			
			if (newTile.getDesertCard().get().getMoveNum() == 1) {
				newTile = tiles[(oldPos + rolled + 1) % 16];

				newTile.addCamelsTop(list);

				for (Camel camel : list) {
					camelsPos.put(camel, oldPos + rolled + 1);
				}
			} else {
				newTile = tiles[(oldPos + rolled - 1) % 16];

				newTile.addCamelsBot(list);

				for (Camel camel : list) {
					camelsPos.put(camel, oldPos + rolled - 1);
				}
			}
		} else {
			newTile.addCamelsTop(list);

			for (Camel camel : list) {
				camelsPos.put(camel, oldPos + rolled);
			}
		}

		tiles[oldPos].removeCamels(list); // Must be last command
	}

	public int getCamelPos(Camel c) {
		return camelsPos.getOrDefault(c, -1);
	}

	public boolean canPlaceCard(int tileNum) {
		if (tileNum <= 0)
			return false;

		if (!tiles[tileNum].getCamels().isEmpty() || tiles[tileNum].getDesertCard().isPresent())
			return false;

		if (tiles[tileNum - 1].getDesertCard().isPresent())
			return false;

		if (tileNum == 15)
			return true;
		
		return !tiles[tileNum + 1].getDesertCard().isPresent();
	}

	public void placeDesertCard(DesertCard d, int tileNum) {
		tiles[tileNum].addDesertCard(d);
	}

	public void removeAllDesertCards() {
		for (int i = 0; i < tiles.length; i++)
			tiles[i].removeDesertCard();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Track:\n");
		for (int i = 0; i < tiles.length; i++) {
			sb.append(i + " " + tiles[i] + "\n");
		}
		return sb.toString();
	}
}
