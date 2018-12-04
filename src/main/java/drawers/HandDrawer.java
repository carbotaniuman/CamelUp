package drawers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.List;

import gamestate.GameState;
import gamestate.Player;
import immutable.RaceBettingCard;
import immutable.RoundBettingCard;

public class HandDrawer 
{
	//size of screen: 1920x1080
	//size of hand: 1920x280
	public static void drawHand(Graphics g, Player p) 
	{
		//WholeHand
		g.setColor(Color.ORANGE);
		g.fillRect(0, 800, 1920, 280);
		g.setColor(Color.BLACK); 
		g.drawRect(0, 800, 1920, 280);
		//DesertCard
		if(!p.hasDesertCard())
		{
			GraphicsMap.drawDesertCard(g, 0, 800, "+/-");
		}
		//RaceBettingCards
		List<RaceBettingCard> cards = p.getRaceBets();
		for(int i = 0; i < cards.size(); i++) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(i*150, 900, 150, 180);
			g.setColor(Color.BLACK);
			g.drawRect(i*150, 900, 150, 180);
			
			g.setColor(cards.get(i).getColor());
			g.fillOval(i* 150 + (150 - 20) / 2, 900 + (180 - 20) / 2, 20, 20);
			g.fillOval(i*150+25, 935, 100, 100);
			
			g.setColor(Color.BLACK);
			
			g.setFont(new Font("TimesRoman", Font.BOLD, 22));
			FontMetrics fm = g.getFontMetrics();
			Rectangle2D textSize = fm.getStringBounds(p.getName(), g);
			int xPos = (150 - (int) textSize.getWidth()) / 2;
			int yPos = (180 - (int) textSize.getHeight()) / 2 + fm.getAscent();
			g.drawString(p.getName(), i*150+xPos, 895+yPos);
		}
		//RoundBettingCards
		g.setColor(GameState.CAMELCOLORS.get(0));
		g.fillRect(1920-200, 800, 200, 100);
		g.setColor(Color.BLACK);
		g.drawRect(1920-200, 800, 200, 100);
		//numWh++;
		List<RoundBettingCard> roundBets = p.getRoundBets();
		int numWh=0, numOrng=0, numYel=0, numGrn=0, numBlu=0;
		for(int i =0; i < roundBets.size(); i++)
		{
			RoundBettingCard r = roundBets.get(i);
			if(r.getCamelColor().equals(GameState.CAMELCOLORS.get(0))) //WHITE
			{
				g.setColor(GameState.CAMELCOLORS.get(0));
				g.fillRect(1920-200, 800, 200, 100);
				g.setColor(Color.BLACK);
				g.drawRect(1920-200, 800, 200, 100);
				numWh++;
			}
			else if(r.getCamelColor().equals((GameState.CAMELCOLORS.get(1))))//ORANGE
			{
				g.setColor(GameState.CAMELCOLORS.get(1));
				numOrng++;
			}
			else if(r.getCamelColor().equals((GameState.CAMELCOLORS.get(2))))//YELLOW
			{
				g.setColor(GameState.CAMELCOLORS.get(2));
				numYel++;
			}
			else if(r.getCamelColor().equals((GameState.CAMELCOLORS.get(3)))) //GREEN
			{
				g.setColor(GameState.CAMELCOLORS.get(3));
				numGrn++;
			}
			else if(r.getCamelColor().equals((GameState.CAMELCOLORS.get(4))))//BLUE
			{
				g.setColor(GameState.CAMELCOLORS.get(4));
				numBlu++;
			}
		}
	}
}
