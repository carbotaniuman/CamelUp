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

public class HandDrawer 
{
	//size of screen: 1920x1080
	//size of hand: 1920x280
	public static void drawHand(Graphics g, Player p, ArrayList<Player> players) 
	{
		//WholeHand
		g.setColor(new Color(255, 153, 0));
		g.fillRect(0, 800, 1920, 280);
		g.setColor(Color.BLACK); 
		g.drawRect(0, 800, 1920, 280);
		//DesertCard
		if(!p.getDesertCard().isPresent())
			GraphicsMap.drawDesertCard(g, 0, 800, "+/-");
		//RaceBettingCards
		List<RaceBettingCard> cards = p.getRaceBets();
		for(int i = 0; i < cards.size(); i++) 
			GraphicsMap.drawRaceBettingCard(g, 150*i, 900, cards.get(i).getColor(), p);
		//RoundBettingCards
		int numWh=0, numOrng=0, numYel=0, numGrn=0, numBlu=0;
		for(int i = 0; i < p.getRoundBets().size(); i++)
		{
			RoundBettingCard r = p.getRoundBets().get(i);
			if(r.getColor().equals(GameState.CAMELCOLORS.get(0))) //WHITE
				GraphicsMap.drawRoundBettingCard(g, (1920-150), 800+numWh++*80, r);
			else if(r.getColor().equals((GameState.CAMELCOLORS.get(1))))//ORANGE
				GraphicsMap.drawRoundBettingCard(g, (1920-300), 800+numOrng++*80, r);
			else if(r.getColor().equals((GameState.CAMELCOLORS.get(2))))//YELLOW
				GraphicsMap.drawRoundBettingCard(g, (1920-450), 800+numYel++*80, r);
			else if(r.getColor().equals((GameState.CAMELCOLORS.get(3)))) //GREEN
				GraphicsMap.drawRoundBettingCard(g, (1920-600), 800+numGrn++*80, r);
			else if(r.getColor().equals((GameState.CAMELCOLORS.get(4))))//BLUE
				GraphicsMap.drawRoundBettingCard(g, (1920-750), 800+numBlu++*80, r);
		}
		//Balance
		g.setColor(new Color(51, 204, 255));
		g.fillRoundRect(250, 800, 500, 100, 30, 30);
		g.setColor(Color.BLACK);
		g.drawRoundRect(250, 800, 500, 100, 30, 30);
		g.setFont(new Font("Serif", Font.BOLD, 30));
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D textSize = fm.getStringBounds(p.getName()+"'s Balance = "+p.getMoney() + ".00E£", g);
		int xPos = (500 - (int) textSize.getWidth()) / 2;
		int yPos = (100 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString(p.getName()+"'s Balance = "+p.getMoney() + ".00E£", 250+xPos, 800+yPos);
		//Players Data
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.ITALIC, 35));
		g.drawString("Player data", 770, 830);
		g.setFont(new Font("Monospaced", Font.BOLD, 15));
		String st = String.format("%-15s %1s %10s %6s", "Name", "RaceBets", "RoundBets", "Money");
		g.drawString(st, 770, 830+20*(1));
		for(int i = 0; i < players.size(); i++)
		{
			Player pl = players.get(i);
			System.out.println(pl.getName().length());
			g.setFont(new Font("Monospaced", Font.BOLD, 15));
			String minS = String.format("10s", 50);
			String s = String.format("%-15s %1d %9d %10d.00E£", pl.getName(), pl.getRaceBets().size(), 50, pl.getMoney());
			g.drawString(s, 770, 830+20*(i+2));
		}
	}
}