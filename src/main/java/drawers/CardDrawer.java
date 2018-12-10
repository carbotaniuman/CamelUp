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
		Font oldFont = g.getFont();
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
		g.setFont(oldFont);
	}

	public static void drawRoundBettingCard(Graphics g, int x, int y, RoundBettingCard r) {
		Font oldFont = g.getFont();
		g.setColor(r.getColor());
		g.fillRect(x, y, 150, 80);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 150, 80);
		g.drawLine(x + 75, y, x + 75, y + 80);
		g.drawLine(x, y + 40, x + 75, y + 40);
		
		g.setFont(new Font("TimesRoman", Font.BOLD, 30));
		
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D textSize = fm.getStringBounds("1", g);
		int xPos = (75 - (int) textSize.getWidth()) / 2;
		int yPos = (40 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString("1", x + xPos, y + yPos);
		textSize = fm.getStringBounds("-1", g);
		xPos = (75 - (int) textSize.getWidth()) / 2;
		yPos = (40 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString("-1", x + xPos, y + 40 + yPos);
		
		g.setFont(new Font("TimesRoman", Font.BOLD, 60));
		fm = g.getFontMetrics();
		textSize = fm.getStringBounds(String.valueOf(r.getPoints()), g);
		xPos = (75 - (int) textSize.getWidth()) / 2;
		yPos = (80 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString(String.valueOf(r.getPoints()), x + 75 + xPos, y + yPos);
		g.setFont(oldFont);
	}

	public static void drawRaceBettingCard(Graphics g, int x, int y, Color c, Player p) {
		Font oldFont = g.getFont();
		g.setColor(new Color(144, 92, 70));
		g.fillRect(x, y, 150, 180);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 150, 180);
		
		Graphics2D g2 = (Graphics2D)g;
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(new BasicStroke(4));
		g.drawOval(x + (150 - 140) / 2, y + (180 - 140) / 2, 140, 140);
		g2.setStroke(oldStroke);
		
		g.setColor(c);
		g.fillOval(x + (150 - 140) / 2, y + (180 - 140) / 2, 140, 140);

		g.setColor(Color.BLACK);

		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D textSize = fm.getStringBounds(p.getName(), g);
		int xPos = (150 - (int) textSize.getWidth()) / 2;
		int yPos = (180 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString(p.getName(), x + xPos, y + yPos);
		g.setFont(oldFont);
	}
	
	public static void drawPlacedRaceBettingCard(Graphics g, int x, int y, Color c, Player p) {
		Font oldFont = g.getFont();
		g.setColor(new Color(144, 92, 70));
		g.fillRect(x, y, 150, 180);
		g.setColor(Color.BLACK);
		
		Graphics2D g2 = (Graphics2D)g;
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(new BasicStroke(2));
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
		
		g.setFont(oldFont);
	}
}
