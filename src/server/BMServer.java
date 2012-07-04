package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BMServer implements Runnable {

	private Socket client;
	private ServerSocket server;

	public boolean flag;

	public int counter;

	public BMServer() throws Exception {
		server = new ServerSocket(3145);
		flag = false;
		counter = 0;

	}

	public void writeToClient(int message) throws Exception {
		DataOutputStream out = new DataOutputStream(client.getOutputStream());

		out.writeInt(message);
		System.out.println("Senden zum Client");

	}

	public void readFromClient(Socket client) throws Exception {
		DataInputStream in = new DataInputStream(client.getInputStream());

		int i = in.readInt();
		if (i != -1) {
			System.out.println("Message empfangen");
			System.out.println(i);
			counter = i;
		}
	}

	@Override
	public void run() {
		while (true) {
			client = null;

			try {
				client = server.accept();
				// FUNKTION
				this.readFromClient(client);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (client != null)
					try {
						client.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
	}
}
