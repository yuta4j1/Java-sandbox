package remoteAccess.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiAccessServer extends Thread {

	private static Socket clientSocket = null;

	public static void main(String[] args) {

		try (ServerSocket socket = new ServerSocket(31)) {
			while (true) {
				clientSocket = socket.accept();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		if (clientSocket == null) {
			return;
		}
		try (OutputStream out = clientSocket.getOutputStream()) {
			out.write("HTTP/1.0 200 OK\n".getBytes());
			out.write("Content-Type: text/html\n\n".getBytes());
			out.write("<h1>Hello, world!</h1>".getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {}
		}

	}

}
