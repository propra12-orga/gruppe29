import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Fenster implements ActionListener {
	private JFrame f;
	private JFrame fc;
	private JPanel p;
	private Spielfeld sp;
	private int width;
	private int height;
	private int columns;
	private int length;
	private int mode;

	// Constructor
	public Fenster(int width, int height, int col, int blockLength) {
		this.width = width;
		this.height = height;
		columns = col;
		length = blockLength;
		sp = new Spielfeld(width, height, columns, length);
		initFrame();
	}

	private void initFrame() {
		f = new JFrame("Bomberman");
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(width, height + length);

		p = new JPanel();
		f.setContentPane(p);
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));

		p.add(sp);
		f.addKeyListener(sp);

		p.setSize(width, height);
		initMenue();
		f.setVisible(true);
	}

	private void initMenue() {
		// MENUEBAR
		JMenuBar menueLeiste = new JMenuBar();
		JMenu menue = new JMenu("Optionen");
		JMenuItem beenden = new JMenuItem("Beenden");
		beenden.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent object) {
				System.exit(0);
			}
		});
		JMenuItem start = new JMenuItem("Neues Spiel");
		start.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent object) {
				f.dispose();
				restart(1);
			}
		});
		JMenuItem credits = new JMenuItem("Credits");
		credits.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent object) {
				fc = new JFrame("Credits");
				fc.setLocation((f.getWidth() - fc.getSize().width) / 2,
						(f.getHeight() - fc.getSize().height) / 2);
				fc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fc.setSize(300, 200);
				JPanel panel = new JPanel();
				JLabel namen = new JLabel(
						"<html><body><br>Dieses unglaublich gute Projekt<br> wurde geschrieben von:<br><br>Marie Arsoy<br>Moritz Kanzler<br>Tobias Küper<br>Sascha Kuhnke<br>Tim Fischer<br></body></html>");
				panel.add(namen);
				JButton close = new JButton("Schließen");
				close.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(
							java.awt.event.ActionEvent object) {
						fc.dispose();
					}
				});
				panel.add(close);
				fc.add(panel);
				fc.setVisible(true);
			}
		});
		JMenu mp = new JMenu("2-Spieler-Modus");
		JMenuItem mehrspieler;
		if (this.mode == 2)
			mehrspieler = new JMenuItem("Neues Spiel");
		else
			mehrspieler = new JMenuItem("Starten");
		mehrspieler.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent object) {
				f.dispose();
				restart(2);

			}
		});
		JMenuItem mehrspielerExit = new JMenuItem("Beenden");
		mehrspielerExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent object) {
				f.dispose();
				restart(1);
			}
		});
		menueLeiste.add(menue);
		menue.add(start);
		menue.add(credits);
		menue.add(beenden);
		menueLeiste.add(mp);
		mp.add(mehrspieler);
		mp.add(mehrspielerExit);
		f.setJMenuBar(menueLeiste);
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

	public void restart(int mode) {
		this.mode = mode;
		if (mode == 2) {
			sp = new Spielfeld(width, height, columns, length, true);
			sp.two_player = true;
			sp.mehrspielermodus();
			System.out.println("Neustart im 2 Spielermodus");
		} else {
			sp = new Spielfeld(width, height, columns, length);
			System.out.println("Neustart im 1 Spielermodus");
		}
		initFrame();
	}

	public void dispose(String messageDialog, boolean renew) {
		if (renew) {
			int answer = JOptionPane.showConfirmDialog(f, messageDialog
					+ " Möchtest du Neustarten?", "Game Over",
					JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION)
				f.dispose();
			else
				System.exit(0);
		} else {
			JOptionPane.showMessageDialog(f, messageDialog);
			f.dispose();
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
