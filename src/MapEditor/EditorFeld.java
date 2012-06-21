package MapEditor;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Spielobjekte.Ausgang;
import Spielobjekte.Boden;
import Spielobjekte.BreakableMauer;
import Spielobjekte.Mauer;

public class EditorFeld extends JPanel implements KeyListener {

	@Override
	/**
	 * Abfrage der gedrueckten Tasten und Eventhandler für beide Bomberman (Bewegung und Bombenwurf)
	 */
	public void keyPressed(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_UP: {
			if (this.raster[this.builder.getPosX()][this.builder.getPosY()
					- this.builder.getSteps()] != 1) {
				this.builder.moveUp();
				repaint();
				this.builder.paintObject();
			}
		}
			break;
		case KeyEvent.VK_DOWN: {
			if (this.raster[this.builder.getPosX()][this.builder.getPosY()
					+ this.builder.getSteps()] != 1) {
				this.builder.moveDown();
				repaint();
				this.builder.paintObject();
			}
		}
			break;
		case KeyEvent.VK_LEFT: {
			if (this.raster[this.builder.getPosX() - this.builder.getSteps()][this.builder
					.getPosY()] != 1) {
				this.builder.moveLeft();
				repaint();
				this.builder.paintObject();
			}
		}
			break;
		case KeyEvent.VK_RIGHT: {
			if (this.raster[this.builder.getPosX() + this.builder.getSteps()][this.builder
					.getPosY()] != 1) {
				this.builder.moveRight();
				repaint();
				this.builder.paintObject();
			}
		}
			break;
		case KeyEvent.VK_SPACE: {
			if (this.raster[this.builder.getPosX()][this.builder.getPosY()] == 0)
				for (int i = 0; i < this.blockLength; i++)
					for (int j = 0; j < this.blockLength; j++)
						this.raster[this.builder.getPosX() + i][this.builder
								.getPosY() + j] = 2;
			else if (this.raster[this.builder.getPosX()][this.builder.getPosY()] == 2)
				for (int i = 0; i < this.blockLength; i++)
					for (int j = 0; j < this.blockLength; j++)
						this.raster[this.builder.getPosX() + i][this.builder
								.getPosY() + j] = 0;
			repaint();
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
	public Builder builder;
	/** Anzahl der begehbaren Reihen */
	private int columns;
	/** Groesse der Bloecke (alle Bloecke sind quadratisch */
	public int blockLength;
	/** Explosionsradius */
	private int expRad;

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
	public EditorFeld(int width, int height, int col, int length, int[][] raster) {
		this.height = height;
		this.width = width;
		columns = col;
		blockLength = length;

		expRad = 2;
		this.builder = new Builder(width - ((columns - 1) * blockLength),
				height - ((columns - 1) * blockLength), blockLength, expRad
						* blockLength, 1);

		this.add(this.builder);

		this.raster = new int[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				this.raster[i][j] = raster[i][j];
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
				case -1: {
					Ausgang a = new Ausgang(i, j, blockLength);
					a.paintObject(g);

				}
					break;
				}
			}
		}

		// Bomberman zeichnen
		builder.paintObject();

	}
}
