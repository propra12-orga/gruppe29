import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;

import parser.RasterParser;
import parser.XMLParser;

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

	private static Document doc;
	private static Element root;

	/**
	 * main-Methode des Spiels, hier fängt Alles an. Raster werden eingelsen und
	 * erzeugt, anschließend damit das Fenster erstellt.
	 * 
	 * @exception IOException
	 *                , JDOMException
	 */
	public static void main(String[] args) {
		XMLParser xml = new XMLParser("Bomberman.xml");
		RasterParser rp = new RasterParser();
		columns = xml.getColumns();
		length = 50;
		sizeX = sizeY = columns * length;
		raster2 = xml.readXML(columns);
		raster = rp.transform(raster2, columns, length);
		f = new Fenster(sizeX, sizeY, columns, length);

	}

}