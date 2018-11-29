import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import game.Camel;

public class CamelTester {
	private Camel camel;
	
	@Before
    public void setUp() {
		camel = new Camel(Color.RED);
    }


    @After
    public void tearDown() {
        camel = null;
    }

	@Test
	public void camelEqualityTest() {
		assertEquals(camel, new Camel(Color.RED));
		assertNotEquals(camel, new Camel(Color.BLUE));
	}
	
	@Test
	public void camelGetterTest() {
		assertEquals(camel.getColor(), Color.RED);
		assertNotEquals(camel.getColor(), Color.BLUE);
	}
}
