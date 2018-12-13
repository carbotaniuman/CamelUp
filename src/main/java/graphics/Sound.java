package graphics;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound implements Runnable {

	private File niceMusic;
	private AudioInputStream streamer;
	private Clip clop;

	public Sound() {
		try {
			niceMusic = new File("src/Sounds/musics.wav");
			streamer = AudioSystem.getAudioInputStream(niceMusic);
			clop = AudioSystem.getClip();
			clop.open(streamer);
		} catch (Exception b) {
			b.printStackTrace();
		}
		Thread ap = new Thread(this);
		ap.start();
	}

	@Override
	public void run() {
		clop.loop(Clip.LOOP_CONTINUOUSLY);
		try {
			Thread.sleep(10000);
		} catch (Exception b) {
		}
	}
}