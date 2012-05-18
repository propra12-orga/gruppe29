import java.util.TimerTask;

public class BombUnExplosion extends TimerTask {

	private Bomb bomb;
	private Spielfeld sp;

	public BombUnExplosion(Bomb b, Spielfeld sp) {
		this.bomb = b;
		this.sp = sp;
	}

	@Override
	public void run() {
		bomb.setExploded(false);
		bomb.setVisible(false);
		System.out.println("Bombe abgefackelt!");
		sp.repaint();
	}

}
