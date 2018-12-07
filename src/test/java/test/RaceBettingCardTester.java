package test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import java.awt.Color;

import gamestate.Player;
import immutable.RaceBettingCard;

public class RaceBettingCardTester {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
	
	@Mock
	private Player player;

	@Test
	public void getterTest() {
		RaceBettingCard rbc = new RaceBettingCard(Color.RED, player);
		assertEquals(rbc.getColor(), Color.RED);
		assertEquals(rbc.getPlayer(), player);
		verifyZeroInteractions(player);
	}
}
