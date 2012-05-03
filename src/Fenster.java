import javax.swing.JFrame;

public class Fenster {

	public Fenster() {
		JFrame f = new JFrame("Bomberman");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(200, 200);
		f.add(new Mauer());
		f.setVisible(true);
	}
}
