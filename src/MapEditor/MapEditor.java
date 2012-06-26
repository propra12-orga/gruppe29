package MapEditor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import parser.RasterParser;
import parser.XMLParser;
import Standard.OurJFileChooser;

public class MapEditor extends JFrame implements ActionListener {

	private int sizeX, sizeY, columns, length;
	int[][] raster, raster2;
	protected EditorFeld ep;

	public MapEditor() {
		XMLParser xml = new XMLParser("maps/Default.xml");
		RasterParser rp = new RasterParser();
		columns = xml.getColumns();
		length = 50;
		sizeX = sizeY = columns * length;
		raster2 = xml.readXML(columns);
		raster = rp.transform(raster2, columns, length);

		this.setResizable(false);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setSize(sizeX, sizeY + 3 * length);
		this.setTitle("Bomberman - Mapeditor");
		this.setLayout(new BorderLayout());

		JMenuBar menueLeiste = new JMenuBar();
		JMenu menue = new JMenu("Optionen");

		JMenuItem speichern = new JMenuItem("Speichern");
		speichern.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent object) {
				// SPEICHERN
				OurJFileChooser jfc = new OurJFileChooser("maps/");
				int status = jfc.showSaveDialog(null);
				if (status == JFileChooser.APPROVE_OPTION) {
					XMLParser xml = new XMLParser(jfc.getSelectedFile()
							.toString());
					RasterParser rp = new RasterParser();
					xml.writeXML(rp.untransform(ep.raster, columns, length),
							columns);
					System.out.println("Speichern");
				} else
					System.out.println("Dateiauswahl abgebrochen");
			}
		});
		JMenuItem laden = new JMenuItem("Laden");
		laden.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent object) {
				// LADEN
				OurJFileChooser jfc = new OurJFileChooser("maps/");
				int status = jfc.showOpenDialog(null);
				if (status == JFileChooser.APPROVE_OPTION) {
					XMLParser xml = new XMLParser(jfc.getSelectedFile()
							.toString());
					RasterParser rp = new RasterParser();
					raster = rp.transform(xml.readXML(columns), columns, length);
					System.out.println("Laden");
					ep.raster = raster;
					ep.repaint();
				} else
					System.out.println("Dateiauswahl abgebrochen");
			}
		});
		JMenuItem beenden = new JMenuItem("Beenden");
		beenden.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent object) {
				dispose();
			}
		});
		JMenuItem bar = new JMenuItem("Toolbar");
		bar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent object) {
				JFrame fc = new JFrame("Toolbar");
				fc.setLocation(700, 300);
				fc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fc.setSize(360, 100);

				JToolBar toolbar = new JToolBar();

				JButton breakable = new JButton(new ImageIcon(
						"img/breakable.png"));
				JButton ausgang = new JButton(new ImageIcon("img/ausgang.png"));
				JButton unbreakable = new JButton(new ImageIcon(
						"img/unbreakable.png"));
				JButton bomberman1 = new JButton(new ImageIcon(
						"img/Bomberman.png"));
				JButton bomberman2 = new JButton(new ImageIcon(
						"img/Bomberman2.png"));
				breakable
						.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(
									java.awt.event.ActionEvent object) {
								ep.setTheChosenOne(2);
							}
						});
				ausgang.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(
							java.awt.event.ActionEvent object) {
						ep.setTheChosenOne(-1);
					}
				});
				unbreakable
						.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(
									java.awt.event.ActionEvent object) {
								ep.setTheChosenOne(1);
							}
						});
				bomberman1
						.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(
									java.awt.event.ActionEvent object) {
								ep.setTheChosenOne(5);
							}
						});
				bomberman2
						.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(
									java.awt.event.ActionEvent object) {
								ep.setTheChosenOne(6);
							}
						});
				toolbar.add(breakable);
				toolbar.add(ausgang);
				toolbar.add(unbreakable);
				toolbar.add(bomberman1);
				toolbar.add(bomberman2);
				toolbar.setSize(sizeX, length);
				fc.add(toolbar);
				fc.setVisible(true);
			}
		});

		menueLeiste.add(menue);
		menue.add(bar);
		menue.addSeparator();
		menue.add(speichern);
		menue.add(laden);
		menue.addSeparator();
		menue.add(beenden);

		this.setJMenuBar(menueLeiste);

		ep = new EditorFeld(sizeX, sizeY, columns, length, raster);

		this.add(ep, BorderLayout.CENTER);
		this.addKeyListener(ep);

		this.setFocusable(true);

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
