package immutable;

import gamestate.Player;

public class DesertCard {
	private final boolean isOasis;
	private final Player p;
	private final int tile;

	// Constructor
	public DesertCard(Player p, boolean isOasis, int tile) {
		this.isOasis = isOasis;
		this.p = p;
		this.tile = tile;
	}

	public int getMoveNum() {
		if (isOasis) {
			return 1;
		} else {
			return -1;
		}
	}

	public Player getPlayer() {
		return p;
	}
	
	public int getTile() {
		return tile;
	}
	
	public String toString() {
		return "DesertCard: " + p.getName() + " " + (isOasis ? "1" : "-1");
	}
}
