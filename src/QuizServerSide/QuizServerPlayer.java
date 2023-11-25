package QuizServerSide;

import QuizServerSide.Questions.ArrayOfQuestions;
import QuizServerSide.Questions.Questions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class QuizServerPlayer extends Thread implements Serializable {

    private final int LOBBY=0;
    private final int CATEGORY = 1;
    private final int GAME = 2;
    private final int SCORE = 3;

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
    Questions currentQuestion;



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

    public QuizServerPlayer getOpponent() {
        return opponent;
    }

    public void setOpponent(QuizServerPlayer opponent) {
        this.opponent = opponent;
    }

    public void run()
    {
        try {
            boolean continueLoop = true;
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

                System.out.println("OUT-OUT WHILE: " + getName());
                //boolean continueLoop = true;
                if (status == LOBBY) {
                    //-----------------------

                    //Let user send their desired name
                    //Keep reading from client until we receive a string
                    System.out.println("OUT WHILE: " + getName());
                    while (continueLoop) {
                        Object lastReadObject = input.readObject();
                        if (lastReadObject instanceof NetworkMessage) {
                            serverProtocol.parseSetPlayerName(input, this);

                            while(getOpponent().getPlayerName() == null)
                            {
                                System.out.println("Opponent name is null");
                            }
                            System.out.println("IN WHILE: " + getName());
                            System.out.println("Opponent Name: " + opponent.getPlayerName());


                            opponent.getNetworkProtocolServer().sendOpponentName(output, opponent.getPlayerName());
                            continueLoop = false;

                            /*
                            if (game.playerOne == this) {
                                System.out.println("SEND PACKET TO OPPONENT");
                                game.playerTwo.getNetworkProtocolServer().sendOpponentName(output, game.playerOne.getPlayerName());
                            } else {
                                System.out.println("SEND PACKET TO OPPONENT");
                                game.playerOne.getNetworkProtocolServer().sendOpponentName(output, game.playerOne.getPlayerName());
                            }

                             */
                        }
                    }

                    //Player is ready to start game
                    //NetworkProtocolServer.sendPlayerReady(output);
                    status = GAME;
                }


                if (status == CATEGORY) {

                    //NetworkProtocolServer.sendQuestion(output, "Bajskorv");
                    status = GAME;
                }
                if (status == GAME) {
                    // Plocka nästa fråga från databas
                    currentQuestion = game.getAq().generateRandomQuestion("Sci-fi:");
                    //skickar fråga och alternativ
                    serverProtocol.sendQuestion(output, currentQuestion);
                    //ta emot svar
                    Object lastReadObject = input.readObject();
                    if (lastReadObject instanceof NetworkMessage) {
                        boolean correctAnswer = serverProtocol.parseAnswerQuestion(input, this);
                        System.out.println("correctAnswer: " + correctAnswer);
                        //validera svar mot correctanswer och skicka tillbaks
                        serverProtocol.sendAnswerResult(output, correctAnswer);
                    }
                    //updatera färg på knappen
                    //

                   // status = SCORE;
                }

                if(status == SCORE) {

                }

                output.flush(); // True?: One flush per loop should be more than enough if not too much, don't call flush more than once per loop
            }
        }catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }


    //Cannot be named getName cause inherit from Thread class
    public String getPlayerName() {
        return playerName;
    }
    public NetworkProtocolServer getNetworkProtocolServer() {
        return serverProtocol;
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
