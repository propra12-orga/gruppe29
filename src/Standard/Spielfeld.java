package Standard;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import Spielobjekte.Ausgang;
import Spielobjekte.Boden;
import Spielobjekte.BreakableMauer;
import Spielobjekte.Mauer;
import Spielobjekte.PowerUp;

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
			}
				break;

			case -2: {
				this.bm.moveUp();
				for (int i = 0; i < powerups.size(); i++) {
					if ((this.powerups.get(i).getPosX() == this.bm.getPosX())
							&& (this.powerups.get(i).getPosY() == this.bm
									.getPosY())) {
						this.powerups.get(i).upgrade(bm);
						decNumbOfPowerUps();
						break;
					}
				}
				repaint();
				this.bm.paintObject();
			}
				break;

			case -1: {
				this.bm.moveUp();
				repaint();
				this.bm.paintObject();
				Main.f.dispose("Du hast dich bis zum Ausgang durchgeschlagen!",
						true);
				if (two_player == true) {
					Main.f.restart(2, true);
				} else {
					Main.f.restart(1, true);
				}
			}
				break;

			}
		}
			break;
		case KeyEvent.VK_DOWN: {
			switch (this.raster[this.bm.getPosX()][this.bm.getPosY()
					+ this.bm.getSteps()]) {
			case 0: {
				this.bm.moveDown();
				repaint();
				this.bm.paintObject();
			}
				break;

			case -2: {
				this.bm.moveDown();
				for (int i = 0; i < powerups.size(); i++) {
					if ((this.powerups.get(i).getPosX() == this.bm.getPosX())
							&& (this.powerups.get(i).getPosY() == this.bm
									.getPosY())) {
						this.powerups.get(i).upgrade(bm);
						decNumbOfPowerUps();
						break;
					}
				}
				repaint();
				this.bm.paintObject();
			}
				break;

			case -1: {
				this.bm.moveDown();
				repaint();
				this.bm.paintObject();
				Main.f.dispose("Du hast dich bis zum Ausgang durchgeschlagen!",
						true);
				if (two_player == true) {
					Main.f.restart(2, true);
				} else {
					Main.f.restart(1, true);
				}
			}
				break;
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

			}
				break;

			case -2: {
				this.bm.moveLeft();
				for (int i = 0; i < powerups.size(); i++) {
					if ((this.powerups.get(i).getPosX() == this.bm.getPosX())
							&& (this.powerups.get(i).getPosY() == this.bm
									.getPosY())) {
						this.powerups.get(i).upgrade(bm);
						decNumbOfPowerUps();
						break;
					}
				}
				repaint();
				this.bm.paintObject();
			}
				break;

			case -1: {
				this.bm.moveLeft();
				repaint();
				this.bm.paintObject();
				Main.f.dispose("Du hast dich bis zum Ausgang durchgeschlagen!",
						true);
				if (two_player == true) {
					Main.f.restart(2, true);
				} else {
					Main.f.restart(1, true);
				}

			}
				break;

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

			}
				break;

			case -2: {
				this.bm.moveRight();
				for (int i = 0; i < powerups.size(); i++) {
					if ((this.powerups.get(i).getPosX() == this.bm.getPosX())
							&& (this.powerups.get(i).getPosY() == this.bm
									.getPosY())) {
						this.powerups.get(i).upgrade(bm);
						decNumbOfPowerUps();
						break;
					}
				}
				repaint();
				this.bm.paintObject();
			}
				break;

			case -1: {
				this.bm.moveRight();
				repaint();
				this.bm.paintObject();
				Main.f.dispose("Du hast dich bis zum Ausgang durchgeschlagen!",
						true);
				if (two_player == true) {
					Main.f.restart(2, true);
				} else {
					Main.f.restart(1, true);
				}

			}
				break;

			}
		}
			break;
		case KeyEvent.VK_SPACE: {
			if (this.bm.getPlacedBombs() < this.bm.getAmountOfBombs()) {
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
			}
		}
			break;
		case KeyEvent.VK_W: {
			switch (this.raster[this.bm2.getPosX()][this.bm2.getPosY()
					- this.bm2.getSteps()]) {
			case 0: {
				this.bm2.moveUp();
				repaint();
				this.bm2.paintObject();

			}
				break;

			case -1: {
				this.bm2.moveUp();
				repaint();
				this.bm2.paintObject();
				Main.f.dispose("Du hast dich bis zum Ausgang durchgeschlagen!",
						true);
				if (two_player == true) {
					Main.f.restart(2, true);
				} else {
					Main.f.restart(1, true);
				}

			}
				break;

			}

		}
			break;
		case KeyEvent.VK_S: {
			switch (this.raster[this.bm2.getPosX()][this.bm2.getPosY()
					+ this.bm2.getSteps()]) {
			case 0: {
				this.bm2.moveDown();
				repaint();
				this.bm2.paintObject();

			}
				break;

			case -1: {
				this.bm2.moveDown();
				repaint();
				this.bm2.paintObject();
				Main.f.dispose("Du hast dich bis zum Ausgang durchgeschlagen!",
						true);
				if (two_player == true) {
					Main.f.restart(2, true);
				} else {
					Main.f.restart(1, true);
				}

			}
				break;

			}

		}
			break;
		case KeyEvent.VK_A: {
			switch (this.raster[this.bm2.getPosX() - this.bm2.getSteps()][this.bm2
					.getPosY()]) {
			case 0: {
				this.bm2.moveLeft();
				repaint();
				this.bm2.paintObject();

			}
				break;

			case -1: {
				this.bm2.moveLeft();
				repaint();
				this.bm2.paintObject();
				Main.f.dispose("Du hast dich bis zum Ausgang durchgeschlagen!",
						true);
				if (two_player == true) {
					Main.f.restart(2, true);
				} else {
					Main.f.restart(1, true);
				}

			}
				break;

			}

		}
			break;
		case KeyEvent.VK_D: {
			switch (this.raster[this.bm2.getPosX() + this.bm2.getSteps()][this.bm2
					.getPosY()]) {
			case 0: {
				this.bm2.moveRight();
				repaint();
				this.bm2.paintObject();

			}
				break;

			case -1: {
				this.bm2.moveRight();
				repaint();
				this.bm2.paintObject();
				Main.f.dispose("Du hast dich bis zum Ausgang durchgeschlagen!",
						true);
				if (two_player == true) {
					Main.f.restart(2, true);
				} else {
					Main.f.restart(1, true);
				}

			}
				break;

			}

		}
			break;
		case KeyEvent.VK_Q: {
			if (this.bm2.getPlacedBombs() < this.bm.getAmountOfBombs()) {
				int n = this.bm2.getNewBombIndex();
				if (this.raster[this.bm2.bombs.get(n).getPosX()][this.bm2.bombs
						.get(n).getPosY()] != 3) {
					this.bm2.incBombs();
					this.raster[this.bm2.bombs.get(n).getPosX()][this.bm2.bombs
							.get(n).getPosY()] = 3;
					this.bm2.bombs.get(n).setVisible(true);
					this.add(this.bm2.bombs.get(n));
					repaint();
					this.bm2.bombs.get(n).explode(this, this.bm, this.bm2);
					repaint();
				}
			}
		}
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

	/** Breite des Fensters */
	private int width;
	/** Hoehe des Fensters */
	private int height;

	public List<PowerUp> powerups;
	private int numbOfPowerUps;

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
		this.two_player = false;

		this.powerups = new ArrayList<PowerUp>();
		this.numbOfPowerUps = 0;

		expRad = 1; // Explosionsradius in Feldern
		this.bm = new Bomberman(blockLength, blockLength, blockLength, expRad
				* blockLength, 1);

		if (mode) {
			this.two_player = true;
			this.bm2 = new Bomberman(width - (2 * blockLength), height
					- (2 * blockLength), blockLength, expRad * blockLength, 2);
		}

		boolean bmLoaded = false;
		boolean bm2Loaded = false;
		this.raster = new int[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				this.raster[i][j] = raster[i][j];
				if (!bmLoaded) {
					if (raster[i][j] == 5) {
						bmLoaded = true;
						this.bm.setPosX(i);
						this.bm.setPosY(j);
					}
				}
				if (raster[i][j] == 5)
					this.raster[i][j] = 0;
				if (two_player) {
					if (!bm2Loaded) {
						if (raster[i][j] == 6) {
							bm2Loaded = true;
							this.bm2.setPosX(i);
							this.bm2.setPosY(j);
						}
					}
				}
				if (raster[i][j] == 6)
					this.raster[i][j] = 0;
			}

		this.add(this.bm);
		if (this.bm2 != null)
			this.add(this.bm2);
	}

	public void incNumbOfPowerUps() {
		numbOfPowerUps++;
	}

	public void decNumbOfPowerUps() {
		numbOfPowerUps--;
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
				case 5: {
					this.bm.paintObject();
				}
					break;
				case 6: {
					if (bm2 != null)
						this.bm2.paintObject();
				}
					break;
				case -1: {
					Ausgang a = new Ausgang(i, j, blockLength);
					a.paintObject(g);

				}
					break;
				case -2: {
					for (int k = 0; k < powerups.size(); k++)
						if ((powerups.get(k).getPosX() == i)
								&& (powerups.get(k).getPosY() == j))
							powerups.get(k).paintObject(g);
				}
					break;
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
				this.bm.bombs.get(i).paintObject(g, this);

		if (bm2 != null) {
			for (int i = 0; i < this.bm2.bombs.size(); i++)
				if (this.bm2.bombs.get(i) != null
						&& this.bm2.bombs.get(i).isVisible())
					this.bm2.bombs.get(i).paintObject(g, this);
		}
	}
}
