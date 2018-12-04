package drawers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import immutable.RoundBettingCard;

public class GraphicsMap 
{
	public static void drawDesertCard(Graphics g, int x,int y, String plusOrMinus)
	{
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 180, 100);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 180, 100);
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.BOLD, 30));
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D textSize = fm.getStringBounds(plusOrMinus, g);
		int xPos = (180 - (int) textSize.getWidth()) / 2;
		int yPos = (100 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString(plusOrMinus, x+xPos, y+yPos);
	}
	
	public static void drawRoundBettingCard( Graphics g, int x, int y, RoundBettingCard r, Color c)
	{
		g.setColor(c);
		g.fillRect(x, y, 200, 100);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 200, 100);
		g.drawLine(x+100, y, x+100, y+100);
		g.drawLine(x, y+50, x+100, y+50);
		g.setFont(new Font("TimesRoman", Font.BOLD, 30));
		g.drawString("1",x+48, y+48);
		g.drawString("-1",x+48, y+98);
		g.setFont(new Font("TimesRoman", Font.BOLD, 60));
		g.drawString(r.getPoints() + "", x+148, y+80);
	}
}
