package drawers;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.List;

import gamestate.Player;
import immutable.RaceBettingCard;

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
		g.setColor(Color.YELLOW);
		g.fillRect(0, 800, 180, 100);
		g.setColor(Color.BLACK);
		g.drawRect(0, 800, 180, 100);
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
			FontMetrics fm = g.getFontMetrics();
			Rectangle2D textSize = fm.getStringBounds(p.getName(), g);
			int xPos = (150 - (int) textSize.getWidth()) / 2;
			int yPos = (180 - (int) textSize.getHeight()) / 2 + fm.getAscent();
			g.drawString(p.getName(), i*150+xPos, 900 + yPos);
		}
	}
}
