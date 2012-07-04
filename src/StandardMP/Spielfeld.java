package StandardMP;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import server.BMServer;
import Spielobjekte.Ausgang;
import Spielobjekte.Boden;
import Spielobjekte.BreakableMauer;
import Spielobjekte.Mauer;

public class Spielfeld extends JPanel implements KeyListener {

	@Override
	/**
	 * Abfrage der gedrueckten Tasten und Eventhandler für beide Bomberman (Bewegung und Bombenwurf)
	 */
	public void keyPressed(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_UP: {
			if (this.raster[this.bm2.getPosX()][this.bm2.getPosY()
					- this.bm2.getSteps()] <= 0) {

				this.raster[this.bm2.getPosX()][this.bm2.getPosY()] = 0;
				this.bm2.moveUp();
				this.raster[this.bm2.getPosX()][this.bm2.getPosY()] = -5;

				try {
					server.writeToClient(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				repaint();

			}
			break;
		}
		case KeyEvent.VK_DOWN: {
			if (this.raster[this.bm2.getPosX()][this.bm2.getPosY()
					+ this.bm2.getSteps()] <= 0) {

				this.raster[this.bm2.getPosX()][this.bm2.getPosY()] = 0;
				this.bm2.moveDown();
				this.raster[this.bm2.getPosX()][this.bm2.getPosY()] = -5;

				try {
					server.writeToClient(2);
				} catch (Exception e) {
					e.printStackTrace();
				}
				repaint();

			}
		}
			break;

		case KeyEvent.VK_LEFT: {
			if (this.raster[this.bm2.getPosX() - this.bm2.getSteps()][this.bm2
					.getPosY()] <= 0) {

				this.raster[this.bm2.getPosX()][this.bm2.getPosY()] = 0;
				this.bm2.moveLeft();
				this.raster[this.bm2.getPosX()][this.bm2.getPosY()] = -5;

				try {
					server.writeToClient(3);
				} catch (Exception e) {
					e.printStackTrace();
				}
				repaint();

			}
		}
			break;
		case KeyEvent.VK_RIGHT: {
			if (this.raster[this.bm2.getPosX() + this.bm2.getSteps()][this.bm2
					.getPosY()] <= 0) {

				this.raster[this.bm2.getPosX()][this.bm2.getPosY()] = 0;
				this.bm2.moveRight();
				this.raster[this.bm2.getPosX()][this.bm2.getPosY()] = -5;

				try {
					server.writeToClient(4);
				} catch (Exception e) {
					e.printStackTrace();
				}
				repaint();

			}
		}
			break;
		case KeyEvent.VK_SPACE: {
			int n = this.bm2.getNewBombIndex();
			if (this.raster[this.bm2.bombs.get(n).getPosX()][this.bm2.bombs
					.get(n).getPosY()] != 3) {
				this.bm2.incBombs();
				this.raster[this.bm2.bombs.get(n).getPosX()][this.bm2.bombs
						.get(n).getPosY()] = 3;
				this.bm2.bombs.get(n).setVisible(true);
				this.add(this.bm2.bombs.get(n));
				repaint();
				if (two_player)
					this.bm2.bombs.get(n).explode(this, this.bm2, this.bm2);
				else
					this.bm2.bombs.get(n).explode(this, this.bm2);
				repaint();
			}
			try {
				server.writeToClient(5);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
	public Bomberman bm;
	/** Spielfeldobjekt Bomberman (Zweiter Spieler) */
	public Bomberman bm2;
	/** Anzahl der begehbaren Reihen */
	private int columns;
	/** Groesse der Bloecke (alle Bloecke sind quadratisch */
	public int blockLength;
	/** Explosionsradius */
	private int expRad;
	/** Bombe */
	public Bomb bomb;

	/** Ist Zweispielermodus angeschaltet? */
	public boolean two_player;

	public BMServer server;

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
	public Spielfeld(int width, int height, int col, int length, boolean mode,
			int[][] raster) {
		this.height = height;
		this.width = width;
		columns = col;
		blockLength = length;
		this.two_player = true;

		expRad = 1; // Explosionsradius in Feldern
		this.bm = new Bomberman(blockLength, blockLength, blockLength, expRad
				* blockLength, 1);

		this.bm2 = new Bomberman(width - (2 * blockLength), height
				- (2 * blockLength), blockLength, expRad * blockLength, 2);

		boolean bmLoaded = false;
		boolean bm2Loaded = false;
		this.raster = new int[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				this.raster[i][j] = raster[i][j];
				if (!bmLoaded) {
					if (raster[i][j] == -5) {
						bmLoaded = true;
						this.bm.setPosX(i);
						this.bm.setPosY(j);
					}
				}
				if (raster[i][j] == -5)
					this.raster[i][j] = 0;
				if (two_player) {
					if (!bm2Loaded) {
						if (raster[i][j] == -6) {
							bm2Loaded = true;
							this.bm2.setPosX(i);
							this.bm2.setPosY(j);
						}
					}
				}
				if (raster[i][j] == -6)
					this.raster[i][j] = 0;
			}

		this.add(this.bm);
		this.add(this.bm2);

		try {
			this.server = new BMServer();
			Thread t = new Thread(server);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Thread empfangen = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {

					switch (server.counter) {
					case 1: {
						int[][] r = getRaster();
						if (r[bm.getPosX()][bm.getPosY() - bm.getSteps()] <= 0)
							bm.moveUp();
						server.counter = 0;
						repaint();
					}
						break;
					case 2: {
						int[][] r = getRaster();
						if (r[bm.getPosX()][bm.getPosY() + bm.getSteps()] <= 0)
							bm.moveDown();
						server.counter = 0;
						repaint();
					}
						break;
					case 3: {
						// LINKS
						int[][] r = getRaster();
						if (r[bm.getPosX() - bm.getSteps()][bm.getPosY()] <= 0)
							bm.moveLeft();
						server.counter = 0;
						repaint();
					}
						break;
					case 4: {
						// RECHTS
						int[][] r = getRaster();
						if (r[bm.getPosX() + bm.getSteps()][bm.getPosY()] <= 0)
							bm.moveRight();
						server.counter = 0;
						repaint();
					}
						break;

					case 5: {
						int[][] r = getRaster();
						int n = bm.getNewBombIndex();
						if (r[bm.bombs.get(n).getPosX()][bm.bombs.get(n)
								.getPosY()] != 3) {
							bm.incBombs();
							r[bm.bombs.get(n).getPosX()][bm.bombs.get(n)
									.getPosY()] = 3;
							bm.bombs.get(n).setVisible(true);
							add(bm.bombs.get(n));
							repaint();
							if (two_player)
								bm.bombs.get(n).explode(Main.f.sp, bm2, bm);
							else
								bm.bombs.get(n).explode(Main.f.sp, bm2);
							repaint();
						}
					}
						break;
					}
				}
				// BOMBERMAN BEWEGEN

			}
		});
		empfangen.start();

	}

	public void setRaster(int[][] raster) {
		this.raster = raster;
	}

	public int[][] getRaster() {
		return this.raster;
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
					Boden b = new Boden(i, j, blockLength);
					b.paintObject(g);
				}
					break;
				case 1: {

					Mauer m = new Mauer(i, j, blockLength);
					m.paintObject(g);

				}
					break;
				case 2: {
					BreakableMauer brm = new BreakableMauer(i, j, blockLength);
					brm.paintObject(g);
				}
					break;
				case 4: {
					BreakableMauer brm = new BreakableMauer(i, j, blockLength);
					brm.paintObject(g);
				}
					break;

				case -5: {
					Boden b = new Boden(i, j, blockLength);
					b.paintObject(g);
					this.bm.paintObject();
				}
					break;
				case -6: {
					Boden b = new Boden(i, j, blockLength);
					b.paintObject(g);
					this.bm2.paintObject();
				}
					break;

				case -1: {
					Ausgang a = new Ausgang(i, j, blockLength);
					a.paintObject(g);

				}
					break;
				}
			}
		}

		// Bomberman zeichnen
		bm.paintObject();

		bm2.paintObject();

		for (int i = 0; i < this.bm.bombs.size(); i++)
			if (this.bm.bombs.get(i) != null
					&& this.bm.bombs.get(i).isVisible())
				this.bm.bombs.get(i).paintObject(g, this);

		for (int i = 0; i < this.bm2.bombs.size(); i++)
			if (this.bm2.bombs.get(i) != null
					&& this.bm2.bombs.get(i).isVisible())
				this.bm2.bombs.get(i).paintObject(g, this);
	}
}
