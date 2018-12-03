import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import drawers.HandDrawer;
import gamestate.GameState;
import gamestate.Player;

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
		HandDrawer.drawHand(g, new Player("Test", GameState.CAMELCOLORS));
	}
}
