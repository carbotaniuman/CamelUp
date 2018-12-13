package gamestate;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import immutable.Camel;
import immutable.DesertCard;

public class Track {
	static class CamelComparator implements Comparator<Camel> {
		private final Map<Camel, Integer> camelPos;
		private final Tile[] tiles;

		public CamelComparator(Map<Camel, Integer> camelPos, Tile[] tiles) {
			this.camelPos = camelPos;
			this.tiles = tiles;
		}

		@Override
		public int compare(Camel c1, Camel c2) {
			if (camelPos.get(c1) != camelPos.get(c2)) {
				return Integer.compare(camelPos.get(c2), camelPos.get(c1));
			}

			return Integer.compare(tiles[camelPos.get(c1) % 16].getCamelStackPos(c1),
					tiles[camelPos.get(c2) % 16].getCamelStackPos(c2));
		}
	}

	protected final Tile[] tiles;
	protected final Map<Camel, Integer> camelPos;
	protected final Map<Color, Camel> camels;
	protected final CamelComparator compare;

	public Track(int size, ArrayList<Camel> camelList) {
		tiles = new Tile[size];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new Tile();
		}

		camelPos = new HashMap<>();
		camels = new HashMap<>();

		for (Camel c : camelList) {
			camels.put(c.getColor(), c);
			int startPos = ThreadLocalRandom.current().nextInt(0, 3);
			camelPos.put(c, startPos);
			tiles[startPos].addCamelBot(c);
		}

		compare = new CamelComparator(camelPos, tiles);
	}

	public Tile getTile(int i) {
		return tiles[i];
	}

	// AI Constructor
	protected Track(Track t) {
		tiles = new Tile[t.tiles.length];

		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new Tile(t.tiles[i]);
		}

		camelPos = new HashMap<>(t.camelPos);
		camels = new HashMap<>(t.camels);

		compare = new CamelComparator(camelPos, tiles);
	}

	public List<Camel> getCamelRankings() {
		List<Camel> rank = new ArrayList<>(camels.values());
		rank.sort(compare);

		return rank;
	}

	public boolean hasCamelWon() {
		for (Map.Entry<Camel, Integer> entry : camelPos.entrySet()) {
			if (entry.getValue() > 15) {
				return true;
			}
		}
		return false;
	}

	public void moveCamel(Color color, int rolled) {
		Camel c = camels.get(color);

		int oldPos = camelPos.get(c);
		int cPosOnTile = tiles[oldPos % 16].getCamelStackPos(c);
		List<Camel> list = new ArrayList<>(tiles[oldPos % 16].getCamels().subList(0, cPosOnTile + 1));

		Tile newTile = tiles[(oldPos + rolled) % 16];

		if (newTile.getDesertCard().isPresent()) {
			Player trapper = newTile.getDesertCard().get().getPlayer();
			trapper.setMoney(trapper.getMoney() + 1);

			if (newTile.getDesertCard().get().getMoveNum() == 1) {
				newTile = tiles[(oldPos + rolled + 1) % 16];

				newTile.addCamelsTop(list);

				for (Camel camel : list) {
					camelPos.put(camel, oldPos + rolled + 1);
				}
				tiles[oldPos % 16].removeCamels(list);
			} else {
				if (rolled == 1) {
					newTile = tiles[oldPos % 16];
					tiles[oldPos % 16].removeCamels(list);
					newTile.addCamelsBot(list);
				} else {
					newTile = tiles[(oldPos + rolled - 1) % 16];

					newTile.addCamelsBot(list);

					for (Camel camel : list) {
						camelPos.put(camel, oldPos + rolled - 1);
					}
					tiles[oldPos % 16].removeCamels(list);
				}
			}

		} else {
			newTile.addCamelsTop(list);

			for (Camel camel : list) {
				camelPos.put(camel, oldPos + rolled);
			}
			tiles[oldPos % 16].removeCamels(list);
		}
	}

	public int getCamelPos(Camel c) {
		return camelPos.getOrDefault(c, -1);
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

	public boolean canMoveCard(int origNum, int newNum) {
		if (newNum <= 0)
			return false;

		if (!tiles[newNum].getCamels().isEmpty() || tiles[newNum].getDesertCard().isPresent())
			return false;

		if (newNum - 1 == origNum)
			return true;

		if (tiles[newNum - 1].getDesertCard().isPresent())
			return false;

		if (newNum == 15)
			return true;

		if (newNum + 1 == origNum) {
			return true;
		} else {
			return !tiles[newNum + 1].getDesertCard().isPresent();
		}
	}

	public void placeDesertCard(Optional<DesertCard> old, DesertCard d, int tileNum) {
		if (old.isPresent()) {
			tiles[old.get().getTile()].removeDesertCard();
		}

		tiles[tileNum].addDesertCard(d);
	}

	public void removeAllDesertCards() {
		for (Tile t : tiles) {
			t.getDesertCard().ifPresent(c -> c.getPlayer().getDesertCard());
			t.removeDesertCard();
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tiles.length; i++) {
			sb.append(i);
			sb.append(" ");
			sb.append(tiles[i]);
			if (i != tiles.length - 1) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}
}
