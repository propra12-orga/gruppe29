import javax.swing.JFrame;

public class Fenster {

	public Fenster() {
		JFrame f = new JFrame("Bomberman");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600, 600);
		f.add(new Mauer(10, 10));
		f.add(new Mauer(200, 50));
		f.setVisible(true);
	}
}
