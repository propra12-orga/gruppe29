import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Spielfeld extends JPanel implements KeyListener {

	@Override
	public void keyPressed(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (this.raster[this.bm.getPosX()][this.bm.getPosY()
					- this.bm.getSteps()] != true) {
				this.bm.moveUp();
				repaint();
				break;
			} else
				break;
		case KeyEvent.VK_DOWN:
			if (this.raster[this.bm.getPosX()][this.bm.getPosY()
					+ this.bm.getSteps()] != true) {
				this.bm.moveDown();
				repaint();
				break;
			} else
				break;
		case KeyEvent.VK_LEFT:
			if (this.raster[this.bm.getPosX() - this.bm.getSteps()][this.bm
					.getPosY()] != true) {
				this.bm.moveLeft();
				repaint();
				break;
			} else
				break;
		case KeyEvent.VK_RIGHT:
			if (this.raster[this.bm.getPosX() + this.bm.getSteps()][this.bm
					.getPosY()] != true) {
				this.bm.moveRight();
				repaint();
				break;
			} else
				break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	// Groesse des Fensters
	private int width;
	private int height;

	private int border; // rand unten&rechts
	private int field; // laenge des spielfelds

	// Spielraster
	private boolean[][] raster;

	// Spielfeldobjekte
	private Bomberman bm;

	// Groesse der Bloecke
	private int blockLength;

	public Spielfeld(int width, int height) {
		this.height = height;
		this.width = width;
		findVariables();
		this.bm = new Bomberman(width - border - field, border, blockLength);
		this.raster = new boolean[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				this.raster[i][j] = false;
	}

	private void findVariables() {
		int tmp = height;
		while (tmp % 9 != 0) {
			tmp -= 10;
		}
		field = tmp;
		blockLength = field / 9;
		border = (height - field) / 2;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Rand zeichnen
		g.fillRect(0, 0, width, border);
		for (int i = 0; i < width; i++)
			for (int j = 0; j < border; j++)
				this.raster[i][j] = true;
		g.fillRect(0, height - border, width, border);
		for (int i = 0; i < width; i++)
			for (int j = height - border; j < border; j++)
				this.raster[i][j] = true;
		g.fillRect(width - border, border, border, height);
		for (int i = width - border; i < border; i++)
			for (int j = border; j < height; j++)
				this.raster[i][j] = true;
		g.fillRect(0, border, width - border - field, height - 2 * border);
		for (int i = 0; i < width - border - field; i++)
			for (int j = border; j < height - 2 * border; j++)
				this.raster[i][j] = true;

		// Blöcke zeichnen
		for (int i = width - border - field + blockLength; i < width - border; i += 2 * blockLength) {
			for (int j = border + blockLength; j < height - border; j += 2 * blockLength) {
				g.fillRect(i, j, blockLength, blockLength);
				for (int k = 0; k < blockLength; k++)
					for (int l = 0; l < blockLength; l++)
						this.raster[i + k][j + l] = true;
			}
		}

		// Bomberman zeichnen
		bm.paintBomberman(g);
	}
}