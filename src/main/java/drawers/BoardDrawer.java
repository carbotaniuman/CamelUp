package drawers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

import game.Die;
import gamestate.GameState;
import immutable.RaceBettingCard;
import immutable.RoundBettingCard;

public class BoardDrawer {
	/*
	 * Queue<RaceBettingCard> winRaces, Queue<RaceBettingCard> loseRaces,
	 * ArrayList<Die> dice
	 */
	public static void drawBoard(Graphics g, Map<Color, TreeSet<RoundBettingCard>> cards) {
		// draws the board outline
		g.setColor(new Color(255, 153, 0));
		g.fillRect(800, 0, 1120, 800);

		// Green for win, red for lose
		g.setColor(Color.GREEN);
		g.fillRect(1300, 40, 620, 360);

		g.setColor(Color.RED);
		g.fillRect(1300, 440, 620, 360);
		g.setColor(Color.BLACK);

		Font currentFont = g.getFont();
		Font newFont = currentFont.deriveFont(currentFont.getSize() * 3F);

		g.setFont(newFont);
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D textSize = fm.getStringBounds("Win Bets", g);
		int xPos = (620 - (int) textSize.getWidth()) / 2;
		int yPos = (360 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString("Win Bets", 1300 + xPos, 40 + yPos);

		textSize = fm.getStringBounds("Lose Bets", g);
		xPos = (620 - (int) textSize.getWidth()) / 2;
		yPos = (360 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString("Lose Bets", 1300 + xPos, 440 + yPos);

		g.setFont(currentFont);

		g.drawRect(800, 0, 1120, 800);
		g.drawLine(1300, 0, 1300, 800);
		g.drawLine(1300, 400, 1920, 400);
		// draws the RoundBettingCards
		int c = 0;
		for (int y = 50; y <= 750; y += 150) {
			if (cards.get(GameState.CAMELCOLORS.get(c)) != null) {
				CardDrawer.drawRoundBettingCard(g, 1050, y, cards.get(GameState.CAMELCOLORS.get(c++)).first());
			}
		}
		// draws the buttons for the raceBettingCards
		int i = 0;
		for (int x = 1300; x < 1920; x += 124) {
			g.setColor(GameState.CAMELCOLORS.get(i++));
			g.fillRect(x, 0, 124, 40);
			g.fillRect(x, 400, 124, 40);
			g.setColor(Color.BLACK);
			g.drawRect(x, 0, 124, 40);
			g.drawString("+", x + 45, 40);
			g.drawRect(x, 400, 124, 40);
			g.drawString("-", x + 53, 435);
		}

		// draws the die
		int d = 0;
		for (int y = 50; y <= 750; y += 150) {
			g.setColor(GameState.CAMELCOLORS.get(d++));
			g.fillRect(870, y, 100, 80);
			g.setColor(Color.BLACK);
			g.drawRect(870, y, 100, 80);
		}
	}

	public static void drawWinBets(Graphics g, Queue<RaceBettingCard> cards) {
		int y = 40 + 10;
		int c = 0;
		int x = 1760;
		while (!cards.isEmpty()) {
			if (c == 5 || c == 10 || c == 15 || c == 20) {
				y += 40;
				x = 1760 + (int)(c/5) * -10;
			}
			CardDrawer.drawPlacedRaceBettingCard(g, x, y, cards.peek().getColor(), cards.poll().getPlayer());
			c++;
			x -= 100;
		}
	}

	public static void drawLoseBets(Graphics g, Queue<RaceBettingCard> cards) {
		int y = 440 + 10;
		int c = 0;
		int x = 1760;
		while (!cards.isEmpty()) {
			if (c == 5 || c == 10 || c == 15 || c == 20) {
				y += 40;
				x = 1760 + (int)(c/5) * -10;
			}
			CardDrawer.drawPlacedRaceBettingCard(g, x, y, cards.peek().getColor(), cards.poll().getPlayer());
			c++;
			x -= 100;
		}
	}

	public void drawDie(Graphics g, ArrayList<Die> dice) {

	}

}
