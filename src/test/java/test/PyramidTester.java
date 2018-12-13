package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

import game.Die;
import game.Pyramid;

public class PyramidTester {
	public final static List<Color> CAMELCOLORS = ImmutableList.of(Color.WHITE, Color.ORANGE, Color.YELLOW, Color.GREEN,
			Color.BLUE);

	private Pyramid pyramid;

	@Before
	public void setUp() {
		pyramid = new Pyramid(CAMELCOLORS);
	}

	@After
	public void tearDown() {
		pyramid = null;
	}

	@Test
	public void pyramidNonEqualityTest() {
		assertNotEquals(pyramid, new Pyramid(CAMELCOLORS));
	}

	@Test(expected = IllegalArgumentException.class)
	public void atLeastOneDieTest() {
		new Pyramid(Collections.emptyList());
	}

	@Test
	public void pyramidRollTest() {
		assertEquals(pyramid.getDiceNotRolled().size(), CAMELCOLORS.size());
		assertFalse(pyramid.areAllDiceRolled());
		while (!pyramid.areAllDiceRolled()) {
			pyramid.getRandomDie();
		}
		assertEquals(pyramid.getDiceNotRolled().size(), 0);
		assertTrue(pyramid.areAllDiceRolled());

		pyramid.resetDice();
		assertEquals(pyramid.getDiceNotRolled().size(), CAMELCOLORS.size());
		assertFalse(pyramid.areAllDiceRolled());
	}

	@Test
	public void lastDieTest() {
		Die d = pyramid.getRandomDie();
		assertEquals(d, pyramid.getLastDie());
	}
}
