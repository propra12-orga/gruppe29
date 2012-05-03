import java.awt.Graphics;

import javax.swing.JPanel;

class Mauer extends JPanel {

	public Mauer() {

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < 200; i = i + 20)
			for (int j = 0; j < 200; j = j + 20)
				g.fillRect(i, j, 10, 10);
	}
}
