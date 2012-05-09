import java.awt.Color;
import java.awt.Graphics;

public class Bomberman extends GraphicObject {

	private int posX;
	private int posY;
	private int radius;
	private int steps;

	public Bomberman(int posX, int posY, int radius) {
		this.posX = posX;
		this.posY = posY;
		this.radius = radius;
		setSteps(radius);
	}

	public void paintObject(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(this.posX, this.posY, radius, radius);
	}

	public void moveUp() {
		setPosY(getPosY() - steps);
	}

	public void moveDown() {
		setPosY(getPosY() + steps);
	}

	public void moveLeft() {
		setPosX(getPosX() - steps);
	}

	public void moveRight() {
		setPosX(getPosX() + steps);
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
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

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

}
