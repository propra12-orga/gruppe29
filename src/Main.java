public class Main {
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
		f = new Fenster(sizeX, sizeY, columns, length);
	}
}