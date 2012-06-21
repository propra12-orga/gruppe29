package MapEditor;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Builder extends JLabel {
	/**
	 * posX, posY Koordinaten des Bomberman; radius Pixelgroeße der
	 * Bombermanfigur
	 */
	private int posX;
	private int posY;
	private int radius;
	/** Geschwindigkeit */
	private int steps;

	/** Explosionsradius der Bomben */

	/**
	 * Erstellt einen neuen Bomberman und initialisiert die Werte. Moeglichkeit
	 * die Farbe des Bomberman zu ändern.
	 * 
	 * @param posX
	 *            Startposition
	 * @param posY
	 *            Startposition
	 * @param radius
	 *            Pixelgroeße der Bombermanfigur
	 * @param expRad
	 *            Explosionsradius
	 * @param color
	 *            Wert fuer Farbe des Bomberman
	 */
	public Builder(int posX, int posY, int radius, int expRad, int color) {
		this.posX = posX;
		this.posY = posY;
		this.radius = radius;
		ImageIcon ico = new ImageIcon("img/Builder.png");
		ico.setImage(ico.getImage().getScaledInstance(radius, radius,
				Image.SCALE_DEFAULT));
		this.setIcon(ico);
		setSteps(radius);
	}

	/**
	 * Zeichnen der Bombe
	 */
	public void paintObject() {
		this.setBounds(this.posX, this.posY, this.radius, this.radius);
		/*
		 * g.setColor(Color.red); g.fillOval(this.posX, this.posY, radius,
		 * radius);
		 */
	}

	/**
	 * Aendert die Y-Position nach oben um steps.
	 */
	public void moveUp() {
		setPosY(getPosY() - steps);
	}

	/**
	 * Aendert die Y-Position nach unten um steps.
	 */
	public void moveDown() {
		setPosY(getPosY() + steps);
	}

	/**
	 * Aendert die X-Position nach links um steps.
	 */
	public void moveLeft() {
		setPosX(getPosX() - steps);
	}

	/**
	 * Aendert die X-Position nach rechts um steps.
	 */
	public void moveRight() {
		setPosX(getPosX() + steps);
	}

	/**
	 * 
	 * @return Pixelgroeße des Bomberman
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Setzt Pixelgroeße des Bomberman
	 * 
	 * @param radius
	 *            Pixelgroeße des Bomberman
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * 
	 * @return X-Position des Bomberman
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Setzt X-Position des Bomberman
	 * 
	 * @param posX
	 *            X-Position des Bomberman
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * 
	 * @return Y-Position des Bomberman
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Setzt Y-Position des Bomberman
	 * 
	 * @param posY
	 *            Y-Position des Bomberman
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * 
	 * @return Geschwindigkeit des Bomberman
	 */
	public int getSteps() {
		return steps;
	}

	/**
	 * Setze Geschwindigkeit des Bomberman
	 * 
	 * @param steps
	 *            Geschwindigkeit des Bomberman
	 */
	public void setSteps(int steps) {
		this.steps = steps;
	}

}
