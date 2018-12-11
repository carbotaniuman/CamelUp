package drawers;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import gamestate.Player;
import gamestate.Track;

public class TrackDrawer {
	public static final BufferedImage camelImage;

	static {
		try {
			camelImage = scale(ImageIO.read(new File("Camel.png")), 49, 32);
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
		for (int i1 = 320; i1 < 640; i1 += 160) {
			g.setColor(new Color(255, 245, 175));
			g.fillRect(x1, i1, 160, 160);
			g.setColor(Color.BLACK);
			g.drawRect(x1, i1, 160, 160);
			player.getDesertCard();
			if (!Optional.empty().isPresent()) {
				if (track.canPlaceCard(i++)) {
					drawPlus(g, x1, i1);
				}
			} else
				drawMiniDesert(g, track.getTile(i - 1).getDesertCard().get().getMoveNum(), x1, i1);
		}
		for (int i2 = x1; i2 > 0; i2 -= 160) {
			g.setColor(new Color(255, 245, 175));
			g.fillRect(i2, y1, 160, 160);
			g.setColor(Color.BLACK);
			g.drawRect(i2, y1, 160, 160);
			player.getDesertCard();
			if (!Optional.empty().isPresent()) {
				if (track.canPlaceCard(i++)) {
					drawPlus(g, i2, y1);
				}
			} else
				drawMiniDesert(g, track.getTile(i - 1).getDesertCard().get().getMoveNum(), i2, y1);
		}
		for (int i3 = 640; i3 > 0; i3 -= 160) {
			g.setColor(new Color(255, 245, 175));
			g.fillRect(x2, i3, 160, 160);
			g.setColor(Color.BLACK);
			g.drawRect(x2, i3, 160, 160);
			player.getDesertCard();
			if (!Optional.empty().isPresent()) {
				if (track.canPlaceCard(i++)) {
					drawPlus(g, x2, i3);
				}
			} else
				drawMiniDesert(g, track.getTile(i - 1).getDesertCard().get().getMoveNum(), x2, i3);
		}
		for (int i4 = 0; i4 < 640; i4 += 160) {
			g.setColor(new Color(255, 245, 175));
			g.fillRect(i4, y2, 160, 160);
			g.setColor(Color.BLACK);
			g.drawRect(i4, y2, 160, 160);
			player.getDesertCard();
			if (!Optional.empty().isPresent()) {
				if (track.canPlaceCard(i++)) {
					drawPlus(g, i4, y2);
				}
			} else
				drawMiniDesert(g, track.getTile(i - 1).getDesertCard().get().getMoveNum(), i4, y2);
		}
		for (int i5 = 0; i5 < 320; i5 += 160) {
			g.setColor(new Color(255, 245, 175));
			g.fillRect(x1, i5, 160, 160);
			g.setColor(Color.BLACK);
			g.drawRect(x1, i5, 160, 160);
			player.getDesertCard();
			if (!Optional.empty().isPresent()) {
				if (track.canPlaceCard(i++)) {
					drawPlus(g, x1, i5);
				}
			} else
				drawMiniDesert(g, track.getTile(i - 1).getDesertCard().get().getMoveNum(), x1, i5);
		}
//		g.drawImage(camelImage, 0, 0, null);
		g.drawImage(blackToColor(camelImage, Color.RED), 0, 0, null);
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
			graphics2D.drawImage(imageToColor, 0, 0, imageToColor.getWidth(), imageToColor.getHeight(), 0, 0, imageToColor.getWidth(),
					imageToColor.getHeight(), null);
			graphics2D.dispose();
		}
		return coloredImage;
	}

	private static void drawPlus(Graphics g, int x, int y) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 20, 40);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 20, 40);
		g.drawLine(x, y + 20, x + 20, y + 20);
		g.setFont(new Font("Serif", Font.PLAIN, 20));
		g.drawString("+", x + 5, y + 17);
		g.drawString("-", x + 7, y + 36);
	}

	private static void drawMiniDesert(Graphics g, int val, int x, int y) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 30, 20);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 30, 20);
		g.setFont(new Font("Serif", Font.PLAIN, 20));
		g.drawString(val + "", x + 10, y + 20);
	}
}
