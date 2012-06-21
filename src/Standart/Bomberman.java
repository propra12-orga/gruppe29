package Standart;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomberman extends JLabel {
	/**
	 * posX, posY Koordinaten des Bomberman; radius Pixelgroeße der
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
	private int score;

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
	public Bomberman(int posX, int posY, int radius, int expRad, int color) {
		this.posX = posX;
		this.posY = posY;
		this.radius = radius;
		this.expRad = expRad;
		this.bombs = new ArrayList<Bomb>();
		this.counter = 0;
		this.score = 0;
		String path_to_Image;
		if (color > 1) // Bomberman Farbwahl
			path_to_Image = "img/Bomberman2.png";
		else
			path_to_Image = "img/Bomberman.png";
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
		Bomb bomb = new Bomb(this.posX, this.posY, this.radius, this.expRad,
				this);
		this.bombs.add(bomb);
		return this.bombs.lastIndexOf(bomb);
	}

	public synchronized void removeAllBombsFromList() {
		for (int i = 0; i < this.bombs.size(); i++) {
			this.bombs.remove(i);
		}
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

	public void addScore(int value) {
		this.score += 3;
	}

	public int getScore() {
		return this.score;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void incBombs() {
		this.counter++;
	}

	public void decBombs() {
		this.counter--;
	}
}
