import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenster {

	public Fenster() {
		JFrame f = new JFrame("Bomberman");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600, 600);
		JPanel p = new JPanel();
		f.setContentPane(p);
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.add(new Mauer(10, 10));
		p.add(new Mauer(200, 50));
		p.add(new Mauer(1, 200));
		f.setVisible(true);
	}
}
