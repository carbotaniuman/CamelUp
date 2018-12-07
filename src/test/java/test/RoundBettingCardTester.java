package test;
import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.Color;

import immutable.RoundBettingCard;

public class RoundBettingCardTester {
	@Test
	public void getterTest() {
		RoundBettingCard rbc = new RoundBettingCard(Color.RED, 5);
		assertEquals(rbc.getColor(), Color.RED);
	}
	
	@Test
	public void compareToTest() {
		RoundBettingCard rbc1 = new RoundBettingCard(Color.RED, 5);
		RoundBettingCard rbc2 = new RoundBettingCard(Color.RED, 1);
		assertTrue(rbc1.compareTo(rbc2) > 0);
	}
}
