import java.util.TimerTask;

public class BombExplosion extends TimerTask {

	private Bomb bomb;
	private Spielfeld sp;
	Bomberman bm, bm2;

	public BombExplosion(Bomb b, Spielfeld sp, Bomberman man) {
		this.bomb = b;
		this.sp = sp;
		this.bm = man;
	}

	public BombExplosion(Bomb b, Spielfeld sp, Bomberman man, Bomberman man2) {
		this.bomb = b;
		this.sp = sp;
		this.bm = man;
		this.bm2 = man2;
	}

	@Override
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
					"Die Bombe ist explodiert und hat dich mitgerissen!", true,
					1);
			Main.f.restart();
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
				Main.f.dispose("Dein Gegenspieler hat dich erledigt!", true, 2);
				Main.f.restart();
			}
		}
		sp.repaint();
	}
}