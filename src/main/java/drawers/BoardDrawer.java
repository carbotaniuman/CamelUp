package drawers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeSet;

import game.Die;
import game.Pyramid;
import immutable.RaceBettingCard;
import immutable.RoundBettingCard;

public class BoardDrawer {

    private final static Color[] colors = {Color.BLUE, Color.GREEN, Color.ORANGE, Color.YELLOW, Color.WHITE,};

	public void drawRoundsBets(Graphics g, Map<Color,TreeSet<RoundBettingCard>> cards)
	{	//draw x = 1035
		int c = 0; 
		for( int y = 30; y <= 800; y += 130 )
		{
			
		}
	}
	
	public void drawBets ( Graphics g, Queue<RaceBettingCard> cards )
	{
		
	}
	
	public void drawDie( Graphics g, ArrayList<Die> dice )
	{
		
	}
	
}
