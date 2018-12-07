
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.JPanel;

import drawers.BoardDrawer;
import drawers.HandDrawer;
import gamestate.GameState;
import gamestate.Player;
import immutable.RoundBettingCard;

public class HandDrawTest extends JPanel 
{
	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		f.add(new HandDrawTest());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setUndecorated(true);
		f.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Player pl = new Player("Prince Zuko", GameState.CAMELCOLORS);
		//TESTS
		pl.addRoundBet(new RoundBettingCard(Color.WHITE, 1));
		//pl.addRoundBet(new RoundBettingCard(Color.WHITE, 3));
		pl.addRoundBet(new RoundBettingCard(Color.WHITE, 5));
		pl.addRoundBet(new RoundBettingCard(Color.YELLOW, 5));
		//pl.addRoundBet(new RoundBettingCard(Color.YELLOW, 3));
		pl.addRoundBet(new RoundBettingCard(Color.YELLOW, 1));
		//pl.addRoundBet(new RoundBettingCard(Color.GREEN, 5));
		pl.addRoundBet(new RoundBettingCard(Color.GREEN, 3));
		pl.addRoundBet(new RoundBettingCard(Color.GREEN, 1));
		//pl.addRoundBet(new RoundBettingCard(Color.ORANGE, 5));
		//pl.addRoundBet(new RoundBettingCard(Color.ORANGE, 3));
		pl.addRoundBet(new RoundBettingCard(Color.ORANGE, 1));
		Color c = GameState.CAMELCOLORS.get(0);
		pl.addRoundBet(new RoundBettingCard(c, 5));
		pl.addRoundBet(new RoundBettingCard(c, 3));
		pl.addRoundBet(new RoundBettingCard(c, 1));
		pl.setMoney(34);
		ArrayList<Player> plyrs = new ArrayList<Player>();
		for(int i = 0; i < 5; i++)
		{
			Player p = new Player(GameState.names[i], GameState.CAMELCOLORS);
			plyrs.add(p);
		}
		HandDrawer.drawHand(g, pl, plyrs);
		Map<Color,TreeSet<RoundBettingCard>> roundBets = new HashMap<Color, TreeSet<RoundBettingCard>>();
		for (int i = 0; i < 5; i++) {
			TreeSet<RoundBettingCard> tree = new TreeSet<RoundBettingCard>();
			tree.add(new RoundBettingCard(GameState.CAMELCOLORS.get(i), 5));
			tree.add(new RoundBettingCard(GameState.CAMELCOLORS.get(i), 3));
			tree.add(new RoundBettingCard(GameState.CAMELCOLORS.get(i), 2));
			roundBets.put(GameState.CAMELCOLORS.get(i), tree);
		}
		BoardDrawer.drawBoard(g, roundBets);
	}
}
