import java.util.TimerTask;

public class BombExplosion extends TimerTask {

	private Bomb bomb;

	public BombExplosion(Bomb b) {
		this.bomb = b;
	}

	@Override
	public void run() {
		bomb.setExploded(true);
		System.out.println("Tralala");

	}

}
