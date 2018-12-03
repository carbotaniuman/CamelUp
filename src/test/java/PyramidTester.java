import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

import game.Pyramid;

import java.awt.Color;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PyramidTester {
	public final static List<Color> CAMELCOLORS = ImmutableList.of(Color.WHITE, Color.ORANGE, Color.YELLOW,
			Color.GREEN, Color.BLUE);
	
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
		assertEquals(pyramid.getNumNotRolledDice(), CAMELCOLORS.size());
		assertFalse(pyramid.areAllDiceRolled());
		while(!pyramid.areAllDiceRolled()) {
			pyramid.getDie(ThreadLocalRandom.current().nextInt(pyramid.getNumNotRolledDice()));
		}
		assertEquals(pyramid.getNumNotRolledDice(), 0);
		assertTrue(pyramid.areAllDiceRolled());
		
		pyramid.resetDice();
		assertEquals(pyramid.getNumNotRolledDice(), CAMELCOLORS.size());
		assertFalse(pyramid.areAllDiceRolled());
	}
}
