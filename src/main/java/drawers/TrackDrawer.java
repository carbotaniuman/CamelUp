package drawers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Optional;

import gamestate.Player;
import gamestate.Track;

public class TrackDrawer {
	public static void drawTrack(Graphics g, Track track, Player player) {
		// drawing the pyramid
		g.setColor(Color.ORANGE);
		g.fillRect(160, 160, 640, 640);
		g.setColor(Color.BLACK);
		g.drawLine(160, 160, 640, 640);
		g.drawLine(640, 160, 160, 640);
		g.drawRect(240, 240, 320, 320);
		g.drawRect(200, 200, 400, 400);
		g.drawRect(280, 280, 240, 240);
		g.drawRect(320, 320, 160, 160);
		g.setColor(Color.ORANGE);
		g.fillRect(321, 321, 159, 159);
		// drawing the track
		int x1 = 640;
		int x2 = 0;
		int y1 = 640;
		int y2 = 0;
		int i = 0;
		for (int i1 = 320; i1 < 640; i1 += 160) {
			g.setColor(new Color(221, 129, 65));
			g.fillRect(x1, i1, 160, 160);
			g.setColor(Color.BLACK);
			g.drawRect(x1, i1, 160, 160);
			player.getDesertCard();
			if (!Optional.empty().isPresent()) {
				if (track.canPlaceCard(i++)) {
					drawPlus(g, i, x1, i1);
				}
			}
		}
		for (int i2 = x1; i2 > 0; i2 -= 160) {
			g.setColor(new Color(221, 129, 65));
			g.fillRect(i2, y1, 160, 160);
			g.setColor(Color.BLACK);
			g.drawRect(i2, y1, 160, 160);
			player.getDesertCard();
			if (!Optional.empty().isPresent()) {
				if (track.canPlaceCard(i++)) {
					drawPlus(g, i, i2, y1);
				}
			}
		}
		for (int i3 = 640; i3 > 0; i3 -= 160) {
			g.setColor(new Color(221, 129, 65));
			g.fillRect(x2, i3, 160, 160);
			g.setColor(Color.BLACK);
			g.drawRect(x2, i3, 160, 160);
			player.getDesertCard();
			if (!Optional.empty().isPresent()) {
				if (track.canPlaceCard(i++)) {
					drawPlus(g, i, x2, i3);
				}
			}
		}
		for (int i4 = 0; i4 < 640; i4 += 160) {
			g.setColor(new Color(221, 129, 65));
			g.fillRect(i4, y2, 160, 160);
			g.setColor(Color.BLACK);
			g.drawRect(i4, y2, 160, 160);
			player.getDesertCard();
			if (!Optional.empty().isPresent()) {
				if (track.canPlaceCard(i++)) {
					drawPlus(g, i, i4, y2);
				}
			}
		}
		for (int i5 = 0; i5 < 320; i5 += 160) {
			g.setColor(new Color(221, 129, 65));
			g.fillRect(x1, i5, 160, 160);
			g.setColor(Color.BLACK);
			g.drawRect(x1, i5, 160, 160);
			player.getDesertCard();
			if (!Optional.empty().isPresent()) {
				if (track.canPlaceCard(i++)) {
					drawPlus(g, i, x1, i5);
				}
			}
		}
	}

	private static void drawPlus(Graphics g, int i, int x, int y) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 20, 40);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 20, 40);
		g.drawLine(x, y + 20, x + 20, y + 20);
		g.setFont(new Font("Serif", Font.PLAIN, 20));
		g.drawString(i+"", x + 5, y + 17);
		g.drawString("-", x + 7, y + 36);
	}

}
