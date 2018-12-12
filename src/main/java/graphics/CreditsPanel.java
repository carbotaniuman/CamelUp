package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JPanel;

import ai.AIPlayer;
import drawers.BoardDrawer;
import drawers.HandDrawer;
import drawers.TrackDrawer;
import gamestate.GameState;
import gamestate.Player;
import gamestate.GameListener;

public class CreditsPanel extends JPanel {
	private static final long serialVersionUID = -3381262603959472520L;

	public CreditsPanel() {
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(255, 213, 93));
		g.fillRect(0, 0, 1920, 1080);

		g.setColor(Color.BLACK);
		Font oldFont = g.getFont();
		g.setFont(new Font("TimesRoman", Font.PLAIN, 90));
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D textSize = fm.getStringBounds("Credits", g);
		int xPos = (1920 - (int) textSize.getWidth()) / 2;
		g.drawString("Credits", xPos, 300);

		g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		fm = g.getFontMetrics();
		textSize = fm.getStringBounds("Quy Nguyen", g);
		xPos = (1920 - (int) textSize.getWidth()) / 2;
		g.drawString("Quy Nguyen", xPos, 450);

		textSize = fm.getStringBounds("Siddarth Srinivas", g);
		xPos = (1920 - (int) textSize.getWidth()) / 2;
		g.drawString("Siddarth Srinivas", xPos, 550);

		textSize = fm.getStringBounds("Andres Luengo", g);
		xPos = (1920 - (int) textSize.getWidth()) / 2;
		g.drawString("Andres Luengo", xPos, 650);

		textSize = fm.getStringBounds("Rhea Dixit", g);
		xPos = (1920 - (int) textSize.getWidth()) / 2;
		g.drawString("Rhea Dixit", xPos, 750);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1920, 1080);
	}
}