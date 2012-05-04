import java.awt.Graphics;

import javax.swing.JPanel;

public class Mauer extends JPanel {
	// Groesse des Fensters
	private int width;
	private int height;

	// Groesse der Bloecke
	private final int blockWidth;
	private final int blockHeight;

	public Mauer(int width, int height) {
		this.height = height;
		this.width = width;
		blockHeight = height / 10;
		blockWidth = width / 10;
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
	}

}