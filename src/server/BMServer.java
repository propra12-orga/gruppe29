package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BMServer {

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(3143);

		while (true) {
			Socket client = null;

			try {
				client = server.accept();
				// FUNKTION
				serverAction(client);
			} catch (IOException e) {
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

	private static void serverAction(Socket client) throws Exception {
		DataInputStream in = new DataInputStream(client.getInputStream());
		DataOutputStream out = new DataOutputStream(client.getOutputStream());

		System.out.println("Einlesen..");
		String xmlin = in.readUTF();

		// SPEICHERN
		FileOutputStream fos = new FileOutputStream("maps/temp.xml");
		for (int i = 0; i < xmlin.length(); i++) {
			fos.write((byte) xmlin.charAt(i));
		}
		fos.close();

		System.out.println("Datei gespeichert");

		// LESEN UND ZURÜCKSCHICKEN
		byte character;
		String xmlout = "";
		System.out.println("Datei lesen...");
		FileInputStream fis = new FileInputStream("maps/temp.xml");
		do {
			character = (byte) fis.read();
			System.out.print("CHAR: " + (char) character);
			if (character != -1)
				xmlout += (char) character;
		} while (character != -1);
		fis.close();
		System.out.println("Datei gelesen");
		out.writeUTF(xmlout);
		System.out.println("Ausgabe an Client");
	}
}
