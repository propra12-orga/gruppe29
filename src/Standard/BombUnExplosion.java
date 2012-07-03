package Standard;

import java.util.TimerTask;

public class BombUnExplosion extends TimerTask {
	/** Bomberman */
	private Bomberman bm;
	/** Bomb-Objekt */
	private Bomb bomb;
	/** Spielfeld-Referenz */
	private Spielfeld sp;

	/**
	 * Erzeugt eine neues Objekt von BombUnExplosion.
	 * 
	 * @param b
	 *            Übergabe von zu behandelnder Bombe
	 * @param sp
	 *            Übergabe des Spielfelds
	 */
	public BombUnExplosion(Bomb b, Bomberman bm, Spielfeld sp) {
		this.bm = bm;
		this.bomb = b;
		this.sp = sp;
	}

	@Override
	/**
	 * Bombenexplosion ausschalten, Bombe unsichtbar setzen, Raster auf begehbar schalten und schließlcih die Bombe aus der Liste löschen und den entsprechenden Zähler dekrementieren.
	 */
	public void run() {
		bomb.setExploded(false);
		bomb.setVisible(false);
		sp.raster[bomb.getPosX()][bomb.getPosY()] = 0;

		if (bm.bombs.contains(bomb)) {
			this.bomb.getOwner().decBombs();
			this.bm.bombs.remove(bomb);
		}
		sp.repaint();
	}
}