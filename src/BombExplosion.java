import java.util.TimerTask;

public class BombExplosion extends TimerTask {

	private Bomb bomb;
	private Spielfeld sp;
	Bomberman bm;

	public BombExplosion(Bomb b, Spielfeld sp, Bomberman man) {
		this.bomb = b;
		this.sp = sp;
		this.bm = man;
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
			Main.f.dispose();
			Main.f.restart();
		}
		sp.repaint();
	}
}