import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Spielfeld extends JPanel implements KeyListener {

	@Override
	public void keyPressed(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_UP:
			if ((this.bm.getPosY() - this.bm.getSteps() >= 0)
					&& (this.raster[this.bm.getPosX()][this.bm.getPosY()
							- this.bm.getSteps()] != true)) {
				this.bm.moveUp();
				repaint();
				this.bm.paintObject();
				break;
			} else
				break;
		case KeyEvent.VK_DOWN:
			if ((this.bm.getPosY() + this.bm.getSteps() < height - border)
					&& (this.raster[this.bm.getPosX()][this.bm.getPosY()
							+ this.bm.getSteps()] != true)) {
				this.bm.moveDown();
				repaint();
				this.bm.paintObject();
				break;
			} else
				break;
		case KeyEvent.VK_LEFT:
			if ((this.bm.getPosX() - this.bm.getSteps() >= 0)
					&& (this.raster[this.bm.getPosX() - this.bm.getSteps()][this.bm
							.getPosY()] != true)) {
				this.bm.moveLeft();
				repaint();
				this.bm.paintObject();
				break;
			} else
				break;
		case KeyEvent.VK_RIGHT:
			if ((this.bm.getPosX() + this.bm.getSteps() < width - border)
					&& (this.raster[this.bm.getPosX() + this.bm.getSteps()][this.bm
							.getPosY()] != true)) {
				this.bm.moveRight();
				repaint();
				this.bm.paintObject();
				break;
			} else
				break;
		case KeyEvent.VK_SPACE: {
			if (!bombs[0].isVisible()) {
				this.bombs[0].setPosX(this.bm.getPosX());
				this.bombs[0].setPosY(this.bm.getPosY());
				this.bombs[0].setVisible(true);
				this.bombs[0].explode();
				repaint();
			}
			break;
		}
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
	private Bomb[] bombs;

	// Groesse der Bloecke
	private int blockLength;

	public Spielfeld(int width, int height) {
		this.height = height;
		this.width = width;
		findVariables(13);
		this.bm = new Bomberman(width - border - field, border, blockLength);
		this.bombs = new Bomb[10];
		this.bombs[0] = new Bomb(0, 0, this.bm.getRadius(), 1);
		this.raster = new boolean[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				this.raster[i][j] = false;
	}

	private void findVariables(int columns) {
		int tmp = height;
		while (tmp % columns != 0) {
			tmp -= 10;
		}
		field = tmp;
		blockLength = field / columns;
		border = (height - field) / 2;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Rand zeichnen
		// oben
		g.fillRect(0, 0, width, border);
		for (int i = 0; i < width; i++)
			for (int j = 0; j < border; j++)
				this.raster[i][j] = true;
		// unten
		g.fillRect(0, height - border, width, border);
		for (int i = 0; i < width; i++)
			for (int j = height - border; j < border; j++)
				this.raster[i][j] = true;
		// rechts
		g.fillRect(width - border, border, border, height - border);
		for (int i = width - border; i < border; i++)
			for (int j = border; j < height; j++)
				this.raster[i][j] = true;
		// links
		g.fillRect(0, border, width - border - field, height - border);
		for (int i = 0; i < width - border - field; i++)
			for (int j = border; j < height - border; j++)
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
		// Ausgang zeichnen
		g.setColor(Color.blue);
		g.drawOval(width - border - blockLength, height - border - blockLength,
				blockLength - 1, blockLength - 1);
		// JLabel ausgang = new JLabel();
		// ausgang.setIcon(new ImageIcon("ausgang.png"));
		// ausgang.paintImmediately(width - border - blockLength, height -
		// border
		// - blockLength, blockLength - 1, blockLength - 1);

		// Bomberman zeichnen

		bm.paintObject();
		this.add(this.bm);
		if (bombs[0].isVisible())
			bombs[0].paintObject(g);
	}
}