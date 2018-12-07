package test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

import gamestate.Player;

public class PlayerTester {
	private Player player;

	@Before
	public void setUp() {
		player = new Player("Test", ImmutableList.of());
	}

	@After
	public void tearDown() {
		player = null;
	}

	@Test
	public void playerNonEqualityTest() {
		assertNotEquals(player, new Player("TestNG", ImmutableList.of()));
		assertNotEquals(player, new Player("Boolean Jim", ImmutableList.of()));
	}

	@Test
	public void moneySetterGetterTest() {
		assertEquals(player.getMoney(), 0);
		player.setMoney(5);
		assertEquals(player.getMoney(), 5);
		player.setMoney(Integer.MAX_VALUE);
		assertEquals(player.getMoney(), Integer.MAX_VALUE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void noNegativeMoneyTest() {
		player.setMoney(-1);
	}

	@Test
	public void desertCardTest() {
		assertFalse(player.getDesertCard().isPresent());
		player.setDesertCard(true);
		assertTrue(player.getDesertCard().isPresent());
	}
}
