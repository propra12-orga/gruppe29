package StandardMP;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

public class OurJFileChooser extends JFileChooser {

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
