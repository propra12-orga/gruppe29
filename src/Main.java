public class Main {
	private static int sizeX; // Breite des Fensters
	private static int sizeY; // Hoehe des Fesnters

	public static void main(String[] args) {
		sizeX = 640;
		sizeY = 480;
		new Fenster(sizeX, sizeY); // Fenster erstellen
	}
}