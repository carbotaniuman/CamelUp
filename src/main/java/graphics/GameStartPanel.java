package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameStartPanel extends JPanel implements ChangeListener, MouseListener {

	private static final long serialVersionUID = -58760937816298343L;
	private JSpinner spinner;
	private boolean[] choices;

	public GameStartPanel() {
		setLayout(null);
		
		SpinnerModel sm = new SpinnerNumberModel(5, 1, 8, 1);
		spinner = new JSpinner(sm) {
			private static final long serialVersionUID = 8916717439253552751L;

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(100, 25);
			}
		};
		spinner.setToolTipText("Number Of Players");
		spinner.addChangeListener(this);
		spinner.setBounds((1920 - 100) / 2, 350, 100, 25);
		add(spinner);
		addMouseListener(this);

		choices = new boolean[8];
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(255, 213, 93));
		g.fillRect(0, 0, 1920, 1080);

		g.setColor(Color.BLACK);

		Font oldFont = g.getFont();
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D textSize = fm.getStringBounds("Number of Players", g);
		int xPos = (1920 - (int) textSize.getWidth()) / 2;
		g.drawString("Number of Players", xPos, 320);

		g.drawRect((1920 - 300) / 2, 150, 300, 100);
		textSize = fm.getStringBounds("Start Game", g);
		xPos = (300 - (int) textSize.getWidth()) / 2;
		int yPos = (100 - (int) textSize.getHeight()) / 2 + fm.getAscent();
		g.drawString("Start Game", (1920 - 300) / 2 + xPos, 150 + yPos);

		switch ((int) spinner.getValue()) {
		case 8:
			drawBox(g, 7);
		case 7:
			drawBox(g, 6);
		case 6:
			drawBox(g, 5);
		case 5:
			drawBox(g, 4);
		case 4:
			drawBox(g, 3);
		case 3:
			drawBox(g, 2);
		case 2:
			drawBox(g, 1);
		case 1:
			drawBox(g, 0);
			break;
		}
		g.setFont(oldFont);
	}

	private void drawBox(Graphics g, int num) {
		Font oldFont = g.getFont();
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		if (choices[num]) {
			g.setColor(new Color(58, 213, 224));
			g.fillRect((1920 - 300) / 2, 400 + 75 * num, 300, 50);
			FontMetrics fm = g.getFontMetrics();
			Rectangle2D textSize = fm.getStringBounds("Player " + (num + 1) + ": AI", g);
			int xPos = (300 - (int) textSize.getWidth()) / 2;
			int yPos = (50 - (int) textSize.getHeight()) / 2 + fm.getAscent();
			g.setColor(Color.BLACK);
			g.drawString("Player " + (num + 1) + ": AI", (1920 - 300) / 2 + xPos, 400 + 75 * num + yPos);
		} else {
			g.setColor(new Color(40, 214, 112));
			g.fillRect((1920 - 300) / 2, 400 + 75 * num, 300, 50);
			FontMetrics fm = g.getFontMetrics();
			Rectangle2D textSize = fm.getStringBounds("Player " + (num + 1) + ": Human", g);
			int xPos = (300 - (int) textSize.getWidth()) / 2;
			int yPos = (50 - (int) textSize.getHeight()) / 2 + fm.getAscent();
			g.setColor(Color.BLACK);
			g.drawString("Player " + (num + 1) + ": Human", (1920 - 300) / 2 + xPos, 400 + 75 * num + yPos);
		}

		g.drawRect((1920 - 300) / 2, 400 + 75 * num, 300, 50);
		g.setFont(oldFont);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		for (int num = 0; num < Math.min(choices.length, (int) spinner.getValue()); num++) {
			if (x > (1920 - 300) / 2 && (1920 - 300) / 2 + 300 > x && y > 400 + 75 * num && y < 400 + 75 * num + 50) {
				choices[num] = !choices[num];
				repaint();
			}
		}
		
		if(x > (1920 - 300) / 2 && x < (1920 - 300) / 2 + 300 && y > 150 && y < 250) {
			//System.out.println("EEE");
			removeMouseListener(this);
			GamePanel game = new GamePanel(Arrays.copyOf(choices, (int) spinner.getValue()));
			game.setBounds(0, 0, 1920, 1080);
			remove(spinner);
			add(game);
			revalidate();
			repaint();
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1920, 1080);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}