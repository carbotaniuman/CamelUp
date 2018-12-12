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
	public static void drawBoard(Graphics g, Map<Color, TreeSet<RoundBettingCard>> cards, Player pl, List<Die> dice) {
		// draws the board outline
		g.setColor(new Color(255, 213, 93));
		g.fillRect(800, 0, 1120, 800);
		g.setColor(Color.BLACK);
		g.drawRect(1300, 80, 310, 250);
		g.drawRect(1300 + 310, 80, 310, 250);

		Font currentFont = g.getFont();
		g.setFont(new Font("TimesRoman", Font.PLAIN, 32));

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
		for (int y = 50, c = 0; y <= 750; y += 150, c++) {
			if (!cards.get(GameState.CAMELCOLORS.get(c)).isEmpty()) {
				CardDrawer.drawRoundBettingCard(g, 1050, y, cards.get(GameState.CAMELCOLORS.get(c)).first());
			}
		}

		// draws the buttons for the raceBettingCards
		for(int x = 1300; x <= 1796; x += 124) {
			drawRaceBetButton(g, Color.GRAY, x);
			drawRaceBetButton(g, Color.GRAY, x);
			drawRaceBetButton(g, Color.GRAY, x);
			drawRaceBetButton(g, Color.GRAY, x);
			drawRaceBetButton(g, Color.GRAY, x);
		}
		
		for (int i = 0; i < pl.getRaceBets().size(); i++) {
			RaceBettingCard rbc = pl.getRaceBets().get(i);

			if (rbc.getColor().equals(GameState.CAMELCOLORS.get(0))) // WHITE
				drawRaceBetButton(g, rbc.getColor(), 1300);
			else if (rbc.getColor().equals((GameState.CAMELCOLORS.get(1))))// ORANGE
				drawRaceBetButton(g, rbc.getColor(), 1424);
			else if (rbc.getColor().equals((GameState.CAMELCOLORS.get(2))))// YELLOW
				drawRaceBetButton(g, rbc.getColor(), 1548);
			else if (rbc.getColor().equals((GameState.CAMELCOLORS.get(3)))) // GREEN
				drawRaceBetButton(g, rbc.getColor(), 1672);
			else if (rbc.getColor().equals((GameState.CAMELCOLORS.get(4))))// BLUE
				drawRaceBetButton(g, rbc.getColor(), 1796);
		}

		// draws the die
		for (int y = 50, c = 0; y <= 750; y += 150, c++) {
			g.setColor(GameState.CAMELCOLORS.get(c));
			g.fillRect(870, y, 100, 80);
			g.setColor(Color.BLACK);
			g.drawRect(870, y, 100, 80);
		}

		for (Die d : dice) {
			if (d.getColor().equals(GameState.CAMELCOLORS.get(0))) // WHITE
				drawDie(g, d, 890, 50 + 10);
			else if (d.getColor().equals((GameState.CAMELCOLORS.get(1))))// ORANGE
				drawDie(g, d, 890, 200 + 10);
			else if (d.getColor().equals((GameState.CAMELCOLORS.get(2))))// YELLOW
				drawDie(g, d, 890, 350 + 10);
			else if (d.getColor().equals((GameState.CAMELCOLORS.get(3)))) // GREEN
				drawDie(g, d, 890, 500 + 10);
			else if (d.getColor().equals((GameState.CAMELCOLORS.get(4))))// BLUE
				drawDie(g, d, 890, 650 + 10);
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

	private static void drawRaceBetButton(Graphics g, Color c, int x) {
		Font oldFont = g.getFont();
		g.setFont(new Font("TimesRoman", Font.PLAIN, 56));
		g.setColor(c);
		g.fillRect(x, 0, 124, 40);
		g.fillRect(x, 40, 124, 40);
		g.setColor(Color.BLACK);
		g.drawRect(x, 0, 124, 40);
		g.drawRect(x, 40, 124, 40);

		FontMetrics fm = g.getFontMetrics();
		Rectangle2D textSize = fm.getStringBounds("W", g);
		int xPos = (124 - (int) textSize.getWidth()) / 2;
		int yPos = (38 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString("W", x + xPos, yPos);
		textSize = fm.getStringBounds("L", g);
		xPos = (124 - (int) textSize.getWidth()) / 2;
		yPos = (40 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString("L", x + xPos, 37 + yPos);
		g.setFont(oldFont);
	}

	private static void drawDie(Graphics g, Die die, int x, int y) {
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

	public static void drawPlayerData(Graphics g, List<Player> players) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.ITALIC, 40));
		g.drawString("Player Data", 1530, 380);
		g.setFont(new Font("Monospaced", Font.BOLD, 24));
		String st = String.format("%-15s %-8s %-10s %-6s", "Name", "RaceBets", "RoundBets", "Money");
		g.drawString(st, 1320, 410 + 33);
		for (int i1 = 0; i1 < players.size(); i1++) {
			Player player = players.get(i1);
			g.setFont(new Font("Monospaced", Font.BOLD, 24));
			String s = String.format("%-15s %-8d %-10d %-5s", player.getName(), player.getRaceBets().size(),
					player.getRoundBets().size(), player.getMoney() + " EP");
			g.drawString(s, 1320, 410 + 33 * (i1 + 2));
		}
	}
}
