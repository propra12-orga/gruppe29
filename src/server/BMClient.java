package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

public class BMClient implements Runnable {

	private Socket server;
	public int[][] raster;
	public boolean flag;

	public List<Integer> befehle;
	public int counter;

	public BMClient() throws Exception {
		server = new Socket("134.99.36.224", 3145);
		flag = false;
		counter = 0;
	}

	public void sendToServer(int message) throws Exception {
		DataOutputStream out = new DataOutputStream(server.getOutputStream());

		out.write(message);
		System.out.println("Message geschickt");
	}

	public void readFromServer() throws Exception {
		DataInputStream in = new DataInputStream(server.getInputStream());

		int i = in.read();
		if (i != -1) {
			System.out.println("Message empfangen");
			befehle.add(i);
			counter++;
		}

	}

	public void run() {
		while (true) {
			try {
				this.readFromServer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
