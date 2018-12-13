package drawers;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gamestate.Player;
import gamestate.Track;
import immutable.Camel;

public class TrackDrawer {
	public static final BufferedImage camelImage;
	public static final BufferedImage origImage;

	static {
		try {
			camelImage = scale(ImageIO.read(new File("Camel.png")), 61, 40);
			origImage = ImageIO.read(new File("Camel.png"));
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}

	public static void drawTrack(Graphics g, Track track, Player player) {
		// drawing the pyramid
		g.setColor(Color.ORANGE);
		g.fillRect(160, 160, 480, 480);
		g.setColor(Color.BLACK);
		g.drawLine(160, 160, 640, 640);
		g.drawLine(640, 160, 160, 640);
		g.drawRect(240, 240, 320, 320);
		g.drawRect(200, 200, 400, 400);
		g.drawRect(280, 280, 240, 240);
		g.drawRect(320, 320, 160, 160);
		g.setColor(new Color(135, 206, 235));
		g.fillRect(321, 321, 159, 159);

		// drawing the track
		int x1 = 640;
		int x2 = 0;
		int y1 = 640;
		int y2 = 0;
		int i = 0;
		for (int i1 = 320; i1 < 640; i1 += 160, i++) {
			g.setColor(new Color(255, 245, 175));
			g.fillRect(x1, i1, 160, 160);
			g.setColor(Color.BLACK);
			g.drawRect(x1, i1, 160, 160);

			if (i == 0) {
				FontMetrics fm = g.getFontMetrics();
				Rectangle2D textSize = fm.getStringBounds("Start", g);
				int xPos = (160 - (int) textSize.getWidth()) / 2;
				int yPos = fm.getAscent();
				g.drawString("Start", x1 + xPos, i1 + yPos);
			}

			FontMetrics fm = g.getFontMetrics();
			Rectangle2D textSize = fm.getStringBounds(String.valueOf(i + 1), g);
			int xPos = (160 - (int) textSize.getWidth()) / 2;
			int yPos = (160 - (int) textSize.getHeight()) / 2 + fm.getAscent();
			g.drawString(String.valueOf(i + 1), x1 + xPos, i1 + yPos);

			for (Camel c : track.getTile(i).getCamels()) {
				drawCamel(g, c.getColor(), x1, i1,
						track.getTile(i).getCamels().size() - 1 - track.getTile(i).getCamelStackPos(c));
			}

			if (track.canPlaceCard(i) || (player.getDesertCard().isPresent()
					&& track.canMoveCard(player.getDesertCard().get().getTile(), i)))
				drawPlus(g, x1, i1);
			else if (track.getTile(i).getDesertCard().isPresent())
				CardDrawer.drawPlacedDesertCard(g, track.getTile(i).getDesertCard().get().getMoveNum(), x1, i1,
						track.getTile(i).getDesertCard().get().getPlayer(), player);
		}
		for (int i2 = 640; i2 > 0; i2 -= 160, i++) {
			g.setColor(new Color(255, 245, 175));
			g.fillRect(i2, y1, 160, 160);
			g.setColor(Color.BLACK);
			g.drawRect(i2, y1, 160, 160);

			FontMetrics fm = g.getFontMetrics();
			Rectangle2D textSize = fm.getStringBounds(String.valueOf(i + 1), g);
			int xPos = (160 - (int) textSize.getWidth()) / 2;
			int yPos = (160 - (int) textSize.getHeight()) / 2 + fm.getAscent();
			g.drawString(String.valueOf(i + 1), i2 + xPos, y1 + yPos);

			for (Camel c : track.getTile(i).getCamels()) {
				drawCamel(g, c.getColor(), i2, y1,
						track.getTile(i).getCamels().size() - 1 - track.getTile(i).getCamelStackPos(c));
			}

			if (track.canPlaceCard(i)
					|| (player.getDesertCard().isPresent()
							&& track.canMoveCard(player.getDesertCard().get().getTile(), i))
					|| (player.getDesertCard().isPresent()
							&& track.canMoveCard(player.getDesertCard().get().getTile(), i)))
				drawPlus(g, i2, y1);
			else if (track.getTile(i).getDesertCard().isPresent())
				CardDrawer.drawPlacedDesertCard(g, track.getTile(i).getDesertCard().get().getMoveNum(), i2, y1,
						track.getTile(i).getDesertCard().get().getPlayer(), player);
		}
		for (int i3 = 640; i3 > 0; i3 -= 160, i++) {
			g.setColor(new Color(255, 245, 175));
			g.fillRect(x2, i3, 160, 160);
			g.setColor(Color.BLACK);
			g.drawRect(x2, i3, 160, 160);

			for (Camel c : track.getTile(i).getCamels()) {
				drawCamel(g, c.getColor(), x2, i3,
						track.getTile(i).getCamels().size() - 1 - track.getTile(i).getCamelStackPos(c));
			}

			FontMetrics fm = g.getFontMetrics();
			Rectangle2D textSize = fm.getStringBounds(String.valueOf(i + 1), g);
			int xPos = (160 - (int) textSize.getWidth()) / 2;
			int yPos = (160 - (int) textSize.getHeight()) / 2 + fm.getAscent();
			g.drawString(String.valueOf(i + 1), x2 + xPos, i3 + yPos);

			if (track.canPlaceCard(i) || (player.getDesertCard().isPresent()
					&& track.canMoveCard(player.getDesertCard().get().getTile(), i)))
				drawPlus(g, x2, i3);
			else if (track.getTile(i).getDesertCard().isPresent())
				CardDrawer.drawPlacedDesertCard(g, track.getTile(i).getDesertCard().get().getMoveNum(), x2, i3,
						track.getTile(i).getDesertCard().get().getPlayer(), player);
		}
		for (int i4 = 0; i4 < 640; i4 += 160, i++) {
			g.setColor(new Color(255, 245, 175));
			g.fillRect(i4, y2, 160, 160);
			g.setColor(Color.BLACK);
			g.drawRect(i4, y2, 160, 160);

			for (Camel c : track.getTile(i).getCamels()) {
				drawCamel(g, c.getColor(), i4, y2,
						track.getTile(i).getCamels().size() - 1 - track.getTile(i).getCamelStackPos(c));
			}

			FontMetrics fm = g.getFontMetrics();
			Rectangle2D textSize = fm.getStringBounds(String.valueOf(i + 1), g);
			int xPos = (160 - (int) textSize.getWidth()) / 2;
			int yPos = (160 - (int) textSize.getHeight()) / 2 + fm.getAscent();
			g.drawString(String.valueOf(i + 1), i4 + xPos, y2 + yPos);

			if (track.canPlaceCard(i) || (player.getDesertCard().isPresent()
					&& track.canMoveCard(player.getDesertCard().get().getTile(), i)))
				drawPlus(g, i4, y2);
			else if (track.getTile(i).getDesertCard().isPresent())
				CardDrawer.drawPlacedDesertCard(g, track.getTile(i).getDesertCard().get().getMoveNum(), i4, y2,
						track.getTile(i).getDesertCard().get().getPlayer(), player);
		}
		for (int i5 = 0; i5 < 320; i5 += 160, i++) {
			g.setColor(new Color(255, 245, 175));
			g.fillRect(x1, i5, 160, 160);
			g.setColor(Color.BLACK);
			g.drawRect(x1, i5, 160, 160);

			for (Camel c : track.getTile(i).getCamels()) {
				drawCamel(g, c.getColor(), x1, i5,
						track.getTile(i).getCamels().size() - 1 - track.getTile(i).getCamelStackPos(c));
			}

			if (i == 15) {
				FontMetrics fm = g.getFontMetrics();
				Rectangle2D textSize = fm.getStringBounds("End", g);
				int xPos = (160 - (int) textSize.getWidth()) / 2;
				int yPos = fm.getDescent();
				g.drawString("End", x1 + xPos, i5 + 160 - yPos);
			}

			FontMetrics fm = g.getFontMetrics();
			Rectangle2D textSize = fm.getStringBounds(String.valueOf(i + 1), g);
			int xPos = (160 - (int) textSize.getWidth()) / 2;
			int yPos = (160 - (int) textSize.getHeight()) / 2 + fm.getAscent();
			g.drawString(String.valueOf(i + 1), x1 + xPos, i5 + yPos);

			if (track.canPlaceCard(i) || (player.getDesertCard().isPresent()
					&& track.canMoveCard(player.getDesertCard().get().getTile(), i)))
				drawPlus(g, x1, i5);
			else if (track.getTile(i).getDesertCard().isPresent())
				CardDrawer.drawPlacedDesertCard(g, track.getTile(i).getDesertCard().get().getMoveNum(), x1, i5,
						track.getTile(i).getDesertCard().get().getPlayer(), player);
		}
	}

	public static BufferedImage scale(BufferedImage imageToScale, int dWidth, int dHeight) {
		BufferedImage scaledImage = null;
		if (imageToScale != null) {
			scaledImage = new BufferedImage(dWidth, dHeight, imageToScale.getType());
			Graphics2D graphics2D = scaledImage.createGraphics();
			graphics2D.drawImage(imageToScale, 0, 0, dWidth, dHeight, null);
			graphics2D.dispose();
		}
		return scaledImage;
	}

	public static BufferedImage blackToColor(BufferedImage imageToColor, Color c) {
		BufferedImage coloredImage = null;
		if (imageToColor != null) {
			coloredImage = new BufferedImage(imageToColor.getWidth(), imageToColor.getHeight(), imageToColor.getType());
			Graphics2D graphics2D = coloredImage.createGraphics();
			graphics2D.setColor(c);
			graphics2D.fillRect(0, 0, imageToColor.getWidth(), imageToColor.getHeight());

			// paint original with composite
			graphics2D.setComposite(AlphaComposite.DstIn);
			graphics2D.drawImage(imageToColor, 0, 0, imageToColor.getWidth(), imageToColor.getHeight(), 0, 0,
					imageToColor.getWidth(), imageToColor.getHeight(), null);
			graphics2D.dispose();
		}
		return coloredImage;
	}

	private static void drawCamel(Graphics g, Color c, int x, int y, int stackPos) {
		g.drawImage(blackToColor(camelImage, c), x + (160 - 49) / 2, y + 120 - 26 * stackPos, null);
	}

	public static void drawCamel(BufferedImage img, Graphics g, Color c, int x, int y) {
		g.drawImage(blackToColor(img, c), x, y, null);
	}

	private static void drawPlus(Graphics g, int x, int y) {
		Font oldFont = g.getFont();
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 20, 40);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 20, 40);
		g.drawLine(x, y + 20, x + 20, y + 20);
		g.setFont(new Font("Serif", Font.PLAIN, 20));
		g.drawString("+", x + 5, y + 17);
		g.drawString("-", x + 7, y + 36);
		g.setFont(oldFont);
	}
}
