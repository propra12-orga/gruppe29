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
			// (this.bm.getPosX() + this.bm.getSteps() - 1 ==
			// this.bomb.getPosX())
			// || ((this.bm.getPosX() - this.bm.getSteps() + 1 == this.bomb
			// .getPosX()))
			// || (this.bm.getPosY() + this.bm.getSteps() - 1 == this.bomb
			// .getPosY())
			// || (this.bm.getPosY() - this.bm.getSteps() + 1 == this.bomb
			// .getPosY())
			// || ((this.bm.getPosX() == this.bomb.getPosX()) && (this.bm
			// .getPosY() == this.bomb.getPosY())))
			Main.f.dispose(
					"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
					true);
			if (this.bm2 != null)
				Main.f.restart(2);
			else
				Main.f.restart(1);
		}
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
				// (this.bm2.getPosX() + this.bm2.getSteps() - 1 ==
				// this.bomb.getPosX())
				// || ((this.bm2.getPosX() - this.bm2.getSteps() + 1 ==
				// this.bomb
				// .getPosX()))
				// || (this.bm2.getPosY() + this.bm2.getSteps() - 1 == this.bomb
				// .getPosY())
				// || (this.bm2.getPosY() - this.bm2.getSteps() + 1 == this.bomb
				// .getPosY())
				// || ((this.bm2.getPosX() == this.bomb.getPosX()) && (this.bm2
				// .getPosY() == this.bomb.getPosY())))
				Main.f.dispose("Dein Gegenspieler hat dich erledigt!", true);
				Main.f.restart(2);
			}
		}
		// Zerstörung der Mauern
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
				break; // unzerstörbare mauer stoppt explosion
			} else if (this.sp.raster[this.bomb.getPosX()][i] == 2) {
				// zerstörbare mauer
				swap = i;
				System.out.println("Hallo");

			} // wird zerstört
			else if (this.sp.raster[this.bomb.getPosX()][i] == 3) {
				// andere bombe muss explodieren
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
				break; // unzerstörbare mauer stoppt explosion
			} else if (this.sp.raster[i][this.bomb.getPosY()] == 2) {
				// zerstörbare mauer
				swap = i;
				System.out.println("Hallo");

			} // wird zerstört
			else if (this.sp.raster[i][this.bomb.getPosY()] == 3) {
				// andere bombe muss explodieren
			}
		}
		if (!flag && swap >= 0)
			for (int k = 0; k < this.sp.blockLength; k++)
				for (int l = 0; l < this.sp.blockLength; l++)
					this.sp.raster[swap + k][this.bomb.getPosY() + l] = 0;

		this.sp.repaint();

		// if (this.sp.raster[this.bomb.getPosX() -
		// this.bomb.getRadius()][this.bomb
		// .getPosY()] == 2) {
		// int x = this.bomb.getPosX() - this.bomb.getRadius();
		// int y = this.bomb.getPosY();
		// while (this.sp.raster[(x - 1)][this.bomb.getPosY()] == 2)
		// x -= 1;
		// while (this.sp.raster[this.bomb.getPosX() - this.bomb.getRadius()][y
		// - 1] == 2)
		// y -= 1;
		// for (int i = 0; i < this.sp.blockLength; i++)
		// for (int j = 0; i < this.sp.blockLength; j++)
		// this.sp.raster[x + i][y + j] = 0;
		// }
		// // nach rechts
		// else if (this.sp.raster[this.bomb.getPosX() +
		// this.bomb.getRadius()][this.bomb
		// .getPosY()] == 2) {
		// int x = this.bomb.getPosX() + this.bomb.getRadius();
		// int y = this.bomb.getPosY();
		// while (this.sp.raster[(x - 1)][this.bomb.getPosY()] == 2)
		// x -= 1;
		// while (this.sp.raster[this.bomb.getPosX() + this.bomb.getRadius()][y
		// - 1] == 2)
		// y -= 1;
		// for (int i = 0; i < this.sp.blockLength; i++)
		// for (int j = 0; i < this.sp.blockLength; j++)
		// this.sp.raster[x + i][y + j] = 0;
		// }
		// // nach oben
		// else if (this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY()
		// - this.bomb.getRadius()] == 2) {
		// int x = this.bomb.getPosX();
		// int y = this.bomb.getPosY() - this.bomb.getRadius();
		// while (this.sp.raster[(x - 1)][this.bomb.getPosY()
		// - this.bomb.getRadius()] == 2)
		// x -= 1;
		// while (this.sp.raster[this.bomb.getPosX()][y - 1] == 2)
		// y -= 1;
		// for (int i = 0; i < this.sp.blockLength; i++)
		// for (int j = 0; i < this.sp.blockLength; j++)
		// this.sp.raster[x + i][y + j] = 0;
		// }
		// // nach unten
		// else if (this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY()
		// + this.bomb.getRadius()] == 2) {
		// int x = this.bomb.getPosX();
		// int y = this.bomb.getPosY() + this.bomb.getRadius();
		// while (this.sp.raster[(x - 1)][this.bomb.getPosY()
		// + this.bomb.getRadius()] == 2)
		// x -= 1;
		// while (this.sp.raster[this.bomb.getPosX()][y - 1] == 2)
		// y -= 1;
		// for (int i = 0; i < this.sp.blockLength; i++)
		// for (int j = 0; i < this.sp.blockLength; j++)
		// this.sp.raster[x + i][y + j] = 0;
		// }
	}
}