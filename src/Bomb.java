import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
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
	public Timer tExp;
	public Timer tUnExp;
	public Timer tSound;
	private Bomberman owner;

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
	public Bomb(int posX, int posY, int radius, int explosionRadius,
			Bomberman owner) {
		super();
		this.tExp = new Timer();
		this.tUnExp = new Timer();
		this.tSound = new Timer();
		this.posX = posX;
		this.posY = posY;
		this.radius = radius;
		this.explosionRadius = explosionRadius;
		this.visible = false;
		this.exploded = false;
		this.owner = owner;

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
		tExp.schedule(new BombExplosion(this, sp, man), 2000);
		tUnExp.schedule(new BombUnExplosion(this, man, sp), 2500);
		tSound.schedule(new BombSound(), 2000);
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
		tExp.schedule(new BombExplosion(this, sp, man, man2), 2000);
		tUnExp.schedule(new BombUnExplosion(this, this.getOwner(), sp), 2500);
		tSound.schedule(new BombSound(), 2000);

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

	public Bomberman getOwner() {
		return this.owner;
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
			Image flame = Toolkit.getDefaultToolkit().getImage("flame.png");
			ImageIcon ico2 = new ImageIcon("flamew.png");
			ico2.setImage(ico2.getImage().getScaledInstance(this.radius,
					this.radius, Image.SCALE_DEFAULT));
			this.setIcon(ico2);
			for (int i = radius; i <= explosionRadius; i += radius) {
				g.drawImage(flame, this.posX - i, this.posY, this.radius,
						this.radius, null);
				g.drawImage(flame, this.posX + i, this.posY, this.radius,
						this.radius, null);
				g.drawImage(flame, this.posX, this.posY - i, this.radius,
						this.radius, null);
				g.drawImage(flame, this.posX, this.posY + i, this.radius,
						this.radius, null);
			}
		}

	}
}
