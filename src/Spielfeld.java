import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Spielfeld extends JPanel implements KeyListener {

	@Override
	public void keyPressed(KeyEvent ke) {
		int n;
		switch (ke.getKeyCode()) {
		// std-steuerung
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
			if ((this.bm.getPosY() + this.bm.getSteps() < height - blockLength)
					&& (this.raster[this.bm.getPosX()][this.bm.getPosY()
							+ this.bm.getSteps()] != true)) {
				this.bm.moveDown();
				repaint();
				this.bm.paintObject();
			} else if ((this.bm.getPosY() + this.bm.getSteps()) >= (height - blockLength)
					&& this.bm.getPosX() + this.bm.getSteps() >= (width - blockLength)) {
				Main.f.dispose("Geschafft!", false);
				Main.f.restart(1);
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
			if ((this.bm.getPosX() + this.bm.getSteps() < width - blockLength)
					&& (this.raster[this.bm.getPosX() + this.bm.getSteps()][this.bm
							.getPosY()] != true)) {
				this.bm.moveRight();
				repaint();
				this.bm.paintObject();
				break;
			} else
				break;

		case KeyEvent.VK_SPACE:
			n = this.bm.getNewBombIndex();
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

		// steuerung 2ter spieler
		case KeyEvent.VK_W:
			if ((this.bm2.getPosY() - this.bm2.getSteps() >= 0)
					&& (this.raster[this.bm2.getPosX()][this.bm2.getPosY()
							- this.bm2.getSteps()] != true)) {
				this.bm2.moveUp();
				repaint();
				this.bm2.paintObject();
				break;
			} else
				break;

		case KeyEvent.VK_S:
			if ((this.bm2.getPosY() + this.bm2.getSteps() < height
					- blockLength)
					&& (this.raster[this.bm2.getPosX()][this.bm2.getPosY()
							+ this.bm2.getSteps()] != true)) {
				this.bm2.moveDown();
				repaint();
				this.bm2.paintObject();
			} else if ((this.bm2.getPosY() + this.bm2.getSteps()) >= (height - blockLength)
					&& this.bm2.getPosX() + this.bm2.getSteps() >= (width - blockLength)) {
				Main.f.dispose("Geschafft!", false);
				Main.f.restart(2);
			}
			break;

		case KeyEvent.VK_A:
			if ((this.bm2.getPosX() - this.bm2.getSteps() >= 0)
					&& (this.raster[this.bm2.getPosX() - this.bm2.getSteps()][this.bm2
							.getPosY()] != true)) {
				this.bm2.moveLeft();
				repaint();
				this.bm2.paintObject();
				break;
			} else
				break;

		case KeyEvent.VK_D:
			if ((this.bm2.getPosX() + this.bm2.getSteps() < width - blockLength)
					&& (this.raster[this.bm2.getPosX() + this.bm2.getSteps()][this.bm2
							.getPosY()] != true)) {
				this.bm2.moveRight();
				repaint();
				this.bm2.paintObject();
				break;
			} else
				break;

		case KeyEvent.VK_Q:
			n = this.bm2.getNewBombIndex();
			this.raster[this.bm2.bombs.get(n).getPosX()][this.bm2.bombs.get(n)
					.getPosY()] = true;
			this.bm2.bombs.get(n).setVisible(true);
			repaint();
			this.bm2.bombs.get(n).explode(this, this.bm2, this.bm);
			repaint();
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

	private int columns; // anzahl an reihen
	private int blockLength;// Groesse der Bloecke

	// Spielraster
	public boolean[][] raster;

	// Spielfeldobjekte
	private Bomberman bm;
	private Bomberman bm2;
	private Bomb bomb;

	private int expRad; // Explosionsradius

	// 2-Spielermodus
	public boolean two_player;

	public Spielfeld(int width, int height, int col, int length) {
		this.height = height;
		this.width = width;
		columns = col;
		blockLength = length;
		this.two_player = false;

		expRad = 2;
		this.bm = new Bomberman(width - ((columns - 1) * blockLength), height
				- ((columns - 1) * blockLength), blockLength, expRad
				* blockLength);

		this.bomb = new Bomb(0, 0, this.bm.getRadius(), expRad * blockLength);
		this.raster = new boolean[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				this.raster[i][j] = false;
	}

	public Spielfeld(int width, int height, int col, int length, boolean mode) {
		this(width, height, col, length);
		if (mode)
			this.two_player = true;
		mehrspielermodus();
	}

	public void mehrspielermodus() {
		this.bm2 = new Bomberman(width - (2 * blockLength), height
				- (2 * blockLength), blockLength, expRad * blockLength, 2);
		this.two_player = true;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Rand zeichnen
		// oben
		g.fillRect(0, 0, width, blockLength);
		for (int i = 0; i < width; i++)
			for (int j = 0; j < blockLength; j++)
				this.raster[i][j] = true;
		// unten
		g.fillRect(0, height - blockLength, width, blockLength);
		for (int i = 0; i < width; i++)
			for (int j = height - blockLength; j < blockLength; j++)
				this.raster[i][j] = true;
		// rechts
		g.fillRect(width - blockLength, blockLength, blockLength, height
				- (2 * blockLength));
		for (int i = width - blockLength; i < width; i++)
			for (int j = blockLength; j < height - blockLength; j++)
				this.raster[i][j] = true;
		// links
		g.fillRect(0, blockLength, blockLength, height - (2 * blockLength));
		for (int i = 0; i < blockLength; i++)
			for (int j = blockLength; j < height - blockLength; j++)
				this.raster[i][j] = true;

		// Blöcke zeichnen
		for (int i = 2 * blockLength; i < width - (2 * blockLength); i += 2 * blockLength) {
			for (int j = 2 * blockLength; j < height - (2 * blockLength); j += 2 * blockLength) {
				g.fillRect(i, j, blockLength, blockLength);
				for (int k = 0; k < blockLength; k++)
					for (int l = 0; l < blockLength; l++)
						this.raster[i + k][j + l] = true;
			}
		}

		// Ausgang
		g.setColor(Color.BLUE);
		g.fillRect(width - (2 * blockLength), height - blockLength,
				blockLength, blockLength);

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
			if ((this.bm.bombs.get(i) != null)
					&& (this.bm.bombs.get(i).isVisible()))
				this.bm.bombs.get(i).paintObject(g);

		if (bm2 != null) {
			for (int i = 0; i < this.bm2.bombs.size(); i++)
				if ((this.bm2.bombs.get(i) != null)
						&& (this.bm2.bombs.get(i).isVisible()))
					this.bm2.bombs.get(i).paintObject(g);
		}
	}
}
