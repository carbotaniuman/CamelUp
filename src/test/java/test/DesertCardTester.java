package test;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import gamestate.Player;
import immutable.DesertCard;

public class DesertCardTester {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
	
	@Mock
	private Player player;
	
	@Test
	public void playerMoveNumTest() {
		DesertCard ds = new DesertCard(player, false, 0);
		assertEquals(ds.getMoveNum(), -1);
		
		DesertCard dsi = new DesertCard(player, true, 0);
		assertEquals(dsi.getMoveNum(), 1);
	}

	@Test
	public void playerEqualityTest() {
		DesertCard ds = new DesertCard(player, false, 0);
		assertEquals(ds.getPlayer(), player);
		
		DesertCard dsi = new DesertCard(player, true, 0);
		assertEquals(dsi.getPlayer(), player);
	}
}
