import java.util.TimerTask;

public class BombUnExplosion extends TimerTask {
	private Bomberman bm;
	/** Bomb-Objekt */
	private Bomb bomb;
	/** Spielfeld-Referenz */
	private Spielfeld sp;

	/**
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
	 * Bombenexplosion ausschalten, Bombe unsichtbar setzen, Raster auf begehbar schalten
	 */
	public void run() {
		// try {
		// Thread.sleep(3000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

		bomb.setExploded(false);
		bomb.setVisible(false);
		sp.raster[bomb.getPosX()][bomb.getPosY()] = 0;

		this.bm.bombs.remove(bomb);
		this.bm.decBombs();

		sp.repaint();
	}
}
