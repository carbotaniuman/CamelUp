package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import ai.AIPlayer;
import drawers.BoardDrawer;
import drawers.HandDrawer;
import drawers.TrackDrawer;
import gamestate.GameState;
import gamestate.TurnListener;

public class GamePanel extends JPanel implements MouseListener, TurnListener {
	private static final long serialVersionUID = -58760937816298343L;
	private GameState gamestate;

	public GamePanel() {
		this(false, false, false, false, false);
	}

	public GamePanel(boolean... b) {
		gamestate = new GameState(b);
		gamestate.addTurnListener(this);
		addMouseListener(this);

		gamestate.startGame();
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

	@Override
	public void mousePressed(MouseEvent e) {
		if (!gamestate.isGameStarted() || gamestate.isGameEnded()) {
			return;
		}

		if (gamestate.getCurPlayer() instanceof AIPlayer) {
			return;
		}

		int x = e.getX();
		int y = e.getY();
		for (int i = 50, count = 0; i <= 750; i += 150, count++) {
			if (x > 1050 && x < 1250 && y > i && y < i + 80) {
				gamestate.placeRoundBet(GameState.CAMELCOLORS.get(count));
			}
		}

		for (int i = 1300, count = 0; i < 1920; i += 124, count++) {
			if (x > i && x < i + 124 && y > 0 && y < 40) {
				gamestate.placeWinBet(GameState.CAMELCOLORS.get(count));
			}

			if (x > i && x < i + 124 && y > 40 && y < 80) {
				gamestate.placeLoseBet(GameState.CAMELCOLORS.get(count));
			}
		}

		if (x > 160 && x < 640 && y > 160 && y < 640) {
			gamestate.moveCamel();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void turnPassed() {
		repaint();
	}
}