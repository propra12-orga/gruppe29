package server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class BMClient {

	public static void main(String[] args) {
		Socket server = null;

		try {
			server = new Socket("localhost", 3141);
			InputStream in = server.getInputStream();
			OutputStream out = server.getOutputStream();

			out.write(5);
			int result = in.read();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (server != null)
				try {
					server.close();
				} catch (Exception e) {
				}
		}
	}
}
