package Standard;

import java.util.TimerTask;

import Spielobjekte.PowerUp;

public class BombExplosion extends TimerTask {
	/** Bombe */
	private Bomb bomb;
	/** Spielfeld */
	private Spielfeld sp;
	/** Bombermänner */
	Bomberman bm, bm2;

	private double pExpRadp, pBombep;

	/**
	 * Erzeugt ein neues Objekt von BombExplosion.
	 * 
	 * @param b
	 *            explodierte Bombe
	 * @param sp
	 *            Referenz auf Spielfeld
	 * @param man
	 *            Referenz auf Bomberman
	 */
	public BombExplosion(Bomb b, Spielfeld sp, Bomberman man) {
		this.bomb = b;
		this.sp = sp;
		this.bm = man;
		this.pExpRadp = 0.2;
		this.pBombep = 0.8;
	}

	/**
	 * Erzeugt ein neues Objekt von BombExplosion, falls 2 Spieler existieren.
	 * 
	 * @param b
	 *            explodierte Bombe
	 * @param sp
	 *            Referenz auf Spielfeld
	 * @param man
	 *            Referenz auf Bomberman
	 * @param man2
	 *            Referenz auf den zweiten Bomberman
	 */
	public BombExplosion(Bomb b, Spielfeld sp, Bomberman man, Bomberman man2) {
		this(b, sp, man);
		this.bm2 = man2;
	}

	/**
	 * Exoplsion ausführen. Es wird von der Position der Bombe aus in alle
	 * Richtungen geprüft, ob sich innerhalb des Explosionsradius ein Objekt zu
	 * Interaktion befindet.
	 */
	public void run() {
		bomb.setExploded(true);
		this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY()] = 0;

		// Zerstoerung der Mauern + Bombermans töten
		// oben & unten#

		// oben
		for (int i = 1; i * this.bomb.getRadius() <= this.bomb
				.getExplosionRadius(); i++) {
			switch (this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() - i
					* this.bomb.getRadius()]) {
			case -2: { // PowerUp zerstören
				this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() - i
						* this.bomb.getRadius()] = 0;
				this.sp.decNumbOfPowerUps();
				for (int j = 0; j < this.sp.powerups.size(); j++)
					if ((this.bomb.getPosX() == this.sp.powerups.get(j)
							.getPosX())
							&& (this.bomb.getPosY() - i * this.bomb.getRadius() == this.sp.powerups
									.get(j).getPosY())) {
						this.sp.powerups.remove(j);
						break;
					}
			}
				break;
			case 1: { // unzerstoerbare mauer stoppt explosion
				i = 100;
			}
				break;
			case 2: { // zerstörbare mauer kapputt machen
				bomb.getOwner().addScore(1);
				double n = Math.random();
				if (n < 0.2) {
					this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() - i
							* this.bomb.getRadius()] = -2;
					PowerUp powerup;
					n = Math.random();
					if (n <= pExpRadp)
						powerup = new PowerUp(
								this.bomb.getPosX(),
								this.bomb.getPosY() - i * this.bomb.getRadius(),
								this.bomb.getRadius(), this.sp, "expRadp");
					else
						powerup = new PowerUp(
								this.bomb.getPosX(),
								this.bomb.getPosY() - i * this.bomb.getRadius(),
								this.bomb.getRadius(), this.sp, "bombep");
					this.sp.incNumbOfPowerUps();
					this.sp.powerups.add(powerup);
				} else
					this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() - i
							* this.bomb.getRadius()] = 0;
			}
				break;
			case 3: { // Kettenreaktion
				if (bm2 != null) {
					for (int j = 0; j < Math.max(bm.bombs.size(),
							bm2.bombs.size()); j++) {
						kettenreaktion(bm, bm2, 1, i, j);
					}
				} else {
					for (int j = 0; j < bm.bombs.size(); j++) {
						kettenreaktion(bm, 1, i, j);
					}
				}
			}
				break;
			case 4: { // zerstörbare mauer kapputt machen, exit setzen
				this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() - i
						* this.bomb.getRadius()] = -1;
			}
				break;
			default: {

			}
			}
			if (i < 100) {
				if (bm2 == null)
					kill(bm, 1, i);
				else
					kill(bm, bm2, 1, i);
			}

		}
		// unten
		for (int i = 1; i * this.bomb.getRadius() <= this.bomb
				.getExplosionRadius(); i++) {
			switch (this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() + i
					* this.bomb.getRadius()]) {
			case -2: {
				// PowerUp zerstören
				this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() + i
						* this.bomb.getRadius()] = 0;
				for (int j = 0; j < this.sp.powerups.size(); j++)
					if ((this.bomb.getPosX() == this.sp.powerups.get(j)
							.getPosX())
							&& (this.bomb.getPosY() + i * this.bomb.getRadius() == this.sp.powerups
									.get(j).getPosY())) {
						this.sp.powerups.remove(j);
						break;
					}
				this.sp.decNumbOfPowerUps();
			}
				break;
			case 1: { // unzerstoerbare mauer stoppt explosion
				i = 100;
			}
				break;
			case 2: { // zerstörbare mauer kapputt machen
				bomb.getOwner().addScore(1);
				double n = Math.random();
				if (n < 0.2) {
					this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() + i
							* this.bomb.getRadius()] = -2;
					n = Math.random();
					PowerUp powerup;
					if (n <= pExpRadp)
						powerup = new PowerUp(
								this.bomb.getPosX(),
								this.bomb.getPosY() + i * this.bomb.getRadius(),
								this.bomb.getRadius(), this.sp, "expRadp");
					else
						powerup = new PowerUp(
								this.bomb.getPosX(),
								this.bomb.getPosY() + i * this.bomb.getRadius(),
								this.bomb.getRadius(), this.sp, "bombep");
					this.sp.incNumbOfPowerUps();
					this.sp.powerups.add(powerup);
				} else
					this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() + i
							* this.bomb.getRadius()] = 0;
			}
				break;
			case 3: { // Kettenreaktion
				if (bm2 != null) {
					for (int j = 0; j < Math.max(bm.bombs.size(),
							bm2.bombs.size()); j++) {
						kettenreaktion(bm, bm2, 3, i, j);
					}
				} else {
					for (int j = 0; j < bm.bombs.size(); j++) {
						kettenreaktion(bm, 3, i, j);
					}
				}
			}
				break;
			case 4: { // zerstörbare mauer kapputt machen, exit setzen
				this.sp.raster[this.bomb.getPosX()][this.bomb.getPosY() + i
						* this.bomb.getRadius()] = -1;
			}
				break;
			default: {
			}
			}
			if (i < 100) {
				if (bm2 == null)
					kill(bm, 3, i);
				else
					kill(bm, bm2, 3, i);
			}

		}

		// links&rechts

		// links
		for (int i = 1; i * this.bomb.getRadius() <= this.bomb
				.getExplosionRadius(); i++) {
			switch (this.sp.raster[this.bomb.getPosX() - i
					* this.bomb.getRadius()][this.bomb.getPosY()]) {
			case -2: {
				// PowerUp zerstören
				this.sp.raster[this.bomb.getPosX() - i * this.bomb.getRadius()][this.bomb
						.getPosY()] = 0;
				for (int j = 0; j < this.sp.powerups.size(); j++)
					if ((this.bomb.getPosX() - i * this.bomb.getRadius() == this.sp.powerups
							.get(j).getPosX())
							&& (this.bomb.getPosY() == this.sp.powerups.get(j)
									.getPosY())) {
						this.sp.powerups.remove(j);
						break;
					}
				this.sp.decNumbOfPowerUps();
			}
				break;
			case 1: { // unzerstoerbare mauer stoppt explosion
				i = 100;
			}
				break;
			case 2: {
				bomb.getOwner().addScore(1);
				double n = Math.random();
				if (n < 0.2) {
					this.sp.raster[this.bomb.getPosX() - i
							* this.bomb.getRadius()][this.bomb.getPosY()] = -2;
					n = Math.random();
					PowerUp powerup;
					if (n <= pExpRadp)
						powerup = new PowerUp(this.bomb.getPosX() - i
								* this.bomb.getRadius(), this.bomb.getPosY(),
								this.bomb.getRadius(), this.sp, "expRadp");
					else
						powerup = new PowerUp(this.bomb.getPosX() - i
								* this.bomb.getRadius(), this.bomb.getPosY(),
								this.bomb.getRadius(), this.sp, "bombep");
					this.sp.incNumbOfPowerUps();
					this.sp.powerups.add(powerup);
				} else
					this.sp.raster[this.bomb.getPosX() - i
							* this.bomb.getRadius()][this.bomb.getPosY()] = 0;
			}
				break;
			case 3: {
				if (bm2 != null) {
					for (int j = 0; j < Math.max(bm.bombs.size(),
							bm2.bombs.size()); j++) {
						kettenreaktion(bm, bm2, 4, i, j);
					}
				} else {
					for (int j = 0; j < bm.bombs.size(); j++) {
						kettenreaktion(bm, 4, i, j);
					}
				}
			}
				break;
			case 4: {
				this.sp.raster[this.bomb.getPosX() - i * this.bomb.getRadius()][this.bomb
						.getPosY()] = -1;
			}
			}
			if (i < 100) {
				if (bm2 == null)
					kill(bm, 4, i);
				else
					kill(bm, bm2, 4, i);
			}
		}
		// rechts
		for (int i = 1; i * this.bomb.getRadius() <= this.bomb
				.getExplosionRadius(); i++) {
			switch (this.sp.raster[this.bomb.getPosX() + i
					* this.bomb.getRadius()][this.bomb.getPosY()]) {
			case -2: {
				// PowerUp zerstören
				this.sp.raster[this.bomb.getPosX() + i * this.bomb.getRadius()][this.bomb
						.getPosY()] = 0;
				for (int j = 0; j < this.sp.powerups.size(); j++)
					if ((this.bomb.getPosX() + i * this.bomb.getRadius() == this.sp.powerups
							.get(j).getPosX())
							&& (this.bomb.getPosY() == this.sp.powerups.get(j)
									.getPosY())) {
						this.sp.powerups.remove(j);
						break;
					}
				this.sp.decNumbOfPowerUps();
			}
				break;
			case 1: { // unzerstoerbare mauer stoppt explosion
				i = 100;
			}
				break;
			case 2: {
				bomb.getOwner().addScore(1);
				double n = Math.random();
				if (n < 0.2) {
					this.sp.raster[this.bomb.getPosX() + i
							* this.bomb.getRadius()][this.bomb.getPosY()] = -2;
					n = Math.random();
					PowerUp powerup;
					if (n <= pExpRadp)
						powerup = new PowerUp(this.bomb.getPosX() + i
								* this.bomb.getRadius(), this.bomb.getPosY(),
								this.bomb.getRadius(), this.sp, "expRadp");
					else
						powerup = new PowerUp(this.bomb.getPosX() + i
								* this.bomb.getRadius(), this.bomb.getPosY(),
								this.bomb.getRadius(), this.sp, "bombep");

					this.sp.incNumbOfPowerUps();
					this.sp.powerups.add(powerup);
				} else
					this.sp.raster[this.bomb.getPosX() + i
							* this.bomb.getRadius()][this.bomb.getPosY()] = 0;
			}
				break;
			case 3: {
				if (bm2 != null) {
					for (int j = 0; j < Math.max(bm.bombs.size(),
							bm2.bombs.size()); j++) {
						kettenreaktion(bm, bm2, 2, i, j);
					}
				} else {
					for (int j = 0; j < bm.bombs.size(); j++) {
						kettenreaktion(bm, 2, i, j);
					}
				}
			}
			case 4: {
				this.sp.raster[this.bomb.getPosX() + i * this.bomb.getRadius()][this.bomb
						.getPosY()] = -1;
			}
			}
			if (i < 100) {
				if (bm2 == null)
					kill(bm, 2, i);
				else
					kill(bm, bm2, 2, i);
			}
		}
		/** Bomberman steht AUF der Bombe */
		if ((bm.getPosX() == this.bomb.getPosX())
				&& (bm.getPosY() == this.bomb.getPosY())) {
			this.sp.repaint();
			Main.f.dispose(
					"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
					true);

			Main.f.restart(1, false);
		} else if (bm2 != null)
			if ((bm.getPosX() == this.bomb.getPosX())
					&& (bm.getPosY() == this.bomb.getPosY())
					|| (bm2.getPosX() == this.bomb.getPosX())
					&& (bm2.getPosY() == this.bomb.getPosY())) {
				this.sp.repaint();
				Main.f.dispose(
						"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
						true);

				Main.f.restart(2, false);
			}
		Main.f.updateScore();
		this.sp.repaint();
	}

	//
	// töten mit 1 spieler------------------------------------------------------
	//
	/**
	 * überprüft, ob der Bomberman im Einzelspielermodus stirbt
	 * 
	 * @param man
	 *            Referenz auf Bomberman
	 * @param direction
	 *            übergibt die Richtung in der geprüft wird
	 * @param i
	 *            aktuelle Stelle im Explosionsradius
	 */
	private void kill(Bomberman man, int direction, int i) {
		switch (direction) {
		case 1: { // oben
			if ((man.getPosX() == this.bomb.getPosX())
					&& (man.getPosY() == this.bomb.getPosY() - i
							* this.bomb.getRadius())) {
				this.sp.repaint();
				for (int f = 0; f < man.bombs.size(); f++) {
					man.bombs.get(f).tExp.cancel();
					man.bombs.get(f).tUnExp.cancel();
				}
				Main.f.dispose(
						"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
						true);

				Main.f.restart(1, true);
			}
		}
			break;
		case 2: { // rechts
			if ((man.getPosY() == this.bomb.getPosY())
					&& (man.getPosX() == this.bomb.getPosX() + i
							* this.bomb.getRadius())) {
				this.sp.repaint();
				for (int f = 0; f < man.bombs.size(); f++) {
					man.bombs.get(f).tExp.cancel();
					man.bombs.get(f).tUnExp.cancel();
				}
				Main.f.dispose(
						"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
						true);

				Main.f.restart(1, true);
			}
		}
			break;
		case 3: { // unten
			if ((man.getPosX() == this.bomb.getPosX())
					&& (man.getPosY() == this.bomb.getPosY() + i
							* this.bomb.getRadius())) {
				this.sp.repaint();
				for (int f = 0; f < man.bombs.size(); f++) {
					man.bombs.get(f).tExp.cancel();
					man.bombs.get(f).tUnExp.cancel();
				}
				Main.f.dispose(
						"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
						true);

				Main.f.restart(1, true);
			}
		}
			break;
		case 4: { // links
			if ((man.getPosY() == this.bomb.getPosY())
					&& (man.getPosX() == this.bomb.getPosX() - i
							* this.bomb.getRadius())) {
				this.sp.repaint();
				for (int f = 0; f < man.bombs.size(); f++) {
					man.bombs.get(f).tExp.cancel();
					man.bombs.get(f).tUnExp.cancel();
				}
				Main.f.dispose(
						"Deine eigene Bombe ist explodiert und hat dich mitgerissen!",
						true);

				Main.f.restart(1, true);
			}
		}
			break;
		}

	}

	//
	// Behandlung für Bomberman im Zweispielermodus -----------------------
	//
	/**
	 * überprüft, ob der Bomberman im Zweispielermodus stirbt.
	 * 
	 * @param man
	 *            Referenz auf Bomberman
	 * @param man2
	 *            Referenz auf den zweiten Bomberman
	 * @param direction
	 *            übergibt die Richtung in der geprüft wird
	 * @param i
	 *            aktuelle Position im Explosionsradius
	 */
	private void kill(Bomberman man, Bomberman man2, int direction, int i) {
		switch (direction) {
		case 1: { // oben
			if ((man.getPosX() == this.bomb.getPosX())
					&& (man.getPosY() == this.bomb.getPosY() - i
							* this.bomb.getRadius())) {
				this.sp.repaint();
				/** Andere Explosionen abbrechen */
				for (int f = 0; f < man.bombs.size(); f++) {
					man.bombs.get(f).tExp.cancel();
					man.bombs.get(f).tUnExp.cancel();
				}
				for (int g = 0; g < man2.bombs.size(); g++) {
					man2.bombs.get(g).tExp.cancel();
					man2.bombs.get(g).tUnExp.cancel();
				}
				if (man.equals(this.bomb.getOwner())) {

					Main.f.dispose("Du hast dich selbst getötet!", true);
					Main.f.restart(2, false);
				} else {
					man2.addScore(5);
					Main.f.dispose("Dein Gegenspieler hat dich getötet!", true);
					Main.f.restart(2, false);
				}
			} else if ((man2.getPosX() == this.bomb.getPosX())
					&& (man2.getPosY() == this.bomb.getPosY() - i
							* this.bomb.getRadius())) {
				this.sp.repaint();
				/** Andere Explosionen abbrechen */
				for (int f = 0; f < man.bombs.size(); f++) {
					man.bombs.get(f).tExp.cancel();
					man.bombs.get(f).tUnExp.cancel();
				}
				for (int g = 0; g < man2.bombs.size(); g++) {
					man2.bombs.get(g).tExp.cancel();
					man2.bombs.get(g).tUnExp.cancel();
				}
				if (man2.equals(this.bomb.getOwner())) {

					Main.f.dispose("Du hast dich selbst getötet!", true);
					Main.f.restart(2, false);
				} else {
					man.addScore(5);
					Main.f.dispose("Dein Gegenspieler hat dich getötet!", true);
					Main.f.restart(2, false);
				}
			}
		}
			break;
		case 2: { // rechts
			if ((man.getPosY() == this.bomb.getPosY())
					&& (man.getPosX() == this.bomb.getPosX() + i
							* this.bomb.getRadius())) {
				this.sp.repaint();
				/** Andere Explosionen abbrechen */
				for (int f = 0; f < man.bombs.size(); f++) {
					man.bombs.get(f).tExp.cancel();
					man.bombs.get(f).tUnExp.cancel();
				}
				for (int g = 0; g < man2.bombs.size(); g++) {
					man2.bombs.get(g).tExp.cancel();
					man2.bombs.get(g).tUnExp.cancel();
				}
				if (man.equals(this.bomb.getOwner())) {

					Main.f.dispose("Du hast dich selbst getötet!", true);
					Main.f.restart(2, false);
				} else {
					man2.addScore(5);
					Main.f.dispose("Dein Gegenspieler hat dich getötet!", true);
					Main.f.restart(2, false);
				}
			} else if ((man2.getPosY() == this.bomb.getPosY())
					&& (man2.getPosX() == this.bomb.getPosX() + i
							* this.bomb.getRadius())) {
				this.sp.repaint();
				/** Andere Explosionen abbrechen */
				for (int f = 0; f < man.bombs.size(); f++) {
					man.bombs.get(f).tExp.cancel();
					man.bombs.get(f).tUnExp.cancel();
				}
				for (int g = 0; g < man2.bombs.size(); g++) {
					man2.bombs.get(g).tExp.cancel();
					man2.bombs.get(g).tUnExp.cancel();
				}
				if (man2.equals(this.bomb.getOwner())) {

					Main.f.dispose("Du hast dich selbst getötet!", true);
					Main.f.restart(2, false);
				} else {
					man.addScore(5);
					Main.f.dispose("Dein Gegenspieler hat dich getötet!", true);
					Main.f.restart(2, false);
				}
			}
		}
			break;
		case 3: { // unten
			if ((man.getPosX() == this.bomb.getPosX())
					&& (man.getPosY() == this.bomb.getPosY() + i
							* this.bomb.getRadius())) {
				this.sp.repaint();
				this.sp.repaint();
				/** Andere Explosionen abbrechen */
				for (int f = 0; f < man.bombs.size(); f++) {
					man.bombs.get(f).tExp.cancel();
					man.bombs.get(f).tUnExp.cancel();
				}
				for (int g = 0; g < man2.bombs.size(); g++) {
					man2.bombs.get(g).tExp.cancel();
					man2.bombs.get(g).tUnExp.cancel();
				}
				if (man.equals(this.bomb.getOwner())) {

					Main.f.dispose("Du hast dich selbst getötet!", true);
					Main.f.restart(2, false);
				} else {
					man2.addScore(5);
					Main.f.dispose("Dein Gegenspieler hat dich getötet!", true);
					Main.f.restart(2, false);
				}
			} else if ((man2.getPosX() == this.bomb.getPosX())
					&& (man2.getPosY() == this.bomb.getPosY() + i
							* this.bomb.getRadius())) {
				this.sp.repaint();
				/** Andere Explosionen abbrechen */
				for (int f = 0; f < man.bombs.size(); f++) {
					man.bombs.get(f).tExp.cancel();
					man.bombs.get(f).tUnExp.cancel();
				}
				for (int g = 0; g < man2.bombs.size(); g++) {
					man2.bombs.get(g).tExp.cancel();
					man2.bombs.get(g).tUnExp.cancel();
				}
				if (man2.equals(this.bomb.getOwner())) {

					Main.f.dispose("Du hast dich selbst getötet!", true);
					Main.f.restart(2, false);
				} else {
					man.addScore(5);
					Main.f.dispose("Dein Gegenspieler hat dich getötet!", true);
					Main.f.restart(2, false);
				}
			}
		}
			break;
		case 4: { // links
			if ((man.getPosY() == this.bomb.getPosY())
					&& (man.getPosX() == this.bomb.getPosX() - i
							* this.bomb.getRadius())) {
				this.sp.repaint();
				/** Andere Explosionen abbrechen */
				for (int f = 0; f < man.bombs.size(); f++) {
					man.bombs.get(f).tExp.cancel();
					man.bombs.get(f).tUnExp.cancel();
				}
				for (int g = 0; g < man2.bombs.size(); g++) {
					man2.bombs.get(g).tExp.cancel();
					man2.bombs.get(g).tUnExp.cancel();
				}
				if (man.equals(this.bomb.getOwner())) {

					Main.f.dispose("Du hast dich selbst getötet!", true);
					Main.f.restart(2, false);
				} else {
					man2.addScore(5);
					Main.f.dispose("Dein Gegenspieler hat dich getötet!", true);
					Main.f.restart(2, false);
				}
			} else if ((man2.getPosY() == this.bomb.getPosY())
					&& (man2.getPosX() == this.bomb.getPosX() - i
							* this.bomb.getRadius())) {
				this.sp.repaint();
				/** Andere Explosionen abbrechen */
				for (int f = 0; f < man.bombs.size(); f++) {
					man.bombs.get(f).tExp.cancel();
					man.bombs.get(f).tUnExp.cancel();
				}
				for (int g = 0; g < man2.bombs.size(); g++) {
					man2.bombs.get(g).tExp.cancel();
					man2.bombs.get(g).tUnExp.cancel();
				}
				if (man2.equals(this.bomb.getOwner())) {

					Main.f.dispose("Du hast dich selbst getötet!", true);
					Main.f.restart(2, false);
				} else {
					man.addScore(5);
					Main.f.dispose("Dein Gegenspieler hat dich getötet!", true);
					Main.f.restart(2, false);
				}
			}
		}
			break;
		}

	}

	//
	// Kettenreaktion 1 spieler ----------------------------------------
	//
	/**
	 * Kettenreaktion von Bomben im Einspielermodus.
	 * 
	 * @param man
	 *            Referenz auf Bomberman
	 * @param direction
	 *            übergibt die Richtung in der geprüft wird
	 * @param i
	 *            aktuelle Stelle im Explosionsradius
	 * @param j
	 *            Position der Bombe in der Liste
	 */
	private void kettenreaktion(Bomberman man, int direction, int i, int j) {
		ExplosionThread t = new ExplosionThread(sp, bomb, man, direction, i, j);
		t.run();
	}

	//
	// Kettenreaktion 2 spieler -----------------------------------------
	//
	/**
	 * Kettenreaktion für Bomberman im Zweispielermodus
	 * 
	 * @param man
	 *            Referenz auf Bomberman
	 * @param man2
	 *            Referenz auf den zweiten Bomberman
	 * @param direction
	 *            übergibt die Richtung in der geprüft wird
	 * @param i
	 *            aktuelle Stelle im Explosionsradius
	 * @param j
	 *            Position der Bombe in der Liste
	 */
	private void kettenreaktion(Bomberman man, Bomberman man2, int direction,
			int i, int j) {
		if ((man.bombs.size() < j) || (man.bombs.size() == 0)) {
			kettenreaktion(man2, direction, i, j);
		} else if ((man2.bombs.size() < j) || (man2.bombs.size() == 0)) {
			kettenreaktion(man, direction, i, j);
		} else {
			switch (direction) {
			case 1: { // oben
				if (((man.bombs.get(j).getPosY() == this.bomb.getPosY() - i
						* this.bomb.getRadius()) && (man.bombs.get(j).getPosX() == this.bomb
						.getPosX()))) {
					man.bombs.get(j).tExp.schedule(
							new BombExplosion(man.bombs.get(j), sp, man), 0);
					man.bombs.get(j).tUnExp.schedule(new BombUnExplosion(
							man.bombs.get(j), man, sp), 500); // 500
																// ist
																// Differenz
																// zwischen
																// den
																// TImern
																// !!
				}
				if (((man2.bombs.get(j).getPosY() == this.bomb.getPosY() - i
						* this.bomb.getRadius()) && (man2.bombs.get(j)
						.getPosX() == this.bomb.getPosX()))) {
					man2.bombs.get(j).tExp.schedule(new BombExplosion(
							man2.bombs.get(j), sp, man2), 0);
					switch (direction) {
					case 1: { // oben
						if ((man.bombs.get(j).getPosY() == this.bomb.getPosY()
								- i * this.bomb.getRadius())
								&& (man.bombs.get(j).getPosX() == this.bomb
										.getPosX())) {
							man.bombs.get(j).tExp.schedule(new BombExplosion(
									man.bombs.get(j), sp, man), 0);
							man.bombs.get(j).tUnExp.schedule(
									new BombUnExplosion(man.bombs.get(j), man,
											sp), 500); // 500
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
						if ((man.bombs.get(j).getPosX() == this.bomb.getPosX()
								+ i * this.bomb.getRadius())
								&& (man.bombs.get(j).getPosY() == this.bomb
										.getPosY())) {
							man.bombs.get(j).tExp.schedule(new BombExplosion(
									man.bombs.get(j), sp, man), 0);
							man.bombs.get(j).tUnExp.schedule(
									new BombUnExplosion(man.bombs.get(j), man,
											sp), 500); // 500
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
						if ((man.bombs.get(j).getPosY() == this.bomb.getPosY()
								+ i * this.bomb.getRadius())
								&& (man.bombs.get(j).getPosX() == this.bomb
										.getPosX())) {
							man.bombs.get(j).tExp.schedule(new BombExplosion(
									man.bombs.get(j), sp, man), 0);
							man.bombs.get(j).tUnExp.schedule(
									new BombUnExplosion(man.bombs.get(j), man,
											sp), 500); // 500
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
						if ((man.bombs.get(j).getPosX() == this.bomb.getPosX()
								- i * this.bomb.getRadius())
								&& (man.bombs.get(j).getPosY() == this.bomb
										.getPosY())) {
							man.bombs.get(j).tExp.schedule(new BombExplosion(
									man.bombs.get(j), sp, man), 0);
							man.bombs.get(j).tUnExp.schedule(
									new BombUnExplosion(man.bombs.get(j), man,
											sp), 500); // 500
														// ist
														// Differenz
														// zwischen
														// den
														// TImern
														// !!
						}
					}
					}
					man2.bombs.get(j).tUnExp.schedule(new BombUnExplosion(
							man2.bombs.get(j), man2, sp), 500); // 500 ist
					// Differenz
					// zwischen
					// den
					// TImern
					// !!
				}
			}
				break;
			case 2: { // rechts
				if (((man.bombs.get(j).getPosY() == this.bomb.getPosY()) && (man.bombs
						.get(j).getPosX() == this.bomb.getPosX() + i
						* this.bomb.getRadius()))) {
					man.bombs.get(j).tExp.schedule(
							new BombExplosion(man.bombs.get(j), sp, man), 0);
					man.bombs.get(j).tUnExp.schedule(new BombUnExplosion(
							man.bombs.get(j), man, sp), 500); // 500
																// ist
																// Differenz
																// zwischen
																// den
																// TImern
																// !!
				}
				if (((man2.bombs.get(j).getPosY() == this.bomb.getPosY()) && (man2.bombs
						.get(j).getPosX() == this.bomb.getPosX() + i
						* this.bomb.getRadius()))) {
					man2.bombs.get(j).tExp.schedule(new BombExplosion(
							man2.bombs.get(j), sp, man2), 0);
					man2.bombs.get(j).tUnExp.schedule(new BombUnExplosion(
							man2.bombs.get(j), man2, sp), 500); // 500 ist
					// Differenz
					// zwischen
					// den
					// TImern
					// !!
				}
			}
				break;
			case 3: { // unten
				if (((man.bombs.get(j).getPosY() == this.bomb.getPosY() + i
						* this.bomb.getRadius()) && (man.bombs.get(j).getPosX() == this.bomb
						.getPosX()))) {
					man.bombs.get(j).tExp.schedule(
							new BombExplosion(man.bombs.get(j), sp, man), 0);
					man.bombs.get(j).tUnExp.schedule(new BombUnExplosion(
							man.bombs.get(j), man, sp), 500); // 500
																// ist
																// Differenz
																// zwischen
																// den
																// TImern
																// !!
				}
				if (((man2.bombs.get(j).getPosY() == this.bomb.getPosY() + i
						* this.bomb.getRadius()) && (man2.bombs.get(j)
						.getPosX() == this.bomb.getPosX()))) {
					man2.bombs.get(j).tExp.schedule(new BombExplosion(
							man2.bombs.get(j), sp, man2), 0);
					man2.bombs.get(j).tUnExp.schedule(new BombUnExplosion(
							man2.bombs.get(j), man2, sp), 500); // 500 ist
					// Differenz
					// zwischen
					// den
					// TImern
					// !!
				}
			}
				break;
			case 4: { // links
				if (((man.bombs.get(j).getPosY() == this.bomb.getPosY()) && (man.bombs
						.get(j).getPosX() == this.bomb.getPosX() - i
						* this.bomb.getRadius()))) {
					man.bombs.get(j).tExp.schedule(
							new BombExplosion(man.bombs.get(j), sp, man), 0);
					man.bombs.get(j).tUnExp.schedule(new BombUnExplosion(
							man.bombs.get(j), man, sp), 500); // 500
																// ist
																// Differenz
																// zwischen
																// den
																// TImern
																// !!
				}
				if (((man2.bombs.get(j).getPosY() == this.bomb.getPosY()) && (man2.bombs
						.get(j).getPosX() == this.bomb.getPosX() - i
						* this.bomb.getRadius()))) {
					man2.bombs.get(j).tExp.schedule(new BombExplosion(
							man2.bombs.get(j), sp, man2), 0);
					man2.bombs.get(j).tUnExp.schedule(new BombUnExplosion(
							man2.bombs.get(j), man2, sp), 500); // 500 ist
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
}