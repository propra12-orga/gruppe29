import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Spielfeld extends JPanel implements KeyListener {

	@Override
	/**
	 * Abfrage der gedrueckten Tasten und Eventhandler für beide Bomberman (Bewegung und Bombenwurf)
	 */
	public void keyPressed(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_UP: {
			switch (this.raster[this.bm.getPosX()][this.bm.getPosY()
					- this.bm.getSteps()]) {
			case 0: {
				this.bm.moveUp();
				repaint();
				this.bm.paintObject();
				break;
			}

			case -1: {
				this.bm.moveUp();
				repaint();
				this.bm.paintObject();
				Main.f.dispose("Du hast dich bis zum Ausgang durchgeschlagen!",
						true);
				if (two_player == true) {
					Main.f.restart(2);
				} else {
					Main.f.restart(1);
				}
				break;
			}

			}
			break;
		}
		case KeyEvent.VK_DOWN: {
			switch (this.raster[this.bm.getPosX()][this.bm.getPosY()
					+ this.bm.getSteps()]) {
			case 0: {
				this.bm.moveDown();
				repaint();
				this.bm.paintObject();
				break;
			}

			case -1: {
				this.bm.moveDown();
				repaint();
				this.bm.paintObject();
				Main.f.dispose("Du hast dich bis zum Ausgang durchgeschlagen!",
						true);
				if (two_player == true) {
					Main.f.restart(2);
				} else {
					Main.f.restart(1);
				}
				break;
			}

			}
		}
			break;

		case KeyEvent.VK_LEFT: {
			switch (this.raster[this.bm.getPosX() - this.bm.getSteps()][this.bm
					.getPosY()]) {
			case 0: {
				this.bm.moveLeft();
				repaint();
				this.bm.paintObject();
				break;
			}
			case -1: {
				this.bm.moveLeft();
				repaint();
				this.bm.paintObject();
				Main.f.dispose("Du hast dich bis zum Ausgang durchgeschlagen!",
						true);
				if (two_player == true) {
					Main.f.restart(2);
				} else {
					Main.f.restart(1);
				}
				break;
			}

			}
		}
			break;
		case KeyEvent.VK_RIGHT: {
			switch (this.raster[this.bm.getPosX() + this.bm.getSteps()][this.bm
					.getPosY()]) {
			case 0: {
				this.bm.moveRight();
				repaint();
				this.bm.paintObject();
				break;
			}

			case -1: {
				this.bm.moveRight();
				repaint();
				this.bm.paintObject();
				Main.f.dispose("Du hast dich bis zum Ausgang durchgeschlagen!",
						true);
				if (two_player == true) {
					Main.f.restart(2);
				} else {
					Main.f.restart(1);
				}
				break;
			}

			}
		}
			break;
		case KeyEvent.VK_SPACE: {
			int n = this.bm.getNewBombIndex();
			this.raster[this.bm.bombs.get(n).getPosX()][this.bm.bombs.get(n)
					.getPosY()] = 3;
			this.bm.bombs.get(n).setVisible(true);
			this.add(this.bm.bombs.get(n));
			repaint();
			if (two_player)
				this.bm.bombs.get(n).explode(this, this.bm, this.bm2);
			else
				this.bm.bombs.get(n).explode(this, this.bm);
			repaint();
			break;
		}
		case KeyEvent.VK_W: {
			switch (this.raster[this.bm2.getPosX()][this.bm2.getPosY()
					- this.bm2.getSteps()]) {
			case 0: {
				this.bm2.moveUp();
				repaint();
				this.bm2.paintObject();
				break;
			}

			case -1: {
				this.bm2.moveUp();
				repaint();
				this.bm2.paintObject();
				Main.f.dispose("Du hast dich bis zum Ausgang durchgeschlagen!",
						true);
				if (two_player == true) {
					Main.f.restart(2);
				} else {
					Main.f.restart(1);
				}
				break;
			}

			}
			break;
		}
		case KeyEvent.VK_S: {
			switch (this.raster[this.bm2.getPosX()][this.bm2.getPosY()
					+ this.bm2.getSteps()]) {
			case 0: {
				this.bm2.moveDown();
				repaint();
				this.bm2.paintObject();
				break;
			}

			case -1: {
				this.bm2.moveDown();
				repaint();
				this.bm2.paintObject();
				Main.f.dispose("Du hast dich bis zum Ausgang durchgeschlagen!",
						true);
				if (two_player == true) {
					Main.f.restart(2);
				} else {
					Main.f.restart(1);
				}
				break;
			}

			}
			break;
		}
		case KeyEvent.VK_A: {
			switch (this.raster[this.bm2.getPosX() - this.bm2.getSteps()][this.bm2
					.getPosY()]) {
			case 0: {
				this.bm2.moveLeft();
				repaint();
				this.bm2.paintObject();
				break;
			}

			case -1: {
				this.bm2.moveLeft();
				repaint();
				this.bm2.paintObject();
				Main.f.dispose("Du hast dich bis zum Ausgang durchgeschlagen!",
						true);
				if (two_player == true) {
					Main.f.restart(2);
				} else {
					Main.f.restart(1);
				}
				break;
			}

			}
			break;
		}
		case KeyEvent.VK_D: {
			switch (this.raster[this.bm2.getPosX() + this.bm2.getSteps()][this.bm2
					.getPosY()]) {
			case 0: {
				this.bm2.moveRight();
				repaint();
				this.bm2.paintObject();
				break;
			}

			case -1: {
				this.bm2.moveRight();
				repaint();
				this.bm2.paintObject();
				Main.f.dispose("Du hast dich bis zum Ausgang durchgeschlagen!",
						true);
				if (two_player == true) {
					Main.f.restart(2);
				} else {
					Main.f.restart(1);
				}
				break;
			}

			}
			break;
		}
		case KeyEvent.VK_Q: {
			int n = this.bm2.getNewBombIndex();
			this.raster[this.bm2.bombs.get(n).getPosX()][this.bm2.bombs.get(n)
					.getPosY()] = 3;
			this.bm2.bombs.get(n).setVisible(true);
			this.add(this.bm2.bombs.get(n));
			repaint();
			this.bm2.bombs.get(n).explode(this, this.bm);
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

	/** Breite des Fensters */
	private int width;
	/** Hoehe des Fensters */
	private int height;

	/**
	 * Getter für Fensterbreite
	 * 
	 * @return Breite des Fensters
	 */
	public int getBreite() {
		return width;
	}

	/**
	 * Spielraster mit Int-Variablen für verschiedene Elemente auf dem Spielfeld
	 * (-2) - Powerup (-1) - Ausgang (0) - begehbar (2) - zerstoerbar (3) -
	 * Bombe (4) - Zerstoerbar mit Exit
	 */
	public int[][] raster;

	/** Spielfeldobjekt Bomberman (Einspieler) */
	private Bomberman bm;
	/** Spielfeldobjekt Bomberman (Zweiter Spieler) */
	private Bomberman bm2;
	/** Bombe */
	private Bomb bomb;

	/** Anzahl der begehbaren Reihen */
	private int columns;
	/** Groesse der Bloecke (alle Bloecke sind quadratisch */
	public int blockLength;
	/** Explosionsradius */
	private int expRad;

	/** Ist Zweispielermodus angeschaltet? */
	public boolean two_player;

	/**
	 * Constructor
	 * 
	 * @param width
	 *            Fensterbreite
	 * @param height
	 *            Fensterhoehe
	 * @param col
	 *            Reihen
	 * @param length
	 *            Blocklaenge
	 * @param mode
	 *            2-Spieler-Modus
	 */
	public Spielfeld(int width, int height, int col, int length, boolean mode) {
		this.height = height;
		this.width = width;
		columns = col;
		blockLength = length;
		this.two_player = false;

		expRad = 2;
		this.bm = new Bomberman(width - ((columns - 1) * blockLength), height
				- ((columns - 1) * blockLength), blockLength, expRad
				* blockLength, 1);

		if (mode) {
			this.two_player = true;
			this.bm2 = new Bomberman(width - (2 * blockLength), height
					- (2 * blockLength), blockLength, expRad * blockLength, 2);
		}

		this.bomb = new Bomb(0, 0, this.bm.getRadius(), blockLength);

		this.add(this.bm);
		if (this.bm2 != null)
			this.add(this.bm2);

		this.raster = new int[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				this.raster[i][j] = Main.raster[i][j];
	}

	/**
	 * Zeichenmethode für das Spielfeld, aufgeloest nach Raster
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < this.raster[0].length; i += blockLength) {
			for (int j = 0; j < this.raster.length; j += blockLength) {
				switch (this.raster[i][j]) {
				case 0: {
					g.setColor(Color.WHITE);
					g.fillRect(i, j, blockLength, blockLength);
					break;
				}
				case 1: {
					g.setColor(Color.BLACK);
					g.fillRect(i, j, blockLength, blockLength);
					break;
				}
				case 2: {
					g.setColor(Color.ORANGE);
					g.fillRect(i, j, blockLength, blockLength);
					break;
				}
				case 4: {
					g.setColor(Color.ORANGE);
					g.fillRect(i, j, blockLength, blockLength);
					break;
				}
				case -1: {
					g.setColor(Color.BLUE);
					g.fillRect(i, j, blockLength, blockLength);
					break;
				}
				}
			}
		}

		// Bomberman zeichnen
		bm.paintObject();

		if (bm2 != null)
			bm2.paintObject();

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
