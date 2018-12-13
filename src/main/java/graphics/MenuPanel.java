package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -58760937816298343L;
	private JPanel curShow;
	private MenuListener listener;
	private Clip clip;

	public MenuPanel() {
		setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

		listener = new MenuListener();
		addMouseListener(listener);
		
		setAudioClip("menu.wav");
		
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "BackToMenu");
		getActionMap().put("BackToMenu", new AbstractAction() {
			private static final long serialVersionUID = 7596895278099231841L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (curShow != null) {
					remove(curShow);
					curShow = null;
					revalidate();
					addMouseListener(listener);
					repaint();
					setAudioClip("menu.wav");
				}
			}
		});
	}
	
	private void setAudioClip(String path) {
		if(clip != null) {
			clip.stop();
			clip.close();
		}
		
		InputStream is = new BufferedInputStream(ClassLoader.getSystemClassLoader().getResourceAsStream(path));
		
		try {
			AudioInputStream temp = AudioSystem.getAudioInputStream(is);
			clip = AudioSystem.getClip();
			clip.open(temp);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			throw new AssertionError(e);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(255, 213, 93));
		g.fillRect(0, 0, 1920, 1080);

		g.setColor(Color.BLACK);

		Font oldFont = g.getFont();
		g.setFont(new Font("TimesRoman", Font.PLAIN, 90));
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D textSize = fm.getStringBounds("Camel Up", g);
		int xPos = (1920 - (int) textSize.getWidth()) / 2;
		g.drawString("Camel Up", xPos, 300);

		g.drawRect((1920 - 300) / 2, 400, 300, 100);
		g.drawRect((1920 - 300) / 2, 550, 300, 100);
		g.drawRect((1920 - 300) / 2, 700, 300, 100);

		g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		fm = g.getFontMetrics();
		textSize = fm.getStringBounds("Play Game", g);
		xPos = (300 - (int) textSize.getWidth()) / 2;
		int yPos = (100 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString("Play Game", (1920 - 300) / 2 + xPos, 400 + yPos);

		textSize = fm.getStringBounds("Credits", g);
		xPos = (300 - (int) textSize.getWidth()) / 2;
		yPos = (100 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString("Credits", (1920 - 300) / 2 + xPos, 550 + yPos);
		textSize = fm.getStringBounds("Exit", g);
		xPos = (300 - (int) textSize.getWidth()) / 2;
		yPos = (100 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString("Exit", (1920 - 300) / 2 + xPos, 700 + yPos);

		g.setFont(oldFont);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1920, 1080);
	}

	private class MenuListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			if (x > (1920 - 300) / 2 && x < (1920 - 300) / 2 + 300 && y > 400 && y < 500) {
				removeMouseListener(listener);
				curShow = new GameStartPanel();
				add(curShow);
				revalidate();
				repaint();
				setAudioClip("game.wav");
			}

			if (x > (1920 - 300) / 2 && x < (1920 - 300) / 2 + 300 && y > 550 && y < 650) {
				removeMouseListener(listener);
				curShow = new CreditsPanel();
				add(curShow);
				revalidate();
				repaint();
				setAudioClip("credits.wav");
			}

			if (x > (1920 - 300) / 2 && x < (1920 - 300) / 2 + 300 && y > 700 && y < 800) {
				System.exit(0);
			}
		}
	}
}