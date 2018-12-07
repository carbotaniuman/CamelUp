package test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import game.Die;

public class DieTester {
	private Die die;
	
	@Before
    public void setUp() {
		die = new Die(Color.RED);
    }


    @After
    public void tearDown() {
    	die = null;
    }

	@Test
	public void dieEqualityTest() {
		assertNotEquals(die, new Die(Color.BLUE));
		assertNotEquals(die.getColor(), Color.BLUE);
	}

	@Test
	public void dieRollBooleanTest() {
		assertFalse(die.getIfRolled());
		die.roll();
		assertTrue(die.getIfRolled());
		die.resetIfRolled();
		assertFalse(die.getIfRolled());
	}
	
	@Test
	public void rollTest() {
		for(int i = 0; i < 100; i++) {
			die.roll();
			assertTrue(die.getLastRoll() >= 1 && die.getLastRoll() <= 3);
		}
	}
}
