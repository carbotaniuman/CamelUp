import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import gamestate.Tile;
import immutable.Camel;
import immutable.DesertCard;

public class TileTester {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

	@Mock
	private DesertCard mockCard;
	@Mock
	private Camel mockCamel;
	private Tile tile;

	@Before
	public void setUp() {
		tile = new Tile();
	}

	@After
	public void tearDown() {
		tile = null;
	}

	@Test
	public void tileNonEqualityTest() {
		assertNotEquals(tile, new Tile());
	}

	@Test
	public void desertCardHoldingTest() {
		assertFalse(tile.getDesertCard().isPresent());
		tile.addDesertCard(mockCard);
		assertTrue(tile.getDesertCard().isPresent());
		assertEquals(tile.getDesertCard().orElse(null), mockCard);
		verifyZeroInteractions(mockCard);
	}
	
	@Test(expected = IllegalStateException.class)
	public void doubleDesertCardTest() {
		tile.addDesertCard(mockCard);
		tile.addDesertCard(mockCard);
	}

	@Test
	public void camelHoldTest() {
		assertTrue(tile.getCamels().isEmpty());

		for (int i = 0; i < 5; i++) {
			tile.addCamel(mockCamel);
		}
		assertEquals(tile.getCamels().size(), 5);

		for (int i = 0; i < 5; i++) {
			tile.removeCamel(mockCamel);
		}
		assertTrue(tile.getCamels().isEmpty());
		verifyZeroInteractions(mockCamel);
	}
}
