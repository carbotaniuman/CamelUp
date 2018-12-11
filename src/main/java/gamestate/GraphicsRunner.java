package gamestate;
import javax.swing.JFrame;

import graphics.MenuPanel;

public class GraphicsRunner {
	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		f.add(new MenuPanel());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setUndecorated(true);
		f.setVisible(true);
		f.requestFocus();
	}
}
