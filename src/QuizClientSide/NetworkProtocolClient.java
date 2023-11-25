package QuizClientSide;

import QuizServerSide.NetworkMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NetworkProtocolClient {

    NetworkProtocolClient(QuizController quizController){
        this.quizController = quizController;
    }
    QuizController quizController;

    NetworkProtocolClient(){}

    //THIS NUMBER GETS SENT TO SERVER, ONLY RELATED TO SERVER PARSE
    //IF YOU WANT NUMBERS RELATED TO CLIENT PARSE, LOOK AT NetworkProtocolServer enum
    enum PROTOCOL_SEND
    {
        SET_PLAYERNAME,
        GET_CATEGORY,

    }

    public void parsePacket(ObjectInputStream inputStream, NetworkMessage networkMessage) throws IOException, ClassNotFoundException {
        int networkCode = networkMessage.getNetworkCode();

        switch(networkCode) {
            case 0:
                parsePlayerReady(inputStream);
                break;
            case 1:
                parseGetCategory(inputStream);
                break;
            case 2:
                //parseChooseCategory();
                break;
            case 3:
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
    public void parsePlayerReady(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        System.out.println("Parse player ready: " + (boolean)lastReadObject);
        System.out.println("PLAYER READY B4 READ FROM SERVER: " + quizController.player.getReadyToStartGame());
        quizController.player.setReadyToStartGame((boolean)lastReadObject);
        System.out.println("PLAYER READY AFTER READ FROM SERVER: " + quizController.player.getReadyToStartGame());
    }

    public void parseGetCategory(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        quizController.pGUI.setCategoryName((String)lastReadObject);
        System.out.println("Category: " + (String)lastReadObject);

    }
}
