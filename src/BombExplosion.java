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

		if (bm2 == null) {
			// Zerstoerung der Mauern + Bombermans töten
			// oben & unten#

			// oben
			for (int i = 1; i * this.bomb.getRadius() <= this.bomb
					.getExplosionRadius(); i++) {
				if (this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() - i
						* this.bomb.getRadius()] == 1)
					break;// unzerstoerbare mauer stoppt explosion
				else if (this.sp.raster[this.bomb.getPosX()][this.bomb
						.getPosY() - i * this.bomb.getRadius()] == 2) // zerstörbare
																		// mauer
																		// kapputt
																		// machen
					this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() - i
							* this.bomb.getRadius()] = 0;
				else if (this.sp.raster[this.bomb.getPosX()][this.bomb
						.getPosY() - i * this.bomb.getRadius()] == 4)
					this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() - i
							* this.bomb.getRadius()] = -1;
				else if (this.sp.raster[this.bomb.getPosX()][this.bomb
						.getPosY() - i * this.bomb.getRadius()] == 3) {
					for (int j = 0; j < this.bomb.numbOfBombs1; j++) {
						if ((this.bm.bombs.get(j).getPosY() == this.bomb
								.getPosY() - i * this.bomb.getRadius())
								&& (this.bm.bombs.get(j).getPosX() == this.bomb
										.getPosX())) {
							System.out.println("Bombe gefunden");
							this.bm.bombs.get(j).tExp.schedule(
									new BombExplosion(this.bm.bombs.get(j), sp,
											bm), 0);
							this.bm.bombs.get(j).tUnExp.schedule(
									new BombUnExplosion(this.bm.bombs.get(j),
											sp), 500); // 500 ist Differenz
														// zwischen den
														// TImern
														// !!
						}
					}
				}
				if ((this.bm.getPosX() == this.bomb.getPosX())
						&& (this.bm.getPosY() == this.bomb.getPosY() - i
								* this.bomb.getRadius())) {
					this.sp.repaint();
					Main.f.dispose(
							"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
							true);

					Main.f.restart(1);
				}
			}
			// unten
			for (int i = 1; i * this.bomb.getRadius() <= this.bomb
					.getExplosionRadius(); i++) {
				if (this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() + i
						* this.bomb.getRadius()] == 1)
					break;// unzerstoerbare mauer stoppt explosion
				else if (this.sp.raster[this.bomb.getPosX()][this.bomb
						.getPosY() + i * this.bomb.getRadius()] == 2) // zerstörbare
																		// mauer
																		// kapputt
																		// machen
					this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() + i
							* this.bomb.getRadius()] = 0;
				else if (this.sp.raster[this.bomb.getPosX()][this.bomb
						.getPosY() + i * this.bomb.getRadius()] == 4)
					this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() + i
							* this.bomb.getRadius()] = -1;
				else if (this.sp.raster[this.bomb.getPosX()][this.bomb
						.getPosY() + i * this.bomb.getRadius()] == 3) {
					for (int j = 0; j < this.bomb.numbOfBombs1; j++) {
						if ((this.bm.bombs.get(j).getPosY() == this.bomb
								.getPosY() + i * this.bomb.getRadius())
								&& (this.bm.bombs.get(j).getPosX() == this.bomb
										.getPosX())) {
							System.out.println("Bombe gefunden");
							this.bm.bombs.get(j).tExp.schedule(
									new BombExplosion(this.bm.bombs.get(j), sp,
											bm), 0);
							this.bm.bombs.get(j).tUnExp.schedule(
									new BombUnExplosion(this.bm.bombs.get(j),
											sp), 500); // 500 ist Differenz
														// zwischen den
														// TImern
														// !!
						}
					}

				}
				if ((this.bm.getPosX() == this.bomb.getPosX())
						&& (this.bm.getPosY() == this.bomb.getPosY() + i
								* this.bomb.getRadius())) {
					this.sp.repaint();
					Main.f.dispose(
							"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
							true);

					Main.f.restart(1);
				}
			}

			// links&rechts

			// links
			for (int i = 1; i * this.bomb.getRadius() <= this.bomb
					.getExplosionRadius(); i++) {
				if (this.sp.raster[this.bomb.getPosX() - i
						* this.bomb.getRadius()][this.bomb.getPosY()] == 1)
					break;// unzerstoerbare mauer stoppt explosion
				else if (this.sp.raster[this.bomb.getPosX() - i
						* this.bomb.getRadius()][this.bomb.getPosY()] == 2) // zerstörbare
																			// mauer
					// kapputt machen
					this.sp.raster[this.bomb.getPosX() - i
							* this.bomb.getRadius()][this.bomb.getPosY()] = 0;
				else if (this.sp.raster[this.bomb.getPosX() - i
						* this.bomb.getRadius()][this.bomb.getPosY()] == 4)
					this.sp.raster[this.bomb.getPosX() - i
							* this.bomb.getRadius()][this.bomb.getPosY()] = -1;
				else if (this.sp.raster[this.bomb.getPosX() - i
						* this.bomb.getRadius()][this.bomb.getPosY()] == 3) {
					for (int j = 0; j < this.bomb.numbOfBombs1; j++) {
						if ((this.bm.bombs.get(j).getPosX() == this.bomb
								.getPosX() - i * this.bomb.getRadius())
								&& (this.bm.bombs.get(j).getPosY() == this.bomb
										.getPosY())) {
							System.out.println("Bombe gefunden");
							this.bm.bombs.get(j).tExp.schedule(
									new BombExplosion(this.bm.bombs.get(j), sp,
											bm), 0);
							this.bm.bombs.get(j).tUnExp.schedule(
									new BombUnExplosion(this.bm.bombs.get(j),
											sp), 500); // 500 ist Differenz
														// zwischen den
														// TImern
														// !!
						}
					}
				}
				if ((this.bm.getPosY() == this.bomb.getPosY())
						&& (this.bm.getPosX() == this.bomb.getPosX() - i
								* this.bomb.getRadius())) {
					this.sp.repaint();
					Main.f.dispose(
							"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
							true);

					Main.f.restart(1);
				}
			}
			// rechts
			for (int i = 1; i * this.bomb.getRadius() <= this.bomb
					.getExplosionRadius(); i++) {
				if (this.sp.raster[this.bomb.getPosX() + i
						* this.bomb.getRadius()][this.bomb.getPosY()] == 1)
					break;// unzerstoerbare mauer stoppt explosion
				else if (this.sp.raster[this.bomb.getPosX() + i
						* this.bomb.getRadius()][this.bomb.getPosY()] == 2) // zerstörbare
																			// mauer
					// kapputt machen
					this.sp.raster[this.bomb.getPosX() + i
							* this.bomb.getRadius()][this.bomb.getPosY()] = 0;
				else if (this.sp.raster[this.bomb.getPosX() + i
						* this.bomb.getRadius()][this.bomb.getPosY()] == 4)
					this.sp.raster[this.bomb.getPosX() + i
							* this.bomb.getRadius()][this.bomb.getPosY()] = -1;
				else if (this.sp.raster[this.bomb.getPosX() + i
						* this.bomb.getRadius()][this.bomb.getPosY()] == 3) {
					for (int j = 0; j < this.bomb.numbOfBombs1; j++) {
						if ((this.bm.bombs.get(j).getPosX() == this.bomb
								.getPosX() + i * this.bomb.getRadius())
								&& (this.bm.bombs.get(j).getPosY() == this.bomb
										.getPosY())) {
							System.out.println("Bombe gefunden");
							this.bm.bombs.get(j).tExp.schedule(
									new BombExplosion(this.bm.bombs.get(j), sp,
											bm), 0);
							this.bm.bombs.get(j).tUnExp.schedule(
									new BombUnExplosion(this.bm.bombs.get(j),
											sp), 500); // 500 ist Differenz
														// zwischen den
														// TImern
														// !!
						}
					}
				}
				if ((this.bm.getPosY() == this.bomb.getPosY())
						&& (this.bm.getPosX() == this.bomb.getPosX() + i
								* this.bomb.getRadius())) {
					this.sp.repaint();
					Main.f.dispose(
							"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
							true);

					Main.f.restart(1);
				}
			}
		} else { // ---------------------2 Bomberman---------------------
			// Zerstoerung der Mauern + Bombermans töten
			// oben & unten#

			// oben
			for (int i = 1; i * this.bomb.getRadius() <= this.bomb
					.getExplosionRadius(); i++) {
				if (this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() - i
						* this.bomb.getRadius()] == 1)
					break;// unzerstoerbare mauer stoppt explosion
				else if (this.sp.raster[this.bomb.getPosX()][this.bomb
						.getPosY() - i * this.bomb.getRadius()] == 2) // zerstörbare
																		// mauer
																		// kapputt
																		// machen
					this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() - i
							* this.bomb.getRadius()] = 0;
				else if (this.sp.raster[this.bomb.getPosX()][this.bomb
						.getPosY() - i * this.bomb.getRadius()] == 4)
					this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() - i
							* this.bomb.getRadius()] = -1;
				else if (this.sp.raster[this.bomb.getPosX()][this.bomb
						.getPosY() - i * this.bomb.getRadius()] == 3) {
					for (int j = 0; j < this.bomb.numbOfBombs1; j++) {
						if (((this.bm.bombs.get(j).getPosY() == this.bomb
								.getPosY() - i * this.bomb.getRadius()) && (this.bm.bombs
								.get(j).getPosX() == this.bomb.getPosX()))) {
							System.out.println("Bombe gefunden");
							this.bm.bombs.get(j).tExp.schedule(
									new BombExplosion(this.bm.bombs.get(j), sp,
											bm), 0);
							this.bm.bombs.get(j).tUnExp.schedule(
									new BombUnExplosion(this.bm.bombs.get(j),
											sp), 500); // 500 ist
														// Differenz
														// zwischen
														// den
														// TImern
														// !!
						}
					}
					for (int j = 0; j < this.bomb.numbOfBombs2; j++) {
						if (((this.bm2.bombs.get(j).getPosY() == this.bomb
								.getPosY() - i * this.bomb.getRadius()) && (this.bm2.bombs
								.get(j).getPosX() == this.bomb.getPosX()))) {
							System.out.println("Bombe gefunden");
							this.bm2.bombs.get(j).tExp.schedule(
									new BombExplosion(this.bm2.bombs.get(j),
											sp, bm2), 0);
							this.bm2.bombs.get(j).tUnExp.schedule(
									new BombUnExplosion(this.bm2.bombs.get(j),
											sp), 500); // 500 ist
							// Differenz
							// zwischen
							// den
							// TImern
							// !!
						}
					}
				}
				if ((this.bm.getPosX() == this.bomb.getPosX())
						&& (this.bm.getPosY() == this.bomb.getPosY() - i
								* this.bomb.getRadius())) {
					this.sp.repaint();
					Main.f.dispose(
							"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
							true);

					Main.f.restart(2);
				}
				if ((this.bm2.getPosX() == this.bomb.getPosX())
						&& (this.bm2.getPosY() == this.bomb.getPosY() - i
								* this.bomb.getRadius())) {
					this.sp.repaint();
					Main.f.dispose(
							"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
							true);

					Main.f.restart(2);
				}
			}
			// unten
			for (int i = 1; i * this.bomb.getRadius() <= this.bomb
					.getExplosionRadius(); i++) {
				if (this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() + i
						* this.bomb.getRadius()] == 1)
					break;// unzerstoerbare mauer stoppt explosion
				else if (this.sp.raster[this.bomb.getPosX()][this.bomb
						.getPosY() + i * this.bomb.getRadius()] == 2) // zerstörbare
																		// mauer
																		// kapputt
																		// machen
					this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() + i
							* this.bomb.getRadius()] = 0;
				else if (this.sp.raster[this.bomb.getPosX()][this.bomb
						.getPosY() + i * this.bomb.getRadius()] == 4)
					this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() + i
							* this.bomb.getRadius()] = -1;
				else if (this.sp.raster[this.bomb.getPosX()][this.bomb
						.getPosY() + i * this.bomb.getRadius()] == 3) {
					for (int j = 0; j < this.bomb.numbOfBombs1; j++) {
						if (((this.bm.bombs.get(j).getPosY() == this.bomb
								.getPosY() + i * this.bomb.getRadius()) && (this.bm.bombs
								.get(j).getPosX() == this.bomb.getPosX()))) {
							System.out.println("Bombe gefunden");
							this.bm.bombs.get(j).tExp.schedule(
									new BombExplosion(this.bm.bombs.get(j), sp,
											bm), 0);
							this.bm.bombs.get(j).tUnExp.schedule(
									new BombUnExplosion(this.bm.bombs.get(j),
											sp), 500); // 500 ist
														// Differenz
														// zwischen
														// den
														// TImern
														// !!
						}
					}
					for (int j = 0; j < this.bomb.numbOfBombs2; j++) {
						if (((this.bm2.bombs.get(j).getPosY() == this.bomb
								.getPosY() + i * this.bomb.getRadius()) && (this.bm2.bombs
								.get(j).getPosX() == this.bomb.getPosX()))) {
							System.out.println("Bombe gefunden");
							this.bm2.bombs.get(j).tExp.schedule(
									new BombExplosion(this.bm2.bombs.get(j),
											sp, bm2), 0);
							this.bm2.bombs.get(j).tUnExp.schedule(
									new BombUnExplosion(this.bm2.bombs.get(j),
											sp), 500); // 500 ist
							// Differenz
							// zwischen
							// den
							// TImern
							// !!
						}
					}
				}
				if ((this.bm.getPosX() == this.bomb.getPosX())
						&& (this.bm.getPosY() == this.bomb.getPosY() + i
								* this.bomb.getRadius())) {
					this.sp.repaint();
					Main.f.dispose(
							"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
							true);

					Main.f.restart(2);
				}
				if ((this.bm2.getPosX() == this.bomb.getPosX())
						&& (this.bm2.getPosY() == this.bomb.getPosY() + i
								* this.bomb.getRadius())) {
					this.sp.repaint();
					Main.f.dispose(
							"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
							true);

					Main.f.restart(2);
				}
			}

			// links&rechts

			// links
			for (int i = 1; i * this.bomb.getRadius() <= this.bomb
					.getExplosionRadius(); i++) {
				if (this.sp.raster[this.bomb.getPosX() - i
						* this.bomb.getRadius()][this.bomb.getPosY()] == 1)
					break;// unzerstoerbare mauer stoppt explosion
				else if (this.sp.raster[this.bomb.getPosX() - i
						* this.bomb.getRadius()][this.bomb.getPosY()] == 2) // zerstörbare
					// mauer
					// kapputt
					// machen
					this.sp.raster[this.bomb.getPosX() - i
							* this.bomb.getRadius()][this.bomb.getPosY()] = 0;
				else if (this.sp.raster[this.bomb.getPosX() - i
						* this.bomb.getRadius()][this.bomb.getPosY()] == 4)
					this.sp.raster[this.bomb.getPosX() - i
							* this.bomb.getRadius()][this.bomb.getPosY()] = -1;
				else if (this.sp.raster[this.bomb.getPosX() - i
						* this.bomb.getRadius()][this.bomb.getPosY()] == 3) {
					for (int j = 0; j < this.bomb.numbOfBombs1; j++) {
						if (((this.bm.bombs.get(j).getPosY() == this.bomb
								.getPosY()) && (this.bm.bombs.get(j).getPosX() == this.bomb
								.getPosX() - i * this.bomb.getRadius()))) {
							System.out.println("Bombe gefunden");
							this.bm.bombs.get(j).tExp.schedule(
									new BombExplosion(this.bm.bombs.get(j), sp,
											bm), 0);
							this.bm.bombs.get(j).tUnExp.schedule(
									new BombUnExplosion(this.bm.bombs.get(j),
											sp), 500); // 500 ist
														// Differenz
														// zwischen
														// den
														// TImern
														// !!
						}
					}
					for (int j = 0; j < this.bomb.numbOfBombs2; j++) {
						if (((this.bm2.bombs.get(j).getPosY() == this.bomb
								.getPosY()) && (this.bm2.bombs.get(j).getPosX() == this.bomb
								.getPosX() - i * this.bomb.getRadius()))) {
							System.out.println("Bombe gefunden");
							this.bm2.bombs.get(j).tExp.schedule(
									new BombExplosion(this.bm2.bombs.get(j),
											sp, bm2), 0);
							this.bm2.bombs.get(j).tUnExp.schedule(
									new BombUnExplosion(this.bm2.bombs.get(j),
											sp), 500); // 500 ist
							// Differenz
							// zwischen
							// den
							// TImern
							// !!
						}
					}
				}
				if ((this.bm.getPosX() == this.bomb.getPosX() - i
						* this.bomb.getRadius())
						&& (this.bm.getPosY() == this.bomb.getPosY())) {
					this.sp.repaint();
					Main.f.dispose(
							"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
							true);

					Main.f.restart(2);
				}
				if ((this.bm2.getPosX() == this.bomb.getPosX() - i
						* this.bomb.getRadius())
						&& (this.bm2.getPosY() == this.bomb.getPosY())) {
					this.sp.repaint();
					Main.f.dispose(
							"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
							true);

					Main.f.restart(2);
				}
			}

			// rechts
			for (int i = 1; i * this.bomb.getRadius() <= this.bomb
					.getExplosionRadius(); i++) {
				if (this.sp.raster[this.bomb.getPosX() + i
						* this.bomb.getRadius()][this.bomb.getPosY()] == 1)
					break;// unzerstoerbare mauer stoppt explosion
				else if (this.sp.raster[this.bomb.getPosX() + i
						* this.bomb.getRadius()][this.bomb.getPosY()] == 2) // zerstörbare
					// mauer
					// kapputt
					// machen
					this.sp.raster[this.bomb.getPosX() + i
							* this.bomb.getRadius()][this.bomb.getPosY()] = 0;
				else if (this.sp.raster[this.bomb.getPosX() + i
						* this.bomb.getRadius()][this.bomb.getPosY()] == 4)
					this.sp.raster[this.bomb.getPosX() + i
							* this.bomb.getRadius()][this.bomb.getPosY()] = -1;
				else if (this.sp.raster[this.bomb.getPosX() + i
						* this.bomb.getRadius()][this.bomb.getPosY()] == 3) {
					for (int j = 0; j < this.bomb.numbOfBombs1; j++) {
						if (((this.bm.bombs.get(j).getPosY() == this.bomb
								.getPosY()) && (this.bm.bombs.get(j).getPosX() == this.bomb
								.getPosX() + i * this.bomb.getRadius()))) {
							System.out.println("Bombe gefunden");
							this.bm.bombs.get(j).tExp.schedule(
									new BombExplosion(this.bm.bombs.get(j), sp,
											bm), 0);
							this.bm.bombs.get(j).tUnExp.schedule(
									new BombUnExplosion(this.bm.bombs.get(j),
											sp), 500); // 500 ist
														// Differenz
														// zwischen
														// den
														// TImern
														// !!
						}
					}
					for (int j = 0; j < this.bomb.numbOfBombs2; j++) {
						if (((this.bm2.bombs.get(j).getPosY() == this.bomb
								.getPosY()) && (this.bm2.bombs.get(j).getPosX() == this.bomb
								.getPosX() + i * this.bomb.getRadius()))) {
							System.out.println("Bombe gefunden");
							this.bm2.bombs.get(j).tExp.schedule(
									new BombExplosion(this.bm2.bombs.get(j),
											sp, bm2), 0);
							this.bm2.bombs.get(j).tUnExp.schedule(
									new BombUnExplosion(this.bm2.bombs.get(j),
											sp), 500); // 500 ist
							// Differenz
							// zwischen
							// den
							// TImern
							// !!
						}
					}
				}
				if ((this.bm.getPosX() == this.bomb.getPosX() + i
						* this.bomb.getRadius())
						&& (this.bm.getPosY() == this.bomb.getPosY())) {
					this.sp.repaint();
					Main.f.dispose(
							"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
							true);

					Main.f.restart(2);
				}
				if ((this.bm2.getPosX() == this.bomb.getPosX() + i
						* this.bomb.getRadius())
						&& (this.bm2.getPosY() == this.bomb.getPosY())) {
					this.sp.repaint();
					Main.f.dispose(
							"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
							true);

					Main.f.restart(2);
				}
			}
		}

		this.sp.repaint();
	}
}