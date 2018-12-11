package ai;

import java.awt.Color;
import java.util.List;
import java.util.Map;

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
		int cPosOnTile = tiles[oldPos % 16].getCamelStackPos(c);
		List<Camel> list = tiles[oldPos % 16].getCamels().subList(0, cPosOnTile + 1);
		System.out.println("!!!!!!!!!!!!!!!!!" + Thread.currentThread().getName());
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
				System.out.println("!!!!!!!!!!!!!!!!!" + Thread.currentThread().getName());
				System.out.println(list);
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

		tiles[oldPos % 16].removeCamels(list); // Must be last command
	}
}
