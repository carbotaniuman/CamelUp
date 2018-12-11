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
		
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1920, 1080);
	}
}