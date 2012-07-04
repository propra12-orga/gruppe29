package StandardMP.Client;

public class ExplosionThread implements Runnable {
	/**
	 * Spielfeld
	 */
	private Spielfeld sp;
	/**
	 * Bombe
	 */
	private Bomb bomb;
	/**
	 * Bomberman
	 */
	private Bomberman man;
	/**
	 * Richtung in die geprüft wird
	 */
	private int direction, i, j;

	/**
	 * 
	 * @param sp
	 * @param bomb
	 * @param man
	 * @param direction
	 * @param i
	 * @param j
	 */
	public ExplosionThread(Spielfeld sp, Bomb bomb, Bomberman man,
			int direction, int i, int j) {
		this.sp = sp;
		this.bomb = bomb;
		this.man = man;
		this.direction = direction;
		this.i = i;
		this.j = j;
	}

	/**
	 * Explosion der Bombe
	 */
	public void run() {
		switch (direction) {
		case 1: { // oben
			if ((man.bombs.get(j).getPosY() == this.bomb.getPosY() - i
					* this.bomb.getRadius())
					&& (man.bombs.get(j).getPosX() == this.bomb.getPosX())) {
				System.out.println("Bombe gefunden");
				man.bombs.get(j).tExp.schedule(
						new BombExplosion(man.bombs.get(j), sp, man), 0);
				man.bombs.get(j).tUnExp.schedule(
						new BombUnExplosion(man.bombs.get(j), man, sp), 500); // 500
																				// ist
																				// Differenz
																				// zwischen
																				// den
																				// TImern
																				// !!
			}
		}
			break;
		case 2: { // rechts
			if ((man.bombs.get(j).getPosX() == this.bomb.getPosX() + i
					* this.bomb.getRadius())
					&& (man.bombs.get(j).getPosY() == this.bomb.getPosY())) {
				System.out.println("Bombe gefunden");
				man.bombs.get(j).tExp.schedule(
						new BombExplosion(man.bombs.get(j), sp, man), 0);
				man.bombs.get(j).tUnExp.schedule(
						new BombUnExplosion(man.bombs.get(j), man, sp), 500); // 500
																				// ist
																				// Differenz
																				// zwischen
																				// den
																				// TImern
																				// !!
			}
		}
			break;
		case 3: { // unten
			if ((man.bombs.get(j).getPosY() == this.bomb.getPosY() + i
					* this.bomb.getRadius())
					&& (man.bombs.get(j).getPosX() == this.bomb.getPosX())) {
				System.out.println("Bombe gefunden");
				man.bombs.get(j).tExp.schedule(
						new BombExplosion(man.bombs.get(j), sp, man), 0);
				man.bombs.get(j).tUnExp.schedule(
						new BombUnExplosion(man.bombs.get(j), man, sp), 500); // 500
																				// ist
																				// Differenz
																				// zwischen
																				// den
																				// TImern
																				// !!
			}
		}
			break;
		case 4: { // links
			if ((man.bombs.get(j).getPosX() == this.bomb.getPosX() - i
					* this.bomb.getRadius())
					&& (man.bombs.get(j).getPosY() == this.bomb.getPosY())) {
				System.out.println("Bombe gefunden");
				man.bombs.get(j).tExp.schedule(
						new BombExplosion(man.bombs.get(j), sp, man), 0);
				man.bombs.get(j).tUnExp.schedule(
						new BombUnExplosion(man.bombs.get(j), man, sp), 500); // 500
																				// ist
																				// Differenz
																				// zwischen
																				// den
																				// TImern
																				// !!
			}
		}
		}
	}

}
