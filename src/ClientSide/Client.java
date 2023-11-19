package ClientSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    //String ip="127.0.0.1";
    String ip = "192.168.1.97";
    int port = 45000;
    Socket socket;
    PrintWriter pw;
    BufferedReader br;

    public Client() {

        try {
            this.socket = new Socket(ip, port);
            this.pw = new PrintWriter(socket.getOutputStream(), true);
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String sendMessage(String data) {
        pw.println(data);
        return readMessage();
    }

    public String readMessage() {
        String temp = "";
        try {
            while ((temp = br.readLine()) == null) {
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return temp;
    }




    public static void main(String[] args) {
        new Client();
    }
}
