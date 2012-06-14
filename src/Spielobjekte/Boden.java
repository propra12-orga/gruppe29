package Spielobjekte;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Boden extends GraphicObject {
	/** Position der Mauer */
	private int posX;
	private int posY;

	/** Größe der Blöcke */
	private int blockLength;

	/**
	 * Mauer
	 * 
	 * @param width
	 * @param height
	 */
	public Boden(int posX, int posY, int blockLength) {
		this.posX = posX;
		this.posY = posY;
		this.blockLength = blockLength;
	}

	/**
	 * @param g
	 */
	public void paintObject(Graphics g) {
		Image image = Toolkit.getDefaultToolkit().getImage("boden.png");
		g.drawImage(image, this.posX, this.posY, this.blockLength,
				this.blockLength, null);

		// g.setColor(Color.WHITE);
		// g.fillRect(i, j, blockLength, blockLength);
	}
}
