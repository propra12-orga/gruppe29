package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;

public class BMClient {

	public static void main(String[] args) {
		Socket server = null;
		String xml = "";
		byte character;
		File f = new File("maps/Default.xml");

		try {
			System.out.println("Datei lesen...");
			FileInputStream fis = new FileInputStream(f);

			do {
				character = (byte) fis.read();
				System.out.println("CHAR: " + (char) character);
				if (character != -1)
					xml += (char) character;
			} while (character != -1);

			fis.close();

			System.out.println("Datei gelesen");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			server = new Socket("134.99.38.235", 3143);
			DataInputStream in = new DataInputStream(server.getInputStream());
			DataOutputStream out = new DataOutputStream(
					server.getOutputStream());

			out.writeUTF(xml);
			System.out.println("Abgabe an Server");
			String xmlin = in.readUTF();

			// VOM SERVER LESEN UND SPEICHERN
			FileOutputStream fos = new FileOutputStream("maps/temp.xml");
			for (int i = 0; i < xml.length(); i++) {
				fos.write((byte) xmlin.charAt(i));
			}
			fos.close();
			System.out.println("Datei gespeichert");

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
