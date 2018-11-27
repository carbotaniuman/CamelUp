import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.Color;
import game.Camel;

public class CamelTester {

	@Test
	public void camelEqualityTest() {
		assertEquals(new Camel(Color.RED), new Camel(Color.RED));
		assertNotEquals(new Camel(Color.RED), new Camel(Color.BLUE));
	}
	
	@Test
	public void camelGetterTest() {
		assertEquals(new Camel(Color.RED).getColor(), Color.RED);
		assertNotEquals(new Camel(Color.RED).getColor(), Color.BLUE);
	}
}
