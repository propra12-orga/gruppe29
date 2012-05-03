import javax.swing.*;
import java.awt.*;

public class Fenster {
	
	public Fenster() {
		JFrame f = new JFrame("Bomberman");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600, 600);
		f.setVisible(true);
	}
	
	public void recteck(Graphics g) {
		g.fillRect(50, 50, 100, 100);
	}
}
