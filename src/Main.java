import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Main {
	public static int[][] raster;
	private static int[][] raster2;
	private static int sizeX; // Breite des Fensters
	private static int sizeY; // Hoehe des Fensters
	private static int length; // Laenge der Bloecke
	private static int columns; // Anzahl der Reihen und Spalten
								// (gaenge+mauerreihen+rand)
	public static Fenster f;

	public static void main(String[] args) {
		columns = 13;
		length = 50;
		sizeX = columns * length;
		sizeY = columns * length;
		raster = new int[sizeX][sizeY];
		raster2 = new int[columns][columns];
		try {
			feldEinlesen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transform();
		f = new Fenster(sizeX, sizeY, columns, length);
	}

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

	private static void transform() {
		for (int k = 0; k < columns; k++)
			for (int l = 0; l < columns; l++) {
				for (int i = 0; i < length; i++)
					for (int j = 0; j < length; j++)
						raster[((k * sizeX) / columns) + i][((l * sizeX) / columns)
								+ j] = raster2[k][l];
			}
	}
}