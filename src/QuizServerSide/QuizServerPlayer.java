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
    boolean ready;



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
    public int getScore() {
        return score;
    }

    public void setScore(int score) throws IOException {
        this.score += score;
        getNetworkProtocolServer().sendScore(output,score);
    }
    public void run()
    {
        try {
            while (true) {

                if (status == LOBBY) {
                    //Player set name
                    Object lastReadObject = input.readObject();
                    System.out.println(lastReadObject);
                    if (lastReadObject instanceof NetworkMessage) {
                        serverProtocol.parseSetPlayerName(input, this);
                        serverProtocol.sendOpponentNotReady(output);
                        serverProtocol.sendChangeWindow(output, "1");
                        while (!(getReady() && opponent.getReady())) {
                            Thread.sleep(1);
                        }
                        opponent.getNetworkProtocolServer().sendOpponentName(output, opponent.getPlayerName());
                        //Skicka nytt fönster till client
                        setScore(50);

                        status = CATEGORY;
                    }
                }


                if (status == CATEGORY) {

                    //NetworkProtocolServer.sendQuestion(output, "Bajskorv");
                    status = GAME;

                }
                if (status == GAME) {
                    // Plocka nästa fråga från databas
                    currentQuestion = game.getAq().generateRandomQuestion("Mat:");
                    //skickar fråga och alternativ
                    serverProtocol.sendQuestion(output, currentQuestion);
                    //ta emot svar
                    Object lastReadObject = input.readObject();
                    if (lastReadObject instanceof NetworkMessage) {
                        boolean correctAnswer = serverProtocol.parseAnswerQuestion(input, this);
                        if (correctAnswer){
                            setScore(1);

                        }
                        System.out.println("correctAnswer: " + correctAnswer);
                        //validera svar mot correctanswer och skicka tillbaks
                        serverProtocol.sendAnswerResult(output, correctAnswer);
                    }
                    //

                   //status = SCORE;
                }

                if(status == SCORE) {

                }

                output.flush(); // True?: One flush per loop should be more than enough if not too much, don't call flush more than once per loop
            }
        }catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
    public boolean getReady() {
        return ready;
    }
    public void setReady(boolean ready) {
        this.ready = ready;
    }

}
