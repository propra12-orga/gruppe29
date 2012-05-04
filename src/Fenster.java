import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenster {
	private int width;
	private int height;

	// Constructor
	public Fenster(int width, int height) {
		width(width);
		height(height);
		JFrame f = new JFrame("Bomberman");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(width, height + 24);
		JPanel p = new JPanel();
		f.setContentPane(p);
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.add(new Mauer(width, height));
		p.setSize(width, height);
		f.setVisible(true);
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