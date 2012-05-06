import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Spielfeld extends JPanel implements KeyListener {

	@Override
	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("UP");
			this.bm.moveUp();
			repaint();
		}
		if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("DOWN");
			this.bm.moveDown();
			repaint();
		}
		if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
			System.out.println("LEFT");
			this.bm.moveLeft();
			repaint();
		}
		if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("RIGHT");
			this.bm.moveRight();
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	// Groesse des Fensters
	private int width;
	private int height;

	// Spielfeldobjekte
	private Bomberman bm;

	// Groesse der Bloecke
	private final int blockWidth;
	private final int blockHeight;

	public Spielfeld(int width, int height) {
		this.height = height;
		this.width = width;
		blockHeight = height / 10;
		blockWidth = width / 10;

		this.bm = new Bomberman(100, 100);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Rand zeichnen
		for (int i = 0; i < width; i = i + blockWidth / 2) {
			g.fillRect(i, 0, blockWidth, blockHeight / 2);
			g.fillRect(i, height - blockHeight / 2, blockWidth, blockHeight / 2);
		}
		for (int i = 0; i < height; i = i + blockHeight / 2) {
			g.fillRect(0, i, blockWidth / 2, blockHeight);
			g.fillRect(width - blockWidth / 2, i, blockWidth / 2, blockHeight);
		}

		// Blöcke zeichnen
		for (int i = blockWidth + blockWidth / 2; i < width; i = i + 2
				* blockWidth) {
			for (int j = blockHeight + blockHeight / 2; j < height; j = j + 2
					* blockHeight) {
				g.fillRect(i, j, blockWidth, blockHeight);
			}
		}

		// Bomberman zeichnen
		bm.paintBomberman(g);
	}

}
