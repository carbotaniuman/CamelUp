import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import graphics.MenuPanel;

public class GraphicsRunner {
	public static void main(String[] args) throws IOException {
		JFrame f = new JFrame();
		f.add(new MenuPanel());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setUndecorated(true);
		f.setIconImage(ImageIO.read(ClassLoader.getSystemClassLoader().getResource("Icon.ico")));
		f.setVisible(true);
		f.requestFocus();
	}
}
