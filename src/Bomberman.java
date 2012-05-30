import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomberman extends JLabel {

	private int posX;
	private int posY;
	private int radius;
	private int steps;

	public List<Bomb> bombs;
	public int counter;

	public Bomberman(int posX, int posY, int radius) {
		this.posX = posX;
		this.posY = posY;
		this.radius = radius;
		this.bombs = new ArrayList<Bomb>();// (0, 0, this.radius, this.radius);
		this.counter = 0;
		ImageIcon ico = new ImageIcon("Bomberman.png");
		ico.setImage(ico.getImage().getScaledInstance(radius, radius,
				Image.SCALE_DEFAULT));
		this.setIcon(ico);
		setSteps(radius);
	}

	public Bomberman(int posX, int posY, int radius, int color) {
		this.posX = posX;
		this.posY = posY;
		this.radius = radius;
		this.bombs = new ArrayList<Bomb>();// (0, 0, this.radius, this.radius);
		this.counter = 0;
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

	public int getNewBombIndex() {
		Bomb bomb = new Bomb(this.posX, this.posY, this.radius, this.radius);
		this.bombs.add(bomb);
		return this.bombs.lastIndexOf(bomb);
	}

	public void paintObject() {
		this.setBounds(this.posX, this.posY, this.radius, this.radius);
		/*
		 * g.setColor(Color.red); g.fillOval(this.posX, this.posY, radius,
		 * radius);
		 */
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
