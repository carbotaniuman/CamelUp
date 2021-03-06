package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ai.AIPlayer;
import drawers.BoardDrawer;
import drawers.HandDrawer;
import drawers.TrackDrawer;
import gamestate.GameListener;
import gamestate.GameState;
import gamestate.Player;

public class GamePanel extends JPanel implements MouseListener, GameListener {
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
		if (gamestate.isGameEnded()) {
			g.setColor(new Color(255, 213, 93));
			g.fillRect(0, 0, 1920, 1080);

			g.setColor(Color.BLACK);
			Font oldFont = g.getFont();
			g.setFont(new Font("TimesRoman", Font.PLAIN, 250));
			FontMetrics fm = g.getFontMetrics();
			Rectangle2D textSize = fm.getStringBounds("Game Over", g);
			int xPos = (1920 - (int) textSize.getWidth()) / 2;
			g.drawString("Game Over", xPos, 280);

			List<Player> players = new ArrayList<>(gamestate.getPlayers());
			int max = Integer.MIN_VALUE;
			Player winner = null;
			for (int i1 = 0; i1 < players.size(); i1++) {
				Player player = players.get(i1);
				if (player.getMoney() > max) {
					max = player.getMoney();
					winner = player;
				}
			}
			g.setFont(new Font("Monospaced", Font.BOLD, 50));
			String s = "PLAYER WINNER: " + winner.getName() + " " + winner.getMoney() + " EP";
			fm = g.getFontMetrics();
			textSize = fm.getStringBounds(s, g);
			xPos = (1920 - (int) textSize.getWidth()) / 2;
			g.drawString(s, xPos, 360 + 66);
			players.remove(winner);
			for (int i = 0; i < players.size(); i++) {
				Player plyr = players.get(i);
				if (plyr.getMoney() == winner.getMoney()) {
					g.drawString(
							"PLAYER WINNER: " + String.format("%-11s %-5s", plyr.getName(), plyr.getMoney() + " EP"),
							xPos, 360 + 66 + 50 * (i + 1));
				}
			}
			TrackDrawer.drawCamel(TrackDrawer.origImage, g, gamestate.getCamelRankings().get(0).getColor(), 1000, 700);
			g.setColor(Color.BLACK);
			g.drawString("CAMEL WINNER", 1150, 930);
			TrackDrawer.drawCamel(TrackDrawer.origImage, g,
					gamestate.getCamelRankings().get(gamestate.getCamelRankings().size() - 1).getColor(), 160, 700);
			g.setColor(Color.BLACK);
			g.drawString("CAMEL LOSER", 285, 930); // 1140, 950
			g.setFont(oldFont);
		} else {

			HandDrawer.drawHand(g, gamestate.getCurPlayer());
			BoardDrawer.drawBoard(g, gamestate.getRoundBets(), gamestate.getCurPlayer(),
					gamestate.getPyramid().getRolledDice());
			BoardDrawer.drawPlayerData(g, gamestate.getPlayers());
			BoardDrawer.drawWinBets(g, gamestate.getWinBets());
			BoardDrawer.drawLoseBets(g, gamestate.getLoseBets());
			TrackDrawer.drawTrack(g, gamestate.getTrack(), gamestate.getCurPlayer());

			Font oldFont = g.getFont();
			if (gamestate.getCurPlayer() instanceof AIPlayer) {
				g.setColor(new Color(18, 67, 183));
				g.setFont(new Font("TimesRoman", Font.ITALIC, 120));
				FontMetrics fm = g.getFontMetrics();
				Rectangle2D textSize = fm.getStringBounds("AI Player's Turn", g);
				int xPos = (1920 - (int) textSize.getWidth()) / 2;
				int yPos = (1080 - (int) textSize.getHeight()) / 2 + fm.getAscent();
				g.drawString("AI Player's Turn", xPos, yPos);
			} /*
				 * else if (gamestate.isSleeping()) { g.setColor(new Color(18, 67, 183));
				 * g.setFont(new Font("TimesRoman", Font.ITALIC, 120)); FontMetrics fm =
				 * g.getFontMetrics(); Rectangle2D textSize =
				 * fm.getStringBounds("Processing Turn", g); int xPos = (1920 - (int)
				 * textSize.getWidth()) / 2; int yPos = (1080 - (int) textSize.getHeight()) / 2
				 * + fm.getAscent(); g.drawString("Processing Turn", xPos, yPos); }
				 */
			g.setFont(oldFont);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1920, 1080);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!gamestate.isGameStarted() || gamestate.isGameEnded() || gamestate.isSleeping()) {
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

		for (int i = 640, count = 2; i > 0; i -= 160, count++) {
			if (x > i && x < i + 160 && y > 640 && y < 680) {
				if (gamestate.getTrack().getTile(count).getDesertCard().isPresent()) {
					if (gamestate.getCurPlayer() == gamestate.getTrack().getTile(count).getDesertCard().get()
							.getPlayer()) {
						if (x > i && x < i + 160 && y > 660 && y < 680)
							gamestate.flipDesertCard(gamestate.getCurPlayer().getDesertCard().get().getTile());
					}
				} else if (x > i && x < i + 20 && y > 640 && y < 660) {
					gamestate.placeDesertCard(true, count);
				} else if (x > i && x < i + 20 && y > 660 && y < 680) {
					gamestate.placeDesertCard(false, count);
				}
			}
		}

		for (int i = 0, count = 10; i < 800; i += 160, count++) {
			if (x > i && x < i + 160 && y > 0 && y < 40) {
				if (gamestate.getTrack().getTile(count).getDesertCard().isPresent()) {
					if (gamestate.getCurPlayer() == gamestate.getTrack().getTile(count).getDesertCard().get()
							.getPlayer()) {
						if (x > i && x < i + 160 && y > 20 && y < 40)
							gamestate.flipDesertCard(gamestate.getCurPlayer().getDesertCard().get().getTile());
					}
				} else if (x > i && x < i + 20 && y > 0 && y < 20) {
					gamestate.placeDesertCard(true, count);
				} else if (x > i && x < i + 20 && y > 20 && y < 40) {
					gamestate.placeDesertCard(false, count);
				}
			}
		}
		// checking for tile 2
		if (x > 640 && x < 800 && y > 480 && y < 520) {
			if (gamestate.getTrack().getTile(1).getDesertCard().isPresent()) {
				if (gamestate.getCurPlayer() == gamestate.getTrack().getTile(1).getDesertCard().get().getPlayer()) {
					if (x > 640 && x < 800 && y > 500 && y < 520)
						gamestate.flipDesertCard(gamestate.getCurPlayer().getDesertCard().get().getTile());
				}
			} else if (x > 640 && x < 660 && y > 480 && y < 500) {
				gamestate.placeDesertCard(true, 1);
			} else if (x > 640 && x < 660 && y > 500 && y < 520) {
				gamestate.placeDesertCard(false, 1);
			}
		}
		// checking for tile 16
		if (x > 640 && x < 800 && y > 160 && y < 200) {
			if (gamestate.getTrack().getTile(15).getDesertCard().isPresent()) {
				if (gamestate.getCurPlayer() == gamestate.getTrack().getTile(15).getDesertCard().get().getPlayer()) {
					if (x > 640 && x < 800 && y > 180 && y < 200)
						gamestate.flipDesertCard(gamestate.getCurPlayer().getDesertCard().get().getTile());
				}
			} else if (x > 640 && x < 660 && y > 160 && y < 180) {
				gamestate.placeDesertCard(true, 15);
			} else if (x > 640 && x < 660 && y > 180 && y < 200) {
				gamestate.placeDesertCard(false, 15);
			}
		}
		for (int i = 640, count = 6; i > 0; i -= 160, count++) {
			if (x > 0 && x < 160 && y > i && y < i + 40) {
				if (gamestate.getTrack().getTile(count).getDesertCard().isPresent()) {
					if (gamestate.getCurPlayer() == gamestate.getTrack().getTile(count).getDesertCard().get()
							.getPlayer()) {
						if (x > 0 && x < 160 && y > i + 20 && y < i + 40)
							gamestate.flipDesertCard(gamestate.getCurPlayer().getDesertCard().get().getTile());
					}
				} else if (x > 0 && x < 20 && y > i && y < i + 20) {
					gamestate.placeDesertCard(true, count);
				} else if (x > 0 && x < 20 && y > i + 20 && y < i + 40) {
					gamestate.placeDesertCard(false, count);
				}
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
	public void gameChanged() {
		repaint();
	}
}