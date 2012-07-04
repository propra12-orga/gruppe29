package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class BMClient implements Runnable {

	private Socket server;
	public int[][] raster;
	public boolean flag;

	public int counter;

	public BMClient() throws Exception {
		flag = false;
		counter = 0;
	}

	public void sendToServer(int message) throws Exception {
		DataOutputStream out = new DataOutputStream(server.getOutputStream());

		out.writeInt(message);
		System.out.println("Message geschickt");
	}

	public void readFromServer() throws Exception {
		DataInputStream in = new DataInputStream(server.getInputStream());

		int i = in.readInt();
		if (i > -1) {
			System.out.println("Message empfangen");
			System.out.println(i);
			counter = i;
		}

	}

	public void run() {
		while (true) {

			try {
				server = new Socket("134.99.36.229", 3145);
				this.readFromServer();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					server.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
