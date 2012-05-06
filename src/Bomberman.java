import java.awt.Color;
import java.awt.Graphics;

public class Bomberman {

	int posX;
	int posY;

	public Bomberman(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}

	public void paintBomberman(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(this.posX, this.posY, 10, 10);
	}

	public void moveUp() {
		setPosY(getPosY() - 10);
	}

	public void moveDown() {
		setPosY(getPosY() + 10);
	}

	public void moveLeft() {
		setPosX(getPosX() - 10);
	}

	public void moveRight() {
		setPosX(getPosX() + 10);
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

}
