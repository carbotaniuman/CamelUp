package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import immutable.Camel;

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
		assertNotEquals(camel, null);
	}

	@Test
	public void camelGetterTest() {
		assertEquals(camel.getColor(), Color.RED);
		assertNotEquals(camel.getColor(), Color.BLUE);
	}
}
