package graphics;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import drawers.BoardDrawer;
import drawers.HandDrawer;
import gamestate.GameState;

public class GraphicsPanel extends JPanel {
	private static final long serialVersionUID = -58760937816298343L;
	
	private GameState gamestate;
	public GraphicsPanel(GameState gamestate) {
		this.gamestate = gamestate;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		HandDrawer.drawHand(g, gamestate.getCurPlayer(), gamestate.getPlayers());
		BoardDrawer.drawBoard(g, gamestate.getRoundBets(), gamestate.getCurPlayer(), gamestate.getPyramid().getNotRolledDice());
		BoardDrawer.drawWinBets(g, gamestate.getWinBets());
		BoardDrawer.drawLoseBets(g, gamestate.getLoseBets());
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1920, 1080);
	}
}
