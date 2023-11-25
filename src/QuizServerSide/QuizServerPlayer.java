package QuizServerSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class QuizServerPlayer extends Thread implements Serializable {

    private final int START=0;
    private final int LOBBY = 1;
    private final int CATEGORY = 2;
    private final int GAME = 3;
    private final int SCORE = 4;

    //Testing------------------
    int status = LOBBY;

    //-------------------------


    //NETWORK
    transient Socket socket;
    transient ObjectInputStream input;
    transient ObjectOutputStream output;
    QuizServerGame game;
    NetworkProtocolServer serverProtocol;

    //GAME
    int score;
    boolean firstMove;
    QuizServerPlayer opponent;
    String playerName;



    public QuizServerPlayer(Socket socket, QuizServerGame game, boolean firstMove) {
        this.game = game;
        this.socket = socket;
        this.serverProtocol = new NetworkProtocolServer(this);
        try {
            output = new ObjectOutputStream(this.socket.getOutputStream());
            input = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setOpponent(QuizServerPlayer opponent) {
        this.opponent = opponent;
    }

    public void run()
    {
        try {
            while (true) {

            //ONLY TESTING

                //System.out.println("Running gameloop for player: " + getPlayerName());

                //SEND NETWORKMESSAGE OBJECT
                //System.out.println("Sending NetworkMessageObject for player: " + getPlayerName());
                //output.writeObject(new NetworkMessage((byte) 0x10));

                //SEND PLAYER OBJECT
                //System.out.println("Sending PlayerObject for player: " + getPlayerName());
                //output.writeObject(this);

            //END OF TESTING

                //Test-----------------
                if(status == LOBBY) {
                    //-----------------------

                    //Let user send their desired name
                    //Keep reading from client until we receive a string
                    boolean continueLoop = true;
                    while (continueLoop) {
                        Object lastReadObject = input.readObject();
                        if (lastReadObject instanceof String) {
                            serverProtocol.parseSetPlayerName(input, this);
                        }
                    }

                    //Player is ready to start game
                    NetworkProtocolServer.sendPlayerReady(output);
                    status = 1;
                }

                if (status==CATEGORY){

                    NetworkProtocolServer.sendQuestion(output, "Bajskorv");



                    status = GAME;
                }





                output.flush(); // True?: One flush per loop should be more than enough if not too much, don't call flush more than once per loop
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //Cannot be named getName cause inherit from Thread class
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public QuizServerGame getGame() {
        return game;
    }
    public ObjectOutputStream getOutputStream() {
        return output;
    }
    public ObjectInputStream getInputStream() {
        return input;
    }
}
