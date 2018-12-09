package drawers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import gamestate.Player;
import immutable.RoundBettingCard;

public class CardDrawer {
	public static void drawDesertCard(Graphics g, int x, int y, String plusOrMinus, boolean fill) {
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
		g.drawString(plusOrMinus, x + xPos, y + yPos);
	}

	public static void drawRoundBettingCard(Graphics g, int x, int y, RoundBettingCard r) {
		g.setColor(r.getColor());
		g.fillRect(x, y, 150, 80);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 150, 80);
		g.drawLine(x + 75, y, x + 75, y + 80);
		g.drawLine(x, y + 40, x + 75, y + 40);
		g.setFont(new Font("TimesRoman", Font.BOLD, 30));
		g.drawString("1", x + 30, y + 35);
		g.drawString("-1", x + 30, y + 75);
		g.setFont(new Font("TimesRoman", Font.BOLD, 60));
		g.drawString(r.getPoints() + "", x + 100, y + 60);
	}

	public static void drawRaceBettingCard(Graphics g, int x, int y, Color c, Player p) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, 150, 180);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 150, 180);

		g.setColor(c);
		g.fillOval(x + (150 - 140) / 2, y + (180 - 140) / 2, 140, 140);

		g.setColor(Color.BLACK);

		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D textSize = fm.getStringBounds(p.getName(), g);
		int xPos = (150 - (int) textSize.getWidth()) / 2;
		int yPos = (180 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString(p.getName(), x + xPos, y + yPos);
	}
	
	public static void drawPlacedRaceBettingCard(Graphics g, int x, int y, Color c, Player p) {
		g.setColor(new Color(138, 85, 69));
		g.fillRect(x, y, 150, 180);
		g.setColor(Color.BLACK);
		
		Graphics2D g2 = (Graphics2D)g;
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(new BasicStroke(5));
		g.drawRect(x, y, 150, 180);
		g2.setStroke(oldStroke);

		g.setFont(new Font("TimesRoman", Font.PLAIN, 90));
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D textSize = fm.getStringBounds("?", g);
		int xPos = (150 - (int) textSize.getWidth()) / 2;
		int yPos = (180 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString("?", x + xPos, y + yPos);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		fm = g.getFontMetrics();
		textSize = fm.getStringBounds(p.getName(), g);
		xPos = (150 - (int) textSize.getWidth()) / 2;
		yPos = (180 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString(p.getName(), x + xPos, y + yPos + 50);
	}
}
