import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import parser.RasterParser;
import parser.XMLParser;

public class Fenster implements ActionListener {
	private JFrame f;
	private JFrame fc;
	private JPanel p;
	Clip clip;
	private boolean play;
	private boolean stop;
	/** Erstellung eines Spielfeldes sp */
	private Spielfeld sp;
	private int width;
	private int height;
	private int columns;
	private int length;
	private int mode;

	/**
	 * Fenster erstellen
	 * 
	 * @param width
	 * @param height
	 * @param col
	 * @param blockLength
	 */
	public Fenster(int width, int height, int col, int blockLength) {
		this.width = width;
		this.height = height;
		columns = col;
		length = blockLength;
		play = true;
		stop = false;
		sp = new Spielfeld(width, height, columns, length, false);
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("melodie.wav"));
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					audioInputStream);
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			bufferedInputStream.read(audio, 0, size);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		initFrame();
	}

	/** Frame und Panel erstellen */
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

		playSound();
	}

	private void playSound() {
		if (play)
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		else
			clip.stop();
	}

	/** Menueleiste */
	private void initMenue() {
		// MENUEBAR
		JMenuBar menueLeiste = new JMenuBar();
		JMenu menue = new JMenu("Optionen");

		JMenuItem speichern = new JMenuItem("Speichern");
		speichern.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent object) {
				// SPEICHERN
				XMLParser xml = new XMLParser("save.xml");
				RasterParser rp = new RasterParser();
				xml.writeXML(rp.untransform(sp.raster, columns, length),
						columns);

			}
		});
		JMenuItem laden = new JMenuItem("Laden");
		laden.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent object) {
				// LADEN
				XMLParser xml = new XMLParser("save.xml");
				RasterParser rp = new RasterParser();
				Main.raster = rp.transform(xml.readXML(columns), columns,
						length);
				System.out.println("Laden");
				f.dispose();
				restart(Main.f.mode);
			}
		});
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
		JMenuItem sound = new JMenuItem("Sound An/Aus");
		sound.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent object) {
				if (play) {
					play = false;
					playSound();
				} else {
					play = true;
					playSound();
				}
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
		menue.addSeparator();
		menue.add(speichern);
		menue.add(laden);
		menue.addSeparator();
		menue.add(sound);
		menue.add(credits);
		menue.addSeparator();
		menue.add(beenden);
		menueLeiste.add(mp);
		mp.add(mehrspieler);
		mp.add(mehrspielerExit);
		f.setJMenuBar(menueLeiste);
	}

	/** getter and setter */
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

	/**
	 * Neustarten des Spieles
	 * 
	 * @param mode
	 */
	public void restart(int mode) {
		this.mode = mode;
		sp.bomb.numbOfBombs1 = 0;
		sp.bm.bombs = new ArrayList<Bomb>();
		stop = true;
		clip.stop();

		if (mode == 2) {
			System.out.println(this.sp.bm.getScore());
			// System.out.println(this.sp.bm2.getScore());
			sp = new Spielfeld(width, height, columns, length, true);
			System.out.println("Neustart im 2 Spielermodus");
			sp.bomb.numbOfBombs2 = 0;
			sp.bm2.bombs = new ArrayList<Bomb>();
			stop = false;
		} else {
			sp = new Spielfeld(width, height, columns, length, false);
			System.out.println("Neustart im 1 Spielermodus");
			stop = false;
		}
		initFrame();
	}

	/**
	 * Verstecken des alten Fensters
	 * 
	 * @param messageDialog
	 * @param renew
	 */
	public void dispose(String messageDialog, boolean renew) {
		if (renew) {
			int answer = JOptionPane.showConfirmDialog(f, messageDialog
					+ " Möchtest du Neustarten?", "Game Over",
					JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION) {
				stop = true;
				clip.stop();
				f.dispose();
			} else
				System.exit(0);
		} else {
			stop = true;
			clip.stop();
			JOptionPane.showMessageDialog(f, messageDialog);
			f.dispose();
		}
	}

	/**
	 * Aktionen des ActionListeners
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
