import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Fenster {

	private int width;
	private int height;

	// Constructor
	public Fenster(int width, int height) {
		width(width);
		height(height);
		Spielfeld sp = new Spielfeld(width, height);

		JFrame f = new JFrame("Bomberman");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(width, height + 24);

		JPanel p = new JPanel();
		f.setContentPane(p);
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));

		p.add(sp);
		f.addKeyListener(sp);

		p.setSize(width, height);
		f.setVisible(true);

		// MENUEBAR

		JMenuBar menueLeiste = new JMenuBar();
		JMenu menue = new JMenu("Exit");
		JMenuItem beenden = new JMenuItem("beenden");
		menueLeiste.add(menue, BorderLayout.NORTH);
		menue.add(beenden);
		f.add(menueLeiste, BorderLayout.NORTH);
		// f.setJMenuBar(menueLeiste);
	}

	// getter and setter
	public int width() {
		return width;
	}

	public void width(int width) {
		this.width = width;
	}

	public int height() {
		return height;
	}

	public void height(int height) {
		this.height = height;
	}
}
