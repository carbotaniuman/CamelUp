package drawers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeSet;

import game.Die;
import gamestate.GameState;
import game.Pyramid;
import immutable.RaceBettingCard;
import immutable.RoundBettingCard;

public class BoardDrawer {
	
	public void drawBoard(Graphics g, Map<Color,TreeSet<RoundBettingCard>> cards, Queue<RaceBettingCard> races, ArrayList<Die> dice)
	{
		//draws the board outline
		g.setColor(Color.ORANGE);
	}

	public static void drawRoundsBets(Graphics g, Map<Color,TreeSet<RoundBettingCard>> cards)
	{	//draw x = 1050
		int c = 0; 
		for( int y = 50 ; y <= 750; y += 150 )
		{
			if ( cards.get(GameState.CAMELCOLORS.get(c)) != null)
			{
				GraphicsMap.drawRoundBettingCard(g, 1050, y, cards.get(GameState.CAMELCOLORS.get(c++)).first());
			}
		}
	}
	
	public static void drawBets ( Graphics g, Queue<RaceBettingCard> cards )
	{
		
	}
	
	public void drawDie( Graphics g, ArrayList<Die> dice )
	{
		
	}
	
}
