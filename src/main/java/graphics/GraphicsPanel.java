package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import drawers.BoardDrawer;
import drawers.HandDrawer;
import drawers.TrackDrawer;
import gamestate.GameState;

public class GraphicsPanel extends JPanel {
	private static final long serialVersionUID = -58760937816298343L;
	private GameState gamestate = new GameState();
	public GraphicsPanel() {
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		HandDrawer.drawHand(g, gamestate.getCurPlayer());
		BoardDrawer.drawBoard(g, gamestate.getRoundBets(), gamestate.getCurPlayer(), gamestate.getPyramid().getRolledDice(), gamestate.getPlayers());
		BoardDrawer.drawWinBets(g, gamestate.getWinBets());
		BoardDrawer.drawLoseBets(g, gamestate.getLoseBets());
		TrackDrawer.drawTrack(g, gamestate.getTrack(), gamestate.getCurPlayer());
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1920, 1080);
	}
}