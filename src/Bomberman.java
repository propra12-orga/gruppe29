import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomberman extends JLabel {
	/**
	 * posX, posY Koordinaten des Bomberman; radius Pixelgröße der
	 * Bombermanfigur
	 */
	private int posX;
	private int posY;
	private int radius;
	/** Geschwindigkeit */
	private int steps;
	/** Liste mit allen Bomben des Bombermans */
	public List<Bomb> bombs;
	/** Explosionsradius der Bomben */
	private int expRad;
	/** Anzahl der Bomben in der Liste */
	public int counter;

	/**
	 * Erstellt einen neuen Bomberman und initialisiert die Werte.
	 * 
	 * @param posX
	 *            Startposition
	 * @param posY
	 *            Startposition
	 * @param radius
	 *            Pixelgröße der Bombermanfigur
	 * @param expRad
	 *            Explosionsradius
	 */
	public Bomberman(int posX, int posY, int radius, int expRad) {
		this.posX = posX;
		this.posY = posY;
		this.radius = radius;
		this.expRad = expRad;
		this.bombs = new ArrayList<Bomb>();// (0, 0, this.radius, this.radius);
		this.counter = 0;
		ImageIcon ico = new ImageIcon("Bomberman.png");
		ico.setImage(ico.getImage().getScaledInstance(radius, radius,
				Image.SCALE_DEFAULT));
		this.setIcon(ico);
		setSteps(radius);
	}

	/**
	 * Erstellt einen neuen Bomberman und initialisiert die Werte. Möglichkeit
	 * die Farbe des Bomberman zu ändern.
	 * 
	 * @param posX
	 *            Startposition
	 * @param posY
	 *            Startposition
	 * @param radius
	 *            Pixelgröße der Bombermanfigur
	 * @param expRad
	 *            Explosionsradius
	 * @param color
	 *            Wert für Farbe des Bomberman
	 */
	public Bomberman(int posX, int posY, int radius, int expRad, int color) {
		this(posX, posY, radius, expRad);
		String path_to_Image;
		if (color > 1) // Bomberman Farbwahl
			path_to_Image = "Bomberman2.png";
		else
			path_to_Image = "Bomberman.png";
		ImageIcon ico = new ImageIcon(path_to_Image);
		ico.setImage(ico.getImage().getScaledInstance(radius, radius,
				Image.SCALE_DEFAULT));
		this.setIcon(ico);
		setSteps(radius);
	}

	/**
	 * Fordert eine neue Bombe an.
	 * 
	 * @return Index auf die neu erstellte Bombe
	 */
	public int getNewBombIndex() {
		Bomb bomb = new Bomb(this.posX, this.posY, this.radius, this.expRad);
		this.bombs.add(bomb);
		return this.bombs.lastIndexOf(bomb);
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
	 * Ändert die Y-Position nach oben um steps.
	 */
	public void moveUp() {
		setPosY(getPosY() - steps);
	}

	/**
	 * Ändert die Y-Position nach unten um steps.
	 */
	public void moveDown() {
		setPosY(getPosY() + steps);
	}

	/**
	 * Ändert die X-Position nach links um steps.
	 */
	public void moveLeft() {
		setPosX(getPosX() - steps);
	}

	/**
	 * Ändert die X-Position nach rechts um steps.
	 */
	public void moveRight() {
		setPosX(getPosX() + steps);
	}

	/**
	 * 
	 * @return Pixelgröße des Bomberman
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Setzt Pixelgröße des Bomberman
	 * 
	 * @param radius
	 *            Pixelgröße des Bomberman
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
