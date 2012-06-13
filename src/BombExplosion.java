import java.util.TimerTask;

public class BombExplosion extends TimerTask {
	/** Bombe */
	private Bomb bomb;
	/** Spielfeld */
	private Spielfeld sp;
	/** Bombermänner */
	Bomberman bm, bm2;

	/**
	 * 
	 * @param b
	 * @param sp
	 * @param man
	 */
	public BombExplosion(Bomb b, Spielfeld sp, Bomberman man) {
		this.bomb = b;
		this.sp = sp;
		this.bm = man;
	}

	/**
	 * 
	 * @param b
	 * @param sp
	 * @param man
	 * @param man2
	 */
	public BombExplosion(Bomb b, Spielfeld sp, Bomberman man, Bomberman man2) {
		this.bomb = b;
		this.sp = sp;
		this.bm = man;
		this.bm2 = man2;
	}

	/**
	 * Bombe explodieren lassen
	 */
	public void run() {
		bomb.setExploded(true);
		System.out.println("Bombe gezündet!");
		// Bomberman 1 pruefen
		if ((this.bm.getPosX() >= (this.bomb.getPosX() - this.bomb
				.getExplosionRadius()))
				&& (this.bm.getPosX() <= (this.bomb.getPosX() + this.bomb
						.getExplosionRadius()) && (this.bm.getPosY() == this.bomb
						.getPosY()))
				|| ((this.bm.getPosY() >= (this.bomb.getPosY() - this.bomb
						.getExplosionRadius()))
						&& (this.bm.getPosY() <= (this.bomb.getPosY() + this.bomb
								.getExplosionRadius())) && (this.bm.getPosX() == this.bomb
						.getPosX()))) {
			this.sp.repaint();
			Main.f.dispose(
					"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
					true);
			if (this.bm2 != null)
				Main.f.restart(2);
			else
				Main.f.restart(1);
		}
		// Bomberman 2 pruefen
		if (this.bm2 != null) {
			if ((this.bm2.getPosX() >= (this.bomb.getPosX() - this.bomb
					.getExplosionRadius()))
					&& (this.bm2.getPosX() <= (this.bomb.getPosX() + this.bomb
							.getExplosionRadius()) && (this.bm2.getPosY() == this.bomb
							.getPosY()))
					|| ((this.bm2.getPosY() >= (this.bomb.getPosY() - this.bomb
							.getExplosionRadius()))
							&& (this.bm2.getPosY() <= (this.bomb.getPosY() + this.bomb
									.getExplosionRadius())) && (this.bm2
							.getPosX() == this.bomb.getPosX()))) {
				this.sp.repaint();
				Main.f.dispose("Dein Gegenspieler hat dich erledigt!", true);
				Main.f.restart(2);
			}
		}

		// Zerstoerung der Mauern
		// oben&unten
		boolean flag = false;
		int swap = -1;
		for (int i = (this.bomb.getPosY() - this.bomb.getExplosionRadius()); i < (this.bomb
				.getPosY() + this.bomb.getExplosionRadius()); i += this.bomb
				.getRadius()) {
			if (i < 0)
				i += this.bomb.getRadius();
			else if (i > this.sp.getBreite())
				break;

			if (this.sp.raster[this.bomb.getPosX()][i] == 1) {
				flag = true;
				break; // unzerstoerbare mauer stoppt explosion
			} else if (this.sp.raster[this.bomb.getPosX()][i] == 2) {
				// zerstoerbare mauer
				swap = i;
				System.out.println("Hallo");

			} // wird zerstoert
			else if (this.sp.raster[this.bomb.getPosX()][i] == 3) {
				// andere bombe muss explodieren
			} else if (this.sp.raster[this.bomb.getPosX()][i] == 4) {
				this.sp.raster[this.bomb.getPosX()][i] = -1;
			}
		}
		if (!flag && swap >= 0)
			for (int k = 0; k < this.sp.blockLength; k++)
				for (int l = 0; l < this.sp.blockLength; l++)
					this.sp.raster[this.bomb.getPosX() + k][swap + l] = 0;

		// links&rechts
		flag = false;
		swap = -1;
		for (int i = (this.bomb.getPosX() - this.bomb.getExplosionRadius()); i < (this.bomb
				.getPosX() + this.bomb.getExplosionRadius()); i += this.bomb
				.getRadius()) {
			if (i < 0)
				i += this.bomb.getRadius();
			else if (i > this.sp.getBreite())
				break;

			if (this.sp.raster[i][this.bomb.getPosY()] == 1) {
				flag = true;
				break; // unzerstoerbare mauer stoppt explosion
			} else if (this.sp.raster[i][this.bomb.getPosY()] == 2) {
				// zerstoerbare mauer
				swap = i;
				System.out.println("Hallo");

			} // wird zerstoert
			else if (this.sp.raster[i][this.bomb.getPosY()] == 3) {
				// andere bombe muss explodieren
			} else if (this.sp.raster[i][this.bomb.getPosY()] == 3) {
				this.sp.raster[i][this.bomb.getPosY()] = -1;
			}
		}
		if (!flag && swap >= 0)
			for (int k = 0; k < this.sp.blockLength; k++)
				for (int l = 0; l < this.sp.blockLength; l++)
					this.sp.raster[swap + k][this.bomb.getPosY() + l] = 0;

		this.sp.repaint();
	}
}