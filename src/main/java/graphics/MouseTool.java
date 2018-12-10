package graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gamestate.GameState;

public class MouseTool extends MouseAdapter 
{
	private GraphicsPanel panel = new GraphicsPanel();
	@Override
	public void mousePressed(MouseEvent e) 
	{
		int x = e.getX();
		int y = e.getY();
		for(int i = 50; i <= 750; i +=150)
		{
			if(x > 1050 && x < 1250 && y >i && y < i+150)
			{
				panel.addRoundBetToPlayer(GameState.CAMELCOLORS.get((i-49)/150));
			}
		}
		
	}
}
