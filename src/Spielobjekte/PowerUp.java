package Spielobjekte;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Standard.Bomberman;
import Standard.Spielfeld;

public class PowerUp extends GraphicObject {
	/** Position der Mauer */
	private int posX;
	private int posY;

	/** Größe der Blöcke */
	private int blockLength;

	private Spielfeld sp;

	private String type;
	private String path;

	/**
	 * Mauer
	 * 
	 * @param width
	 * @param height
	 */
	public PowerUp(int posX, int posY, int blockLength, Spielfeld sp,
			String type) {
		this.posX = posX;
		this.posY = posY;
		this.blockLength = blockLength;
		this.sp = sp;
		this.type = type;

		path = type + ".png";
	}

	public void upgrade(Bomberman bm) {
		sp.raster[posX][posY] = 0;
		if (type.equals("bombep")) {
			bm.incAmountOfBombs();
		} else if (type.equals("expRadp")) {
			bm.incExpRad();
		}
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * @param g
	 */
	public void paintObject(Graphics g) {
		Image image = Toolkit.getDefaultToolkit().getImage("img/" + path);
		g.drawImage(image, this.posX, this.posY, this.blockLength,
				this.blockLength, null);
	}
}
