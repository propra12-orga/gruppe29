package Standard;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

public class OurJFileChooser extends JFileChooser {

	/**
	 * Erzeugt ein neues Objekt von OurJFileChooser
	 */
	public OurJFileChooser() {
		// TODO Auto-generated constructor stub
		this.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory()
						|| f.getName().toLowerCase().endsWith(".xml");
			}

			@Override
			public String getDescription() {
				return "Maps";
			}
		});
	}

	/**
	 * Erzeugt ein neues Objekt von OurJFileChooser mit angegebenen Ordnerpfad
	 * für die Dateien.
	 * 
	 * @param currentDirectoryPath
	 */
	public OurJFileChooser(String currentDirectoryPath) {
		super(currentDirectoryPath);
		this.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory()
						|| f.getName().toLowerCase().endsWith(".xml");
			}

			@Override
			public String getDescription() {
				return "Maps";
			}
		});
		// TODO Auto-generated constructor stub
	}

	/**
	 * Erzeugt ein neues Objekt von OurJFileChooser mit Referenz auf eine Datei
	 * und zugehörigem Ordnerpfad.
	 * 
	 * @param currentDirectory
	 */
	public OurJFileChooser(File currentDirectory) {
		super(currentDirectory);
		this.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory()
						|| f.getName().toLowerCase().endsWith(".xml");
			}

			@Override
			public String getDescription() {
				return "Maps";
			}
		});
		// TODO Auto-generated constructor stub
	}

	/**
	 * Erzeugt ein neues Objekt von OurJFileChooser mit einem FileSystemView.
	 * 
	 * @param fsv
	 */
	public OurJFileChooser(FileSystemView fsv) {
		super(fsv);
		this.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory()
						|| f.getName().toLowerCase().endsWith(".xml");
			}

			@Override
			public String getDescription() {
				return "Maps";
			}
		});
		// TODO Auto-generated constructor stub
	}

	/**
	 * Erzeugt ein neues Objekt von OurJFileChooser mit einer Datei und dem
	 * Ordnerpfad und einem FileSystemView.
	 * 
	 * @param currentDirectory
	 * @param fsv
	 */
	public OurJFileChooser(File currentDirectory, FileSystemView fsv) {
		super(currentDirectory, fsv);
		this.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory()
						|| f.getName().toLowerCase().endsWith(".xml");
			}

			@Override
			public String getDescription() {
				return "Maps";
			}
		});
		// TODO Auto-generated constructor stub
	}

	/**
	 * Erzeugt ein neues Objekt von OurJFileChooser mit einem String für den
	 * Ordnerpfad und einem FileSystemView.
	 * 
	 * @param currentDirectoryPath
	 * @param fsv
	 */
	public OurJFileChooser(String currentDirectoryPath, FileSystemView fsv) {
		super(currentDirectoryPath, fsv);
		this.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory()
						|| f.getName().toLowerCase().endsWith(".xml");
			}

			@Override
			public String getDescription() {
				return "Maps";
			}
		});
		// TODO Auto-generated constructor stub
	}

}
