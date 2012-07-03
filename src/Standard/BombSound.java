package Standard;

import java.io.BufferedInputStream;
import java.io.File;
import java.util.TimerTask;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class BombSound extends TimerTask {
	/**
	 * Erzeugt ein neues Objekt von BombSound.
	 */
	public BombSound() {

	}

	/**
	 * Bombensound öffnen und abspielen.
	 */
	public void run() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("sounds/boom2.wav"));
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					audioInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
			clip.start();
			Thread.sleep(1000);
			clip.stop();
			clip.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}