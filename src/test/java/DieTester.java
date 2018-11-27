import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.Color;

import game.Die;

public class DieTester {

	@Test
	public void dieEqualityTest() {
		assertNotEquals(new Die(Color.RED), new Die(Color.BLUE));
		assertNotEquals(new Die(Color.RED).getColor(), Color.BLUE);
	}

	@Test
	public void dieRollBooleanTest() {
		Die d = new Die(Color.RED);
		assertFalse(d.getIfRolled());
		d.roll();
		assertTrue(d.getIfRolled());
	}
}
