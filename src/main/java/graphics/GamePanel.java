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
		
		for ( int i = 640, count = 2; i > 0; i -= 160, count++ ){
			if ( x > i && x < i + 20 && y > 640 && y < 660){
				gamestate.placeDesertCard(true, count);
				repaint();
			}
			else if ( x > i && x < i + 20 && y > 660 && y < 680){
				gamestate.placeDesertCard(false, count);
				repaint();
			}	
		}
		
		for ( int i = 0, count = 10; i < 800; i += 160, count++)
		{
			if ( x > i && x < i + 20 && y > 0 && y < 20){
				gamestate.placeDesertCard(true, count);
				repaint();
			}
			else if ( x > i && x < i + 20 && y > 20 && y < 40){
				gamestate.placeDesertCard(false, count);
				repaint();
			}
		}
		//checking for tile 2
		if ( x > 640 && x < 680 && y > 480 && y < 500){
			gamestate.placeDesertCard(true, 1);
			repaint();
		}
		else if ( x > 640 && x < 680 && y > 500 && y < 520){
			gamestate.placeDesertCard(false, 1);
			repaint();
		} 
		//checking for tile 16
		if ( x > 640 && x < 680 && y > 160 && y < 180){
			gamestate.placeDesertCard(true, 15);
			repaint();
		}
		else if ( x > 640 && x < 680 && y > 180 && y < 200){
			gamestate.placeDesertCard(false, 15);
			repaint();
		} 
		
		for ( int i = 640, count = 6; i > 0; i -= 160, count++)
		{
			if ( x > 0 && x < 20 && y > i && y < i + 20){
				gamestate.placeDesertCard(true, count);
				repaint();
			}
			else if ( x > 0 && x < 20 && y >  i + 20 && y < i + 40){
				gamestate.placeDesertCard(false, count);
				repaint();
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