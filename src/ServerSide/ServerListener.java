package ServerSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    int port = 45000;

    public ServerListener() {

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                System.out.println("Väntar på användare: ");
                Socket socket = serverSocket.accept();
                Server s = new Server(socket);
                s.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        new ServerListener();
    }
}
