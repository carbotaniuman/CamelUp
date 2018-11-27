import static org.junit.Assert.*;
import org.junit.Test;

import gamestate.Player;
import immutable.DesertCard;

public class DesertCardTester {
	@Test
	public void playerDesertCardTest() {
		Player p = new Player("Test");
		DesertCard ds = new DesertCard(p, false);
		assertEquals(ds.getPlayer(), p);
		assertEquals(ds.getMoveNum(), -1);
		
		DesertCard dsi = new DesertCard(p, true);
		assertEquals(dsi.getPlayer(), p);
		assertEquals(dsi.getMoveNum(), 1);
	}

	@Test
	public void playerEqualityTest() {
		Player p = new Player("Test");
		assertNotEquals(p, new Player("Test"));
		assertNotEquals(p, new Player("Adadsdasd"));
	}
}
