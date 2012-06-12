import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Main {
	/** Pixelraster */
	public static int[][] raster;
	/** Blockraster */
	private static int[][] raster2;
	/** sizeX, sizeY Breite und Höhe des Fensters */
	private static int sizeX;
	private static int sizeY;
	/** Länge der Blöcke */
	private static int length;
	/** Anzahl der Reihen und Spalten (gaenge+mauerreihen+rand) */
	private static int columns;
	/** Fenster für das Spiel */
	public static Fenster f;

	/**
	 * main-Methode des Spiels, hier fängt Alles an. Raster werden eingelsen und
	 * erzeugt, anschließend damit das Fenster erstellt.
	 * 
	 * @exception IOException
	 *                , JDOMException
	 */
	public static void main(String[] args) {
		columns = 9;
		length = 50;
		sizeX = sizeY = columns * length;
		raster = new int[sizeX][sizeY];
		raster2 = new int[columns][columns];
		try {
			feldEinlesen();
		} catch (Exception e) {
			e.printStackTrace();
		}
		transform();
		f = new Fenster(sizeX, sizeY, columns, length);
	}

	/**
	 * Methode zum Einlesen des Spielfelds aus der XML-Datei "Bomberman.xml". Es
	 * wird ein blockraster erstellt.
	 * 
	 * @throws IOException
	 *             , JDOMException
	 */
	private static void feldEinlesen() throws Exception {
		String filename = "Bomberman.xml";
		Document doc = new SAXBuilder().build(filename);
		Element root = doc.getRootElement();
		for (int i = 0; i < columns; i++)
			for (int j = 0; j < columns; j++) {
				Element col = root.getChild("Column" + i);
				Element row = col.getChild("Row" + j);
				raster2[i][j] = Integer.parseInt(row.getValue());
			}
	}

	/**
	 * Methode zum erstellen des Pixelrasters aus einem Blockraster. (Jede Zelle
	 * aus dem Blockraster wird zu einem Pixelblock im Pixelraster ausgeweitet).
	 */
	private static void transform() {
		for (int k = 0; k < columns; k++)
			for (int l = 0; l < columns; l++) {
				for (int i = 0; i < length; i++)
					for (int j = 0; j < length; j++)
						raster[((k * sizeX) / columns) + i][((l * sizeY) / columns)
								+ j] = raster2[k][l];
			}
	}
}