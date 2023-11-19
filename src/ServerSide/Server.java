package ServerSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Server extends Thread {

    int port = 45000;

    Socket s;

    public Server(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
             PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        ){
            System.out.println("Connected");
            String inData = "";
            while ((inData = br.readLine()) != null) {
                System.out.println(inData);
                //Spel logik.........
                int randomNr = (int)Math.round(Math.random()*10);
                pw.println("Svar: " + randomNr);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
