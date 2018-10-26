package remoteAccess;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {

		try (Socket socket = new Socket("localhost", 30);
				OutputStream out = socket.getOutputStream();
				InputStream in = socket.getInputStream()) {

			out.write("GET / HTTP/1.0\n\n".getBytes());
			try (ByteArrayOutputStream bytes = new ByteArrayOutputStream()) {
				byte[] buf = new byte[1024 * 8];
				int length = 0;
				while ((length = in.read(buf)) != -1) {
					bytes.write(buf, 0, length);
				}
				System.out.println(new String(bytes.toByteArray(), "UTF-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
