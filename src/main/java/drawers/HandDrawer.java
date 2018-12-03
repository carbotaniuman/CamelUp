package drawers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

import gamestate.*;

public class HandDrawer 
{
	//size of screen: 1920x1080
	//size of hand: 1920x280
	public static void drawHand(Graphics g, Player p) 
	{
		Color[] colors = {Color.WHITE, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN};
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
		for(int i = 0; i < 5; i++)
		{
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(i*150, 900, 150, 180);
			g.setColor(Color.BLACK);
			g.drawRect(i*150, 900, 150, 180);
			g.setColor(Color.BLACK);
			g.fillOval(i*150+25, 935, 100, 100);
			g.setColor(colors[i]);
			g.drawString(p.getName(), i*150+65, 990);
		}
	}
}
