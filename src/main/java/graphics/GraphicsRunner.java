package graphics;

import javax.swing.JFrame;

import gamestate.GameState;

public class GraphicsRunner {
	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		f.add(new GraphicsPanel());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setUndecorated(true);
		f.setVisible(true);
		f.requestFocus();
	}
}
