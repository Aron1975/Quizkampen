package QuizServerSide;

import QuizServerSide.Questions.Questions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//ISSUES WITH THIS:
//Need to code proper buffer storing and buffer reading to work as originally intended (Think will still be useful)

//This class handles sending and parsing packets(NetworkMessage class)
//Reads the first byte which controls what(structure) to send/parse based on the function connected to that specific number of first byte
public class NetworkProtocolServer {

    NetworkProtocolServer(QuizServerPlayer player){
        this.player = player;
    }
    QuizServerPlayer player;

    enum PROTOCOL_SEND
    {
        PLAYER_READY,
        SEND_CATEGORIES,
        OPPONENT_NAME,
        SEND_QUESTION,
        SEND_ANSWER_RESULT,
        CHANGE_WINDOW,
        SCORE,
        OPPONENT_NOT_READY,
        PLAYER_TO_CHOOSE_CATEGORY,
        SEND_CATEGORY_TO_OPPONENT,
    }

    public void parsePacket(ObjectInputStream inputStream, NetworkMessage networkMessage) throws IOException, ClassNotFoundException {
        int networkCode = networkMessage.getNetworkCode();

        switch(networkCode) {
            //Hexadecimal
            case 0:
                parseSetPlayerName(inputStream, player);
                break;
            case 1:
                //UNUSED
                break;
            case 2:
                parseAnswerQuestion(inputStream, player);
                break;
            case 3:
                parseChosenCategory(inputStream, player);
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
            case 15:
                break;
            case 16:
                break;
        }
    }

    //public static void sendPacket(ObjectOutputStream objectOutputStream, NetworkMessage networkMessage) throws IOException {
    //    objectOutputStream.writeObject(networkMessage);
    //}
    public void parseSetPlayerName(ObjectInputStream inputStream, QuizServerPlayer player) throws IOException, ClassNotFoundException {
        System.out.println("Parse set player name");
        Object lastReadObject = inputStream.readObject();
        player.setPlayerName((String)lastReadObject);
        player.setReady(true);
    }
    public boolean parseAnswerQuestion(ObjectInputStream inputStream, QuizServerPlayer player) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        System.out.println((String)lastReadObject);
        player.setReady(true);
        boolean result=player.currentQuestion.checkAnswer((String)lastReadObject);
        lastReadObject=inputStream.readObject();
        player.lastAnsweredButtonIndex=(int) lastReadObject;

        return result;
    }
    public void parseChosenCategory(ObjectInputStream inputStream, QuizServerPlayer player) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        player.game.setCurrentCategory((String)lastReadObject);
        System.out.println("CATEGORY PICKED: " + lastReadObject);
    }


    public static void sendPlayerReady(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeObject(new NetworkMessage(NetworkProtocolServer.PROTOCOL_SEND.PLAYER_READY.ordinal()));
        outputStream.writeObject((boolean)true);
    }

    public void sendOpponentName(ObjectOutputStream outputStream, String name) throws IOException {
        System.out.println("Send opponent name" + name);
        outputStream.writeObject(new NetworkMessage(NetworkProtocolServer.PROTOCOL_SEND.OPPONENT_NAME.ordinal()));
        outputStream.writeObject((String)name);
    }

    public void sendCategories(ObjectOutputStream outputStream, String[] categories) throws IOException{
        outputStream.writeObject(new NetworkMessage(PROTOCOL_SEND.SEND_CATEGORIES.ordinal()));
        outputStream.writeObject(categories);
    }
    public void sendQuestion(ObjectOutputStream outputStream, Questions question) throws IOException{
        outputStream.writeObject(new NetworkMessage(PROTOCOL_SEND.SEND_QUESTION.ordinal()));
        outputStream.writeObject(question.getQuestion());
        outputStream.writeObject(question.getAlternative());
        System.out.println("Send question to + " + player.playerName);

    }
    public void sendAnswerResult(ObjectOutputStream outputStream, boolean result) throws IOException{
        outputStream.writeObject(new NetworkMessage(PROTOCOL_SEND.SEND_ANSWER_RESULT.ordinal()));
        outputStream.writeObject(result);
        outputStream.writeObject(player.lastAnsweredButtonIndex);

    }
    public void sendChangeWindow(ObjectOutputStream outputStream,String windowNumber) throws IOException {
        outputStream.writeObject(new NetworkMessage(PROTOCOL_SEND.CHANGE_WINDOW.ordinal()));
        outputStream.writeObject(windowNumber);

    }
    public void sendScore(ObjectOutputStream outputStream,int score) throws IOException {
        outputStream.writeObject(new NetworkMessage(PROTOCOL_SEND.SCORE.ordinal()));
        outputStream.writeObject(score);
    }
    public void sendOpponentNotReady(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeObject(new NetworkMessage(PROTOCOL_SEND.OPPONENT_NOT_READY.ordinal()));
        outputStream.writeObject((boolean)false);
    }

    public void sendIsPlayerToChooseCategory(ObjectOutputStream outputStream, boolean pickCategory) throws IOException {
        outputStream.writeObject(new NetworkMessage(PROTOCOL_SEND.PLAYER_TO_CHOOSE_CATEGORY.ordinal()));
        outputStream.writeObject(pickCategory);
    }

    public void sendCategoryToOpponent(ObjectOutputStream outputStream, String category) throws IOException {
        outputStream.writeObject(new NetworkMessage(PROTOCOL_SEND.SEND_CATEGORY_TO_OPPONENT.ordinal()));
        outputStream.writeObject(category);
    }
}
