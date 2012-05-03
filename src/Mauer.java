import java.awt.Graphics;

import javax.swing.JPanel;

class Mauer extends JPanel {

	public int x;
	public int y;

	public Mauer(int x, int y) {
		this.x = x;
		this.y = y;

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(this.x, this.y, this.x, this.y);
	}
}
