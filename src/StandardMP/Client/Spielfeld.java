package StandardMP.Client;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import server.BMClient;
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
			if (this.raster[this.bm.getPosX()][this.bm.getPosY()
					- this.bm.getSteps()] <= 0) {

				this.raster[this.bm.getPosX()][this.bm.getPosY()] = 0;
				this.bm.moveUp();
				this.raster[this.bm.getPosX()][this.bm.getPosY()] = -5;

				try {
					client.sendToServer(1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				repaint();

			}
			break;
		}
		case KeyEvent.VK_DOWN: {
			if (this.raster[this.bm.getPosX()][this.bm.getPosY()
					+ this.bm.getSteps()] <= 0) {

				this.raster[this.bm.getPosX()][this.bm.getPosY()] = 0;
				this.bm.moveDown();
				this.raster[this.bm.getPosX()][this.bm.getPosY()] = -5;

				try {
					client.sendToServer(2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				repaint();

			}
		}
			break;

		case KeyEvent.VK_LEFT: {
			if (this.raster[this.bm.getPosX() - this.bm.getSteps()][this.bm
					.getPosY()] <= 0) {

				this.raster[this.bm.getPosX()][this.bm.getPosY()] = 0;
				this.bm.moveLeft();
				this.raster[this.bm.getPosX()][this.bm.getPosY()] = -5;

				try {
					client.sendToServer(3);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				repaint();

			}
		}
			break;
		case KeyEvent.VK_RIGHT: {
			if (this.raster[this.bm.getPosX() + this.bm.getSteps()][this.bm
					.getPosY()] <= 0) {

				this.raster[this.bm.getPosX()][this.bm.getPosY()] = 0;
				this.bm.moveRight();
				this.raster[this.bm.getPosX()][this.bm.getPosY()] = -5;

				try {
					client.sendToServer(4);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				repaint();

			}
		}
			break;
		case KeyEvent.VK_SPACE: {
			int n = this.bm.getNewBombIndex();
			if (this.raster[this.bm.bombs.get(n).getPosX()][this.bm.bombs
					.get(n).getPosY()] != 3) {
				this.bm.incBombs();
				this.raster[this.bm.bombs.get(n).getPosX()][this.bm.bombs
						.get(n).getPosY()] = 3;
				this.bm.bombs.get(n).setVisible(true);
				this.add(this.bm.bombs.get(n));
				repaint();
				if (two_player)
					this.bm.bombs.get(n).explode(this, this.bm, this.bm2);
				else
					this.bm.bombs.get(n).explode(this, this.bm);
				repaint();
			}
			try {
				client.sendToServer(5);
			} catch (Exception e) {
				// TODO Auto-generated catch block
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

	public BMClient client;

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
			this.client = new BMClient();
			Thread t = new Thread(client);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Thread empfangen = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					switch (client.counter) {
					case 1: {
						// OBEN
						int[][] r = getRaster();
						if (r[bm2.getPosX()][bm2.getPosY() - bm2.getSteps()] <= 0)
							bm2.moveUp();
						client.counter = 0;
						repaint();
					}
						break;
					case 2: {
						// UNTEN
						int[][] r = getRaster();
						if (r[bm2.getPosX()][bm2.getPosY() + bm2.getSteps()] <= 0)
							bm2.moveDown();
						client.counter = 0;
						repaint();
					}
						break;
					case 3: {
						// LINKS
						int[][] r = getRaster();
						if (r[bm2.getPosX() - bm2.getSteps()][bm2.getPosY()] <= 0)
							bm2.moveLeft();
						client.counter = 0;
						repaint();
					}
						break;
					case 4: {
						// RECHTS
						int[][] r = getRaster();
						if (r[bm2.getPosX() + bm2.getSteps()][bm2.getPosY()] <= 0)
							bm2.moveRight();
						client.counter = 0;
						repaint();
					}
						break;
					case 5: {
						// BOMBE
						int[][] r = getRaster();
						int n = bm2.getNewBombIndex();
						if (r[bm2.bombs.get(n).getPosX()][bm2.bombs.get(n)
								.getPosY()] != 3) {
							bm2.incBombs();
							r[bm2.bombs.get(n).getPosX()][bm2.bombs.get(n)
									.getPosY()] = 3;
							bm2.bombs.get(n).setVisible(true);
							add(bm2.bombs.get(n));
							repaint();
							if (two_player)
								bm2.bombs.get(n).explode(Main.f.sp, bm, bm2);
							else
								bm2.bombs.get(n).explode(Main.f.sp, bm2);
							repaint();
						}
						break;
					}
					}
				}

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
