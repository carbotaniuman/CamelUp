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

public class HandDrawer {
	public static void drawHand(Graphics g, Player p) {
		Font oldFont = g.getFont();
		// WholeHand
		g.setColor(new Color(255, 153, 0));
		g.fillRect(0, 800, 1920, 280);
		g.setColor(Color.BLACK);
		g.drawRect(0, 800, 1920, 280);

		// DesertCard
		if (!p.getDesertCard().isPresent())
			CardDrawer.drawDesertCard(g, (180 - 70) / 2, 800 + (100 - 70) / 2, "+/-", !p.getDesertCard().isPresent());

		// RaceBettingCards
		List<RaceBettingCard> cards = p.getRaceBets();
		for (int i = 0; i < cards.size(); i++)
			CardDrawer.drawRaceBettingCard(g, 150 * i, 900, cards.get(i).getColor(), p);

		// RollCards
		for (int i = 0, x = 775; i < p.getRollCards(); i++, x += 50)
			CardDrawer.drawRollCard(g, x, 900);

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
		g.drawRect(180, 800, 570, 100);
		g.setFont(new Font("Serif", Font.ITALIC, 30));
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D textSize = fm.getStringBounds(p.getName() + "'s Balance = " + p.getMoney() + "EP", g);
		int xPos = (570 - (int) textSize.getWidth()) / 2;
		int yPos = (100 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString(p.getName() + "'s Balance: " + p.getMoney() + " EP", 180 + xPos, 800 + yPos);

		g.setFont(oldFont);
	}
}