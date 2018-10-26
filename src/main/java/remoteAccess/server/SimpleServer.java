package remoteAccess.server;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

	public static void main(String[] args) {
		try (ServerSocket socket = new ServerSocket(30)) {
			while (true) {
				try (Socket clientSocket = socket.accept();
						OutputStream out = clientSocket.getOutputStream()) {
					out.write("HTTP/1.0 200 OK\n".getBytes());
					out.write("Content-Type: text/html\n\n".getBytes());
					out.write("<h1>Hello, world!</h1>".getBytes());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
