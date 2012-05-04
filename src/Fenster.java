import javax.swing.JFrame;

public class Fenster {
	private int width;
	private int height;

	// Constructor
	public Fenster(int width, int height) {
		width(width);
		height(height);
		JFrame f = new JFrame("Bomberman");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
<<<<<<< HEAD
		f.setSize(width, height + 24);
		JPanel p = new JPanel();
		f.setContentPane(p);
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.add(new Mauer(width, height));
=======
		f.setSize(200, 200);
		f.add(new Mauer());
>>>>>>> a10cc734474e7797456bb44db863e52d31d47452
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
