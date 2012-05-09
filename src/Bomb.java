import java.awt.Color;
import java.awt.Graphics;

public class Bomb extends GraphicObject {
	// bombe
	private int posX;
	private int posY;
	private int radius;
	private int explosionRadius;
	private boolean visible;

	public Bomb(int posX, int posY, int radius, int explosionRadius) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.radius = radius;
		this.explosionRadius = explosionRadius;
		this.visible = false;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
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

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public void paintObject(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(posX, posY, radius, radius);
	}

}
