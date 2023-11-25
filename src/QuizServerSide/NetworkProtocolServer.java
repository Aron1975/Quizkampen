package QuizServerSide;

import QuizClientSide.QuizController;
import QuizClientSide.QuizPlayer;

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
        SEND_CATEGORY,
        OPPONENT_NAME,

    }

    public void parsePacket(ObjectInputStream inputStream, NetworkMessage networkMessage) throws IOException, ClassNotFoundException {
        int networkCode = networkMessage.getNetworkCode();

        switch(networkCode) {
            //Hexadecimal
            case 0:
                parseSetPlayerName(inputStream, player);
                break;
            case 1:
                //parseAnswerQuestion();
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
    public void parseSetPlayerName(ObjectInputStream inputStream, QuizServerPlayer player) throws IOException, ClassNotFoundException {
        System.out.println("Parse set player name");
        Object lastReadObject = inputStream.readObject();
        player.setPlayerName((String)lastReadObject);
    }

    public static void sendPlayerReady(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeObject(new NetworkMessage(NetworkProtocolServer.PROTOCOL_SEND.PLAYER_READY.ordinal()));
        outputStream.writeObject((boolean)true);
    }

    public void sendOpponentName(ObjectOutputStream outputStream, String name) throws IOException {
        System.out.println("Send opponent name");
        outputStream.writeObject(new NetworkMessage(NetworkProtocolServer.PROTOCOL_SEND.OPPONENT_NAME.ordinal()));
        outputStream.writeObject((String)name);
    }

    public static void sendQuestion(ObjectOutputStream outputStream, String cat) throws IOException{
        outputStream.writeObject(new NetworkMessage(PROTOCOL_SEND.SEND_CATEGORY.ordinal()));
        outputStream.writeObject(cat);
    }

}
