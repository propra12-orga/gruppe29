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
			if (this.raster[this.bm.getPosX()][this.bm.getPosY()
					- this.bm.getSteps()] < 1) {
				if (this.raster[this.bm.getPosX()][this.bm.getPosY()
						- this.bm.getSteps()] == -1) {
					Main.f.dispose(
							"Du hast dich bis zum Ausgang durchgeschlagen!",
							true);
					if (two_player == true) {
						Main.f.restart(2);
					} else {
						Main.f.restart(1);
					}
				}
				this.bm.moveUp();
				repaint();
				this.bm.paintObject();
				break;
			} else
				break;
		case KeyEvent.VK_DOWN:
			if (this.raster[this.bm.getPosX()][this.bm.getPosY()
					+ this.bm.getSteps()] < 1) {
				if (this.raster[this.bm.getPosX()][this.bm.getPosY()
						+ this.bm.getSteps()] == -1) {
					Main.f.dispose(
							"Du hast dich bis zum Ausgang durchgeschlagen!",
							true);
					if (two_player == true) {
						Main.f.restart(2);
					} else {
						Main.f.restart(1);
					}
				}
				this.bm.moveDown();
				repaint();
				this.bm.paintObject();
			}
			break;

		case KeyEvent.VK_LEFT:
			if (this.raster[this.bm.getPosX() - this.bm.getSteps()][this.bm
					.getPosY()] < 1) {
				if (this.raster[this.bm.getPosX() - this.bm.getSteps()][this.bm
						.getPosY()] == -1) {
					Main.f.dispose(
							"Du hast dich bis zum Ausgang durchgeschlagen!",
							true);
					if (two_player == true) {
						Main.f.restart(2);
					} else {
						Main.f.restart(1);
					}
				}
				this.bm.moveLeft();
				repaint();
				this.bm.paintObject();
				break;
			} else
				break;
		case KeyEvent.VK_RIGHT:
			if (this.raster[this.bm.getPosX() + this.bm.getSteps()][this.bm
					.getPosY()] < 1) {
				if (this.raster[this.bm.getPosX() + this.bm.getSteps()][this.bm
						.getPosY()] == -1) {
					Main.f.dispose(
							"Du hast dich bis zum Ausgang durchgeschlagen!",
							true);
					if (two_player == true) {
						Main.f.restart(2);
					} else {
						Main.f.restart(1);
					}
				}
				this.bm.moveRight();
				repaint();
				this.bm.paintObject();
				break;
			} else
				break;
		case KeyEvent.VK_SPACE: {
			int n = this.bm.getNewBombIndex();
			this.raster[this.bm.bombs.get(n).getPosX()][this.bm.bombs.get(n)
					.getPosY()] = 3;
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
			if (this.raster[this.bm2.getPosX()][this.bm2.getPosY()
					- this.bm2.getSteps()] < 1) {
				if (this.raster[this.bm2.getPosX()][this.bm2.getPosY()
						- this.bm2.getSteps()] == -1) {
					Main.f.dispose(
							"Du hast dich bis zum Ausgang durchgeschlagen!",
							true);
					if (two_player == true) {
						Main.f.restart(2);
					} else {
						Main.f.restart(1);
					}
				}
				this.bm2.moveUp();
				repaint();
				this.bm2.paintObject();
				break;
			} else
				break;
		}
		case KeyEvent.VK_S: {
			if (this.raster[this.bm2.getPosX()][this.bm2.getPosY()
					+ this.bm2.getSteps()] < 1) {
				if (this.raster[this.bm2.getPosX()][this.bm2.getPosY()
						+ this.bm2.getSteps()] == -1) {
					Main.f.dispose(
							"Du hast dich bis zum Ausgang durchgeschlagen!",
							true);
					if (two_player == true) {
						Main.f.restart(2);
					} else {
						Main.f.restart(1);
					}
				}
				this.bm2.moveDown();
				repaint();
				this.bm2.paintObject();
			}
			break;
		}
		case KeyEvent.VK_A: {
			if (this.raster[this.bm2.getPosX() - this.bm2.getSteps()][this.bm2
					.getPosY()] < 1) {
				if (this.raster[this.bm2.getPosX() - this.bm2.getSteps()][this.bm2
						.getPosY()] == -1) {
					Main.f.dispose(
							"Du hast dich bis zum Ausgang durchgeschlagen!",
							true);
					if (two_player == true) {
						Main.f.restart(2);
					} else {
						Main.f.restart(1);
					}
				}
				this.bm2.moveLeft();
				repaint();
				this.bm2.paintObject();
				break;
			} else
				break;
		}
		case KeyEvent.VK_D: {
			if (this.raster[this.bm2.getPosX() + this.bm2.getSteps()][this.bm2
					.getPosY()] < 1) {
				if (this.raster[this.bm2.getPosX() + this.bm2.getSteps()][this.bm2
						.getPosY()] == -1) {
					Main.f.dispose(
							"Du hast dich bis zum Ausgang durchgeschlagen!",
							true);
					if (two_player == true) {
						Main.f.restart(2);
					} else {
						Main.f.restart(1);
					}
				}
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
					.getPosY()] = 3;
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

	// Spielraster
	public int[][] raster;

	// Spielfeldobjekte
	private Bomberman bm;
	private Bomberman bm2;
	private Bomb bomb;

	private int columns; // anzahl an reihen
	public int blockLength;// Groesse der Bloecke
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

		this.bomb = new Bomb(0, 0, this.bm.getRadius(), blockLength);

		this.raster = Main.raster;
	}

	public Spielfeld(int width, int height, int col, int length, boolean mode) {
		this(width, height, col, length);
		this.raster = Main.raster;
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

		for (int i = 0; i < this.raster[0].length; i++) {
			for (int j = 0; j < this.raster.length; j++) {
				switch (this.raster[i][j]) {
				case 0: {
					g.setColor(Color.WHITE);
					g.drawLine(i, j, i, j);
					break;
				}
				case 1: {
					g.setColor(Color.BLACK);
					g.drawLine(i, j, i, j);
					break;
				}
				case 2: {
					g.setColor(Color.ORANGE);
					g.drawLine(i, j, i, j);
					break;
				}
				case -1: {
					g.setColor(Color.BLUE);
					g.drawLine(i, j, i, j);
					break;
				}
				}
			}
		}

		// Rand zeichnen
		// // oben
		// g.fillRect(0, 0, width, blockLength);
		// for (int i = 0; i < width; i++)
		// for (int j = 0; j < blockLength; j++)
		// this.raster[i][j] = 1;
		// // unten
		// g.fillRect(0, height - blockLength, width, blockLength);
		// for (int i = 0; i < width; i++)
		// for (int j = height - blockLength; j < height; j++)
		// this.raster[i][j] = 1;
		// // rechts
		// g.fillRect(width - blockLength, blockLength, blockLength, height
		// - (2 * blockLength));
		// for (int i = width - blockLength; i < width; i++)
		// for (int j = blockLength; j < height - blockLength; j++)
		// this.raster[i][j] = 1;
		// // links
		// g.fillRect(0, blockLength, blockLength, height - (2 * blockLength));
		// for (int i = 0; i < blockLength; i++)
		// for (int j = blockLength; j < height - blockLength; j++)
		// this.raster[i][j] = 1;
		//
		// // Blöcke zeichnen
		// for (int i = 2 * blockLength; i < width - (2 * blockLength); i += 2 *
		// blockLength) {
		// for (int j = 2 * blockLength; j < height - (2 * blockLength); j += 2
		// * blockLength) {
		// g.fillRect(i, j, blockLength, blockLength);
		// for (int k = 0; k < blockLength; k++)
		// for (int l = 0; l < blockLength; l++)
		// this.raster[i + k][j + l] = 1;
		// }
		// }

		// Ausgang
		g.setColor(Color.BLUE);
		// g.fillRect(width - border - blockLength, height - border,
		// blockLength,border);

		// zerstörbare Mauern
		g.setColor(Color.ORANGE);

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
