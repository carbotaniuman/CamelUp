package ai;

import java.awt.Color;
import java.util.List;

import gamestate.Tile;
import gamestate.Track;
import immutable.Camel;

public class AITrack extends Track {

	public AITrack(Track t) {
		super(t);
	}
	
	public void moveCamel(Color color, int rolled) {
		Camel c = camels.get(color);

		int oldPos = camelPos.get(c);
		int cPosOnTile = tiles[oldPos].getCamelStackPos(c);
		List<Camel> list = tiles[oldPos].getCamels().subList(0, cPosOnTile + 1);

		Tile newTile = tiles[(oldPos + rolled) % 16];

		if (newTile.getDesertCard().isPresent()) {
			if (newTile.getDesertCard().get().getMoveNum() == 1) {
				newTile = tiles[(oldPos + rolled + 1) % 16];

				newTile.addCamelsTop(list);

				for (Camel camel : list) {
					camelPos.put(camel, oldPos + rolled + 1);
				}
			} else {
				newTile = tiles[(oldPos + rolled - 1) % 16];

				newTile.addCamelsBot(list);

				for (Camel camel : list) {
					camelPos.put(camel, oldPos + rolled - 1);
				}
			}
		} else {
			newTile.addCamelsTop(list);

			for (Camel camel : list) {
				camelPos.put(camel, oldPos + rolled);
			}
		}

		tiles[oldPos].removeCamels(list); // Must be last command
	}
}
