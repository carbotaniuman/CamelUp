package drawers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import gamestate.GameState;
import gamestate.Player;
import immutable.RaceBettingCard;
import immutable.RoundBettingCard;

public class HandDrawer {
	public static void drawHand(Graphics g, Player p, List<Player> players) {
		// WholeHand
		g.setColor(new Color(255, 153, 0));
		g.fillRect(0, 800, 1920, 280);
		g.setColor(Color.BLACK);
		g.drawRect(0, 800, 1920, 280);

		// DesertCard
		CardDrawer.drawDesertCard(g, 0, 800, "+/-", !p.getDesertCard().isPresent());

		// RaceBettingCards
		List<RaceBettingCard> cards = p.getRaceBets();
		for (int i = 0; i < cards.size(); i++)
			CardDrawer.drawRaceBettingCard(g, 150 * i, 900, cards.get(i).getColor(), p);

		// RoundBettingCards
		int numWh = 0, numOrng = 0, numYel = 0, numGrn = 0, numBlu = 0;
		for (int i = 0; i < p.getRoundBets().size(); i++) {
			RoundBettingCard r = p.getRoundBets().get(i);
			if (r.getColor().equals(GameState.CAMELCOLORS.get(0))) // WHITE
				CardDrawer.drawRoundBettingCard(g, (1920 - 150), 800 + numWh++ * 80, r);
			else if (r.getColor().equals((GameState.CAMELCOLORS.get(1))))// ORANGE
				CardDrawer.drawRoundBettingCard(g, (1920 - 300), 800 + numOrng++ * 80, r);
			else if (r.getColor().equals((GameState.CAMELCOLORS.get(2))))// YELLOW
				CardDrawer.drawRoundBettingCard(g, (1920 - 450), 800 + numYel++ * 80, r);
			else if (r.getColor().equals((GameState.CAMELCOLORS.get(3)))) // GREEN
				CardDrawer.drawRoundBettingCard(g, (1920 - 600), 800 + numGrn++ * 80, r);
			else if (r.getColor().equals((GameState.CAMELCOLORS.get(4))))// BLUE
				CardDrawer.drawRoundBettingCard(g, (1920 - 750), 800 + numBlu++ * 80, r);
		}
		// Balance
//		g.setColor(new Color(51, 204, 255));
//		g.fillRect(180, 800, 570, 100);
//		g.setColor(Color.BLACK);
		g.drawRect(180, 800, 570, 100);
		g.setFont(new Font("Serif", Font.ITALIC, 30));
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D textSize = fm.getStringBounds(p.getName() + "'s Balance = " + p.getMoney() + ".00E£", g);
		int xPos = (570 - (int) textSize.getWidth()) / 2;
		int yPos = (100 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString(p.getName() + "'s Balance: " + p.getMoney() + " E£", 180 + xPos, 800 + yPos);

		// Players Data
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.ITALIC, 35));
		g.drawString("Player Data", 770, 880);
		g.setFont(new Font("Monospaced", Font.BOLD, 15));
		String st = String.format("%-15s %-8s %-10s %-6s", "Name", "RaceBets", "RoundBets", "Money");
		g.drawString(st, 770, 880 + 20);
		for (int i = 0; i < players.size(); i++) {
			Player pl = players.get(i);
			System.out.println(pl.getName().length());
			g.setFont(new Font("Monospaced", Font.BOLD, 15));
			String s = String.format("%-15s %-8d %-10d %-5s", pl.getName(), pl.getRaceBets().size(), pl.getRoundBets().size(),
					pl.getMoney() + " E£");
			g.drawString(s, 770, 880 + 20 * (i + 2));
		}
	}
}