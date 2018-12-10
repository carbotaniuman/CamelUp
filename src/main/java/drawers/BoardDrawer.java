package drawers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

import game.Die;
import gamestate.GameState;
import gamestate.Player;
import immutable.RaceBettingCard;
import immutable.RoundBettingCard;

public class BoardDrawer {
	/*
	 * Queue<RaceBettingCard> winRaces, Queue<RaceBettingCard> loseRaces,
	 * ArrayList<Die> dice
	 */
	public static void drawBoard(Graphics g, Map<Color, TreeSet<RoundBettingCard>> cards, Player pl, List<Die> dice) {
		// draws the board outline
		g.drawRect(0, 0, 1920, 1080);

		g.setColor(Color.BLACK);
		g.drawRect(1300, 80, 310, 250);
		g.drawRect(1300 + 310, 80, 310, 250);

		Font currentFont = g.getFont();
		g.setFont(new Font("TimesRoman", Font.PLAIN, 38));

		FontMetrics fm = g.getFontMetrics();
		Rectangle2D textSize = fm.getStringBounds("Win Bets", g);
		int xPos = (310 - (int) textSize.getWidth()) / 2;
		int yPos = (250 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString("Win Bets", 1300 + xPos, 80 + yPos);

		textSize = fm.getStringBounds("Lose Bets", g);
		xPos = (310 - (int) textSize.getWidth()) / 2;
		yPos = (250 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString("Lose Bets", 1300 + 310 + xPos, 80 + yPos);

		g.drawRect(800, 0, 1120, 800);
		g.drawLine(1300, 0, 1300, 800);
		g.drawLine(1300, 330, 1920, 330);

		// draws the RoundBettingCards
		int c = 0;
		for (int y = 50; y <= 750; y += 150) {
			if (cards.get(GameState.CAMELCOLORS.get(c)) != null) {
				CardDrawer.drawRoundBettingCard(g, 1050, y, cards.get(GameState.CAMELCOLORS.get(c++)).first());
			}
		}
		
		// draws the buttons for the raceBettingCards
		int i = 0;
		g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
		for (int x = 1300; x < 1920; x += 124) {
			g.setColor(GameState.CAMELCOLORS.get(i++));
			g.fillRect(x, 0, 124, 40);
			g.fillRect(x, 40, 124, 40);
			g.setColor(Color.BLACK);
			g.drawRect(x, 0, 124, 40);
			g.drawRect(x, 40, 124, 40);

			fm = g.getFontMetrics();
			textSize = fm.getStringBounds("+", g);
			xPos = (124 - (int) textSize.getWidth()) / 2;
			yPos = (40 - (int) textSize.getHeight()) / 2 + fm.getAscent();
			g.drawString("+", x + xPos, yPos);
			textSize = fm.getStringBounds("-", g);
			xPos = (124 - (int) textSize.getWidth()) / 2;
			yPos = (40 - (int) textSize.getHeight()) / 2 + fm.getAscent();
			g.drawString("-", x + xPos, 35 + yPos);
		}

		// draws the die
		int d = 0;
		int da = 0;
		for (int y = 50; y <= 750; y += 150) {
			g.setColor(GameState.CAMELCOLORS.get(d));
			g.fillRect(870, y, 100, 80);
			g.setColor(Color.BLACK);
			g.drawRect(870, y, 100, 80);
			if (da < dice.size()) {
				if (dice.get(da).getColor() == GameState.CAMELCOLORS.get(d))
					drawDie(g, dice.get(da++), 890, y + 10);
			}
			d++;
		}

		g.setFont(currentFont);
	}

	public static void drawWinBets(Graphics g, Queue<RaceBettingCard> cards) {
		int y = 80 + 35;
		int x = 1300 + 80;
		
		while (!cards.isEmpty()) {
			CardDrawer.drawPlacedRaceBettingCard(g, x, y, cards.peek().getColor(), cards.poll().getPlayer());
		}
	}

	public static void drawLoseBets(Graphics g, Queue<RaceBettingCard> cards) {
		int y = 80 + 35;
		int x = 1610 + 80;
		while (!cards.isEmpty()) {
			CardDrawer.drawPlacedRaceBettingCard(g, x, y, cards.peek().getColor(), cards.poll().getPlayer());
		}
	}

	public static void drawDie(Graphics g, Die die, int x, int y) {
		g.setColor(die.getColor());
		g.fillRect(x, y, 60, 60);
		g.setColor(Color.BLACK);
		Graphics2D g2d = (Graphics2D) g;
		Stroke oldStroke = g2d.getStroke();
		g2d.setStroke(new BasicStroke(5));
		g.drawRect(x, y, 60, 60);
		g2d.setStroke(oldStroke);
		if (die.getLastRoll() == 1)
			g.fillOval(x + 25, y + 25, 10, 10);
		if (die.getLastRoll() == 2) {
			g.fillOval(x + 10, y + 10, 10, 10);
			g.fillOval(x + 40, y + 40, 10, 10);
		}
		if (die.getLastRoll() == 3) {
			g.fillOval(x + 25, y + 25, 10, 10);
			g.fillOval(x + 10, y + 10, 10, 10);
			g.fillOval(x + 40, y + 40, 10, 10);
		}
	}

}
