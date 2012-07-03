package parser;

public class RasterParser {

	/**
	 * Methode zum erstellen des Pixelrasters aus einem Blockraster. (Jede Zelle
	 * aus dem Blockraster wird zu einem Pixelblock im Pixelraster ausgeweitet).
	 */
	public int[][] transform(int[][] raster2, int columns, int length) {
		int sizeX = columns * length;
		int sizeY = columns * length;
		int[][] raster = new int[sizeX][sizeY];
		for (int k = 0; k < columns; k++)
			for (int l = 0; l < columns; l++) {
				for (int i = 0; i < length; i++)
					for (int j = 0; j < length; j++)
						raster[((k * sizeX) / columns) + i][((l * sizeY) / columns)
								+ j] = raster2[k][l];
			}
		return raster;
	}

	/**
	 * Wandelt ein Pixelraster wieder in ein Blockraster um
	 * 
	 * @param raster
	 * @param columns
	 * @param length
	 * @return
	 */
	public int[][] untransform(int[][] raster, int columns, int length) {
		int[][] raster2 = new int[columns][columns];
		int sizeX = columns * length;
		int sizeY = columns * length;
		for (int k = 0; k < sizeX; k = k + length)
			for (int l = 0; l < sizeY; l = l + length)
				raster2[(k * columns) / sizeX][(l * columns) / sizeY] = raster[k][l];
		return raster2;
	}
}
