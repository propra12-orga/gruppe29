import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomb extends JLabel {
	private int posX;
	private int posY;
	/** Radius der Bombe */
	private int radius;
	/** Explosionsradius der Bombe */
	private int explosionRadius;
	/** Sichtbarkeit der Bombe */
	private boolean visible;
	/** Bombe explodiert nicht explodiert */
	private boolean exploded;

	/**
	 * Die Methode Bomb uebernimmt die Parameter und setzt die Bombe auf
	 * unsichtbar und auf nicht explodiert
	 * 
	 * @param posX
	 *            x Position der Bombe
	 * @param posY
	 *            y Position der Bombe
	 * @param radius
	 *            Radius der Bombe
	 * @param explosionRadius
	 *            Explosionsradius der Bombe
	 * */
	public Bomb(int posX, int posY, int radius, int explosionRadius) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.radius = radius;
		this.explosionRadius = explosionRadius;
		this.visible = false;
		this.exploded = false;

	}

	/**
	 * Die Methode explode erstellt einen Timer t und setzt den Timer
	 * 
	 * @param sp
	 *            uebergibt das Spielfeld
	 * @param man
	 *            uebergibt den Bomberman
	 */
	public void explode(Spielfeld sp, Bomberman man) {
		Timer t = new Timer();
		t.schedule(new BombExplosion(this, sp, man), 1000);
		t.schedule(new BombUnExplosion(this, sp), 1500);

	}

	/**
	 * Die Methode explode erstellt einen Timer t und setzt den Timer
	 * 
	 * @param sp
	 *            uebergibt das Spielfeld
	 * @param man
	 *            uebergibt den 1. Bomberman
	 * @param man2
	 *            übergibt den 2. Bomberman
	 */
	public void explode(Spielfeld sp, Bomberman man, Bomberman man2) {
		Timer t = new Timer();
		t.schedule(new BombExplosion(this, sp, man, man2), 1000);
		t.schedule(new BombUnExplosion(this, sp), 1500);

	}

	/**
	 * 
	 * @return gibt zurueck, ob die Bombe explodiert ist
	 */
	public boolean isExploded() {
		return exploded;
	}

	/**
	 * @param exploded
	 *            gibt an, ob die Bombe explodiert ist
	 */
	public void setExploded(boolean exploded) {
		this.exploded = exploded;
	}

	/**
	 * 
	 * @return gibt zurueck, ob die Bombe sichtbar ist oder unsichtbar ist
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * 
	 * @param visible
	 *            Die Bombe wird auf sichtbar bzw. unsichtbar gesetzt
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * 
	 * @return gibt die x Position der Bombe zurück
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * 
	 * @param posX
	 *            setzt die Position von x
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * 
	 * @return gibt die y Position der Bombe zurück
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * 
	 * @param posY
	 *            setzt die Position von y
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * 
	 * @return gibt den Radius den Radius der Bombe zurück
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * 
	 * @param radius
	 *            setzt den Radius der Bombe
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * 
	 * @return gibt den Explosionsradius der Bombe zurück
	 */
	public int getExplosionRadius() {
		return explosionRadius;
	}

	/**
	 * 
	 * @param explosionRadius
	 *            setzt den Explosionsradius der Bombe
	 */
	public void setExplosionRadius(int explosionRadius) {
		this.explosionRadius = explosionRadius;
	}

	/**
	 * zeichnet die Bombe
	 */
	public void paintObject(Graphics g) {
		ImageIcon ico = new ImageIcon("bombew.png");
		ico.setImage(ico.getImage().getScaledInstance(this.radius, this.radius,
				Image.SCALE_DEFAULT));
		this.setIcon(ico);
		this.setBounds(this.posX, this.posY, this.radius, this.radius);
		if (isExploded()) {
			g.setColor(Color.orange);
			for (int i = radius; i <= explosionRadius; i += radius) {
				g.fillOval(posX - i, posY, radius, radius);
				g.fillOval(posX + i, posY, radius, radius);
				g.fillOval(posX, posY - i, radius, radius);
				g.fillOval(posX, posY + i, radius, radius);
			}
		}

	}
}
