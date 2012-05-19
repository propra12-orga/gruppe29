import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;

public class Bomb extends GraphicObject {
	// bombe
	private int posX;
	private int posY;
	private int radius;
	private int explosionRadius;
	private boolean visible;
	private boolean exploded;

	public Bomb(int posX, int posY, int radius, int explosionRadius) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.radius = radius;
		this.explosionRadius = explosionRadius;
		this.visible = false;
		this.exploded = false;
	}

	public void explode(Spielfeld sp, Bomberman man) {
		Timer t = new Timer();
		t.schedule(new BombExplosion(this, sp, man), 2000);

		t.schedule(new BombUnExplosion(this, sp), 4000);

	}

	public boolean isExploded() {
		return exploded;
	}

	public void setExploded(boolean exploded) {
		this.exploded = exploded;
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

	public int getExplosionRadius() {
		return explosionRadius;
	}

	public void setExplosionRadius(int explosionRadius) {
		this.explosionRadius = explosionRadius;
	}

	@Override
	public void paintObject(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(posX, posY, radius, radius);
		if (isExploded()) {
			g.setColor(Color.orange);
			g.fillOval(posX - explosionRadius, posY, radius, radius);
			g.fillOval(posX + explosionRadius, posY, radius, radius);
			g.fillOval(posX, posY - explosionRadius, radius, radius);
			g.fillOval(posX, posY + explosionRadius, radius, radius);
		}

	}
}
