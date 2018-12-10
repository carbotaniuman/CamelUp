package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import drawers.BoardDrawer;
import drawers.HandDrawer;
import drawers.TrackDrawer;
import gamestate.GameState;

public class GraphicsPanel extends JPanel {
	private static final long serialVersionUID = -58760937816298343L;
	private GameState gamestate;

	public GraphicsPanel() {
		gamestate = new GameState();
		addMouseListener(new GameListener());
	}

	@Override
	protected void paintComponent(Graphics g) {
		HandDrawer.drawHand(g, gamestate.getCurPlayer());
		BoardDrawer.drawBoard(g, gamestate.getRoundBets(), gamestate.getCurPlayer(),
				gamestate.getPyramid().getRolledDice());
		BoardDrawer.drawPlayerData(g, gamestate.getPlayers());
		BoardDrawer.drawWinBets(g, gamestate.getWinBets());
		BoardDrawer.drawLoseBets(g, gamestate.getLoseBets());
		TrackDrawer.drawTrack(g, gamestate.getTrack(), gamestate.getCurPlayer());
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1920, 1080);
	}

	class GameListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			for (int i = 50; i <= 750; i += 150) {
				if (x > 1050 && x < 1250 && y > i && y < i + 150) {
					gamestate.placeRoundBet(GameState.CAMELCOLORS.get((i - 49) / 150));
					repaint();
				}
			}

		}
	}
}