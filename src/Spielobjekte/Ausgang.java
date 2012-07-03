package Spielobjekte;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;

public class Ausgang extends JLabel {

	/** Position der Mauer */
	private int posX;
	private int posY;

	/** Größe der Blöcke */
	private int blockLength;

	/**
	 * Erzeugt einen neuen Ausgang
	 * 
	 * @param posX
	 * @param posY
	 * @param blockLength
	 */
	public Ausgang(int posX, int posY, int blockLength) {
		this.posX = posX;
		this.posY = posY;
		this.blockLength = blockLength;
	}

	/**
	 * Zeichnen des Ausgangs
	 * 
	 * @param g
	 */
	public void paintObject(Graphics g) {
		Image image = Toolkit.getDefaultToolkit().getImage("img/ausgang.png");
		g.drawImage(image, this.posX, this.posY, this.blockLength,
				this.blockLength, null);
		// g.setColor(Color.BLUE);
		// g.fillRect(this.posX, this.posY, this.blockLength, this.blockLength);
	}
}
