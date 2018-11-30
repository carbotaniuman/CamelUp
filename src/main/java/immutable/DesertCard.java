package immutable;

import gamestate.Player;

public class DesertCard {
	private boolean isOasis;
	private Player p;

	// Constructor
	public DesertCard(Player p, boolean isOasis) {
		this.isOasis = isOasis;
		this.p = p;
	}

	// getMoveNum
	public int getMoveNum() {
		if (isOasis) {
			return 1;
		} else {
			return -1;
		}
	}

	// getPlayer
	public Player getPlayer() {
		return p;
	}
}
