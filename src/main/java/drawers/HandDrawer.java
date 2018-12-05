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

public class HandDrawer 
{
	//size of screen: 1920x1080
	//size of hand: 1920x280
	public static void drawHand(Graphics g, Player p) 
	{
		//WholeHand
		g.setColor(Color.ORANGE);
		g.fillRect(0, 800, 1920, 280);
		g.setColor(Color.BLACK); 
		g.drawRect(0, 800, 1920, 280);
		//DesertCard
		if(!p.hasDesertCard())
			GraphicsMap.drawDesertCard(g, 0, 800, "+/-");
		//RaceBettingCards
		List<RaceBettingCard> cards = p.getRaceBets();
		for(int i = 0; i < cards.size(); i++) 
			GraphicsMap.drawRaceBettingCard(g, 150*i, 900, cards.get(i).getColor(), p);
		//RoundBettingCards
		//GraphicsMap.drawRoundBettingCard(g, 1920-200, 800, p.getRoundBets().get(0), GameState.CAMELCOLORS.get(0));
		int numWh=0, numOrng=0, numYel=0, numGrn=0, numBlu=0;
		List<RoundBettingCard> roundBets = p.getRoundBets();
		for(int i = 0; i < p.getRoundBets().size(); i++)
		{
			RoundBettingCard r = roundBets.get(i);
			if(r.getCamelColor().equals(GameState.CAMELCOLORS.get(0))) //WHITE
			{
				GraphicsMap.drawRoundBettingCard(g, (1920-200), 800+numWh*80, r);
				numWh++;
			}
			else if(r.getCamelColor().equals((GameState.CAMELCOLORS.get(1))))//ORANGE
			{
				GraphicsMap.drawRoundBettingCard(g, (1920-400), 800+numOrng*80, r);
				numOrng++;
			}
			else if(r.getCamelColor().equals((GameState.CAMELCOLORS.get(2))))//YELLOW
			{
				GraphicsMap.drawRoundBettingCard(g, (1920-600), 800+numYel*80, r);
				numYel++;
			}
			else if(r.getCamelColor().equals((GameState.CAMELCOLORS.get(3)))) //GREEN
			{
				GraphicsMap.drawRoundBettingCard(g, (1920-800), 800+numGrn*80, r);
				numGrn++;
			}
			else if(r.getCamelColor().equals((GameState.CAMELCOLORS.get(4))))//BLUE
			{
				GraphicsMap.drawRoundBettingCard(g, (1920-1000), 800+numBlu*100, r);
				numBlu++;
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		g.setColor(GameState.CAMELCOLORS.get(0));
		g.fillRect(1920-200, 800, 200, 100);
		g.setColor(Color.BLACK);
		g.drawRect(1920-200, 800, 200, 100);
		//numWh++;
		List<RoundBettingCard> roundBets = p.getRoundBets();
		int numWh=0, numOrng=0, numYel=0, numGrn=0, numBlu=0;
		
		for(int i =0; i < roundBets.size(); i++)
		{
			RoundBettingCard r = roundBets.get(i);
			if(r.getCamelColor().equals(GameState.CAMELCOLORS.get(0))) //WHITE
			{
				g.setColor(GameState.CAMELCOLORS.get(0));
				g.fillRect(1920-200, 800, 200, 100);
				g.setColor(Color.BLACK);
				g.drawRect(1920-200, 800, 200, 100);
				numWh++;
			}
			else if(r.getCamelColor().equals((GameState.CAMELCOLORS.get(1))))//ORANGE
			{
				g.setColor(GameState.CAMELCOLORS.get(1));
				numOrng++;
			}
			else if(r.getCamelColor().equals((GameState.CAMELCOLORS.get(2))))//YELLOW
			{
				g.setColor(GameState.CAMELCOLORS.get(2));
				numYel++;
			}
			else if(r.getCamelColor().equals((GameState.CAMELCOLORS.get(3)))) //GREEN
			{
				g.setColor(GameState.CAMELCOLORS.get(3));
				numGrn++;
			}
			else if(r.getCamelColor().equals((GameState.CAMELCOLORS.get(4))))//BLUE
			{
				g.setColor(GameState.CAMELCOLORS.get(4));
				numBlu++;
			}
		}
		*/
	}
}
