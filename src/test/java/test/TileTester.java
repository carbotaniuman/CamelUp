package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import gamestate.Tile;
import immutable.Camel;
import immutable.DesertCard;

public class TileTester {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

	@Mock
	private DesertCard mockCard;

	@Mock
	private Camel mockCamel1;
	@Mock
	private Camel mockCamel2;
	@Mock
	private Camel mockCamel3;
	@Mock
	private Camel mockCamel4;
	@Mock
	private Camel mockCamel5;

	private List<Camel> mockCamels;

	private Tile tile;

	@Before
	public void setUp() {
		tile = new Tile();
		mockCamels = ImmutableList.of(mockCamel1, mockCamel2, mockCamel3, mockCamel4, mockCamel5);
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

		tile.addCamelsTop(mockCamels);

		assertEquals(tile.getCamels().size(), mockCamels.size());

		tile.removeCamels(mockCamels);

		assertTrue(tile.getCamels().isEmpty());

		for (Camel c : mockCamels) {
			verifyZeroInteractions(c);
		}
	}

	@Test
	public void camelStackTest() {
		assertTrue(tile.getCamels().isEmpty());

		for (Camel c : mockCamels) {
			tile.addCamelTop(c);
		}

		assertEquals(tile.getCamels(), Lists.reverse(mockCamels));
	}
}
