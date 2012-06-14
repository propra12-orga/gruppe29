package Spielobjekte;

import java.awt.Graphics;

public abstract class GraphicObject {

	private int posX;
	private int posY;

	/**
	 * 
	 * @param g
	 */
	public abstract void paintObject(Graphics g);

}
