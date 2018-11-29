import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gamestate.Player;
import immutable.DesertCard;

public class DesertCardTester {
	private Player player;
	
	@Before
    public void setUp() {
		player = new Player("Test");
    }


    @After
    public void tearDown() {
        player = null;
    }
	
	@Test
	public void playerMoveNumTest() {
		DesertCard ds = new DesertCard(player, false);
		assertEquals(ds.getMoveNum(), -1);
		
		DesertCard dsi = new DesertCard(player, true);
		assertEquals(dsi.getMoveNum(), 1);
	}

	@Test
	public void playerEqualityTest() {
		DesertCard ds = new DesertCard(player, false);
		assertEquals(ds.getPlayer(), player);
		
		DesertCard dsi = new DesertCard(player, true);
		assertEquals(dsi.getPlayer(), player);
	}
}
