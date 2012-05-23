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
			} else if ((this.bm.getPosY() + this.bm.getSteps()) >= (height - border)
					&& this.bm.getPosX() + this.bm.getSteps() >= (width - border)) {
				Main.f.dispose();
				Main.f.restart();
			}
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
			int n = this.bm.getNewBombIndex();
			this.raster[this.bm.bombs.get(n).getPosX()][this.bm.bombs.get(n)
					.getPosY()] = true;
			this.bm.bombs.get(n).setVisible(true);
			repaint();
			if (two_player)
				this.bm.bombs.get(n).explode(this, this.bm, this.bm2);
			else
				this.bm.bombs.get(n).explode(this, this.bm);
			repaint();
			break;
		}
		case KeyEvent.VK_W: {
			if ((this.bm2.getPosY() - this.bm2.getSteps() >= 0)
					&& (this.raster[this.bm2.getPosX()][this.bm2.getPosY()
							- this.bm2.getSteps()] != true)) {
				this.bm2.moveUp();
				repaint();
				this.bm2.paintObject();
				break;
			} else
				break;
		}
		case KeyEvent.VK_S: {
			if ((this.bm2.getPosY() + this.bm2.getSteps() < height - border)
					&& (this.raster[this.bm2.getPosX()][this.bm2.getPosY()
							+ this.bm2.getSteps()] != true)) {
				this.bm2.moveDown();
				repaint();
				this.bm2.paintObject();
			} else if ((this.bm2.getPosY() + this.bm2.getSteps()) >= (height - border)
					&& this.bm2.getPosX() + this.bm2.getSteps() >= (width - border)) {
				Main.f.dispose();
				Main.f.restart();
			}
			break;
		}
		case KeyEvent.VK_A: {
			if ((this.bm2.getPosX() - this.bm2.getSteps() >= 0)
					&& (this.raster[this.bm2.getPosX() - this.bm2.getSteps()][this.bm2
							.getPosY()] != true)) {
				this.bm2.moveLeft();
				repaint();
				this.bm2.paintObject();
				break;
			} else
				break;
		}
		case KeyEvent.VK_D: {
			if ((this.bm2.getPosX() + this.bm2.getSteps() < width - border)
					&& (this.raster[this.bm2.getPosX() + this.bm2.getSteps()][this.bm2
							.getPosY()] != true)) {
				this.bm2.moveRight();
				repaint();
				this.bm2.paintObject();
				break;
			} else
				break;
		}
		case KeyEvent.VK_Q: {
			int n = this.bm2.getNewBombIndex();
			this.raster[this.bm2.bombs.get(n).getPosX()][this.bm2.bombs.get(n)
					.getPosY()] = true;
			this.bm2.bombs.get(n).setVisible(true);
			repaint();
			this.bm2.bombs.get(n).explode(this, this.bm2, this.bm);
			repaint();
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
	public boolean[][] raster;

	// Spielfeldobjekte
	private Bomberman bm;
	private Bomberman bm2;
	private Bomb bomb;

	// Groesse der Bloecke
	private int blockLength;

	// 2-Spielermodus
	public boolean two_player;

	public Spielfeld(int width, int height) {
		this.height = height;
		this.width = width;
		this.two_player = false;
		findVariables(13);
		this.bm = new Bomberman(width - border - field, border, blockLength);
		this.bomb = new Bomb(0, 0, this.bm.getRadius(), blockLength);
		this.raster = new boolean[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				this.raster[i][j] = false;
	}

	public Spielfeld(int width, int height, boolean mode) {
		this(width, height);
		if (mode)
			this.two_player = true;
		mehrspielermodus();
	}

	public void mehrspielermodus() {
		this.bm2 = new Bomberman(width - border - blockLength, height - border
				- blockLength, blockLength);
		this.two_player = true;
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

		// Ausgang
		g.setColor(Color.BLUE);
		g.fillRect(width - border - blockLength, height - border, blockLength,
				border);

		// // Ausgang zeichnen
		// g.setColor(Color.blue);
		// g.drawOval(width - border - blockLength, height - border -
		// blockLength,
		// blockLength - 1, blockLength - 1);
		// JLabel ausgang = new JLabel();
		// ausgang.setIcon(new ImageIcon("ausgang.png"));
		// ausgang.paintImmediately(width - border - blockLength, height -
		// border
		// - blockLength, blockLength - 1, blockLength - 1);

		// Bomberman zeichnen
		bm.paintObject();
		this.add(this.bm);

		if (bm2 != null) {
			bm2.paintObject();
			this.add(this.bm2);
		}

		for (int i = 0; i < this.bm.bombs.size(); i++)
			if (this.bm.bombs.get(i) != null
					&& this.bm.bombs.get(i).isVisible())
				this.bm.bombs.get(i).paintObject(g);

		if (bm2 != null) {
			for (int i = 0; i < this.bm2.bombs.size(); i++)
				if (this.bm2.bombs.get(i) != null
						&& this.bm2.bombs.get(i).isVisible())
					this.bm2.bombs.get(i).paintObject(g);
		}
	}
}
