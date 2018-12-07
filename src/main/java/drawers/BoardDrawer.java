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
	
	public static void drawBoard(Graphics g, Map<Color,TreeSet<RoundBettingCard>> cards/*, Queue<RaceBettingCard> winRaces, Queue<RaceBettingCard> loseRaces, ArrayList<Die> dice*/)
	{
		//draws the board outline
		g.setColor(new Color(255, 153, 0));
		g.fillRect(800, 0, 1120, 800);
		g.setColor(Color.BLACK);
		g.drawRect(800, 0, 1120, 800);
		g.drawLine(1300, 0, 1300, 800);
		g.drawLine(1300, 400, 1920, 400);
		//draws the RoundBettingCards
		int c = 0; 
		for( int y = 50 ; y <= 750; y += 150 )
		{
			if ( cards.get(GameState.CAMELCOLORS.get(c)) != null)
			{
				CardDrawer.drawRoundBettingCard(g, 1050, y, cards.get(GameState.CAMELCOLORS.get(c++)).first());
			}
		}
		//draws the buttons for the raceBettingCards
		int i = 0;
		for ( int x = 1300; x < 1920; x += 124)
		{
			g.setColor(GameState.CAMELCOLORS.get(i++));
			g.fillRect(x, 0, 124, 40);
			g.fillRect(x, 400, 124, 40);
			g.setColor(Color.BLACK);
			g.drawRect(x, 0, 124, 40);
			g.drawString("+", x+45,40);
			g.drawRect(x, 400, 124, 40);
			g.drawString("-", x+53,435);
		}
		
		//draws the die
		int d = 0; 
		for( int y = 50 ; y <= 750; y += 150 )
		{
			g.setColor(GameState.CAMELCOLORS.get(d++));
			g.fillRect( 870, y, 100, 80);
			g.setColor(Color.BLACK);
			g.drawRect( 870, y, 100, 80);
		}
	}

	public static void drawWinBets ( Graphics g, Queue<RaceBettingCard> cards )
	{
		int y = 50;
		int c = 0;
		for ( int x = 1760; x >= 1400; x -= 90)
		{
			if ( c == 5 || c == 10 || c== 15 || c == 20 )
			{
				y += 50;
				x = 1760;
			}
			if ( !cards.isEmpty())
				GraphicsMap.drawRaceBettingCard(g, x, y, cards.peek().getColor(), cards.poll().getPlayer());
				c++;
		}
	}
	
	public static void drawLoseBets ( Graphics g, Queue<RaceBettingCard> cards )
	{
		int y = 450;
		int c = 0;
		for ( int x = 1760; x >= 1400; x -= 90)
		{
			if ( c == 5 || c == 10 || c== 15 || c == 20 )
			{
				y += 50;
				x = 1760;
			}
			if ( !cards.isEmpty())
				GraphicsMap.drawRaceBettingCard(g, x, y, cards.peek().getColor(), cards.poll().getPlayer());
				c++;
		}
	}
	
	public void drawDie( Graphics g, ArrayList<Die> dice )
	{
		
	}
	
}
