package parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

public class XMLParser {

	private String filename;

	public XMLParser(String filename) {
		this.filename = filename;
	}

	public int getColumns() {
		Document doc = null;
		try {
			doc = new SAXBuilder().build(new File(this.filename));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		return Integer.parseInt(root.getChild("header").getChild("dimension")
				.getValue());
	}

	/**
	 * Methode zum Einlesen des Spielfelds aus der XML-Datei "Bomberman.xml". Es
	 * wird ein blockraster erstellt.
	 * 
	 * @throws IOException
	 *             , JDOMException
	 */

	public int[][] readXML(int columns) {
		Document doc = null;
		try {
			doc = new SAXBuilder().build(new File(this.filename));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = doc.getRootElement();

		int[][] raster = new int[columns][columns];
		for (int i = 0; i < columns; i++)
			for (int j = 0; j < columns; j++) {
				Element col = root.getChild("Column" + i);
				Element row = col.getChild("Row" + j);
				raster[i][j] = Integer.parseInt(row.getValue());
			}
		return raster;
	}

	public void writeXML(int[][] r, int columns) {
		Element root = new Element("Bomberman");
		Element header = new Element("header");
		Element dimension = new Element("dimension");
		dimension.setText(Integer.toString(columns));
		header.addContent(dimension);
		root.addContent(header);

		for (int i = 0; i < columns; i++) {
			Element col = new Element("Column" + i);
			for (int j = 0; j < columns; j++) {
				Element row = new Element("Row" + j);
				row.setText(Integer.toString(r[i][j]));
				col.addContent(row);
			}
			root.addContent(col);
		}

		Document doc = new Document(root);
		Writer fw = null;
		try {
			fw = new FileWriter(new File(this.filename));

			XMLOutputter out = new XMLOutputter();
			out.output(doc, fw);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

		}
	}
}
