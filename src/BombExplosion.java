import java.util.TimerTask;

public class BombExplosion extends TimerTask {

	private Bomb bomb;
	private Spielfeld sp;

	public BombExplosion(Bomb b, Spielfeld sp) {
		this.bomb = b;
		this.sp = sp;
	}

	@Override
	public void run() {
		bomb.setExploded(true);
		System.out.println("Bombe gezündet!");
		sp.repaint();
	}

}
