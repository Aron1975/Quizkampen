package QuizServerSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//ISSUES WITH THIS:
//Need to code proper buffer storing and buffer reading to work as originally intended (Think will still be useful)

//This class handles sending and parsing packets(NetworkMessage class)
//Reads the first byte which controls what(structure) to send/parse based on the function connected to that specific number of first byte
public class NetworkProtocolServer {

    enum PROTOCOL_SEND
    {
        GAME_READY,

    }

    public void parsePacket(ObjectInputStream inputStream, NetworkMessage networkMessage) throws IOException, ClassNotFoundException {
        int networkCode = networkMessage.getNetworkCode();

        switch(networkCode) {
            //Hexadecimal
            case 0:
                parseSetPlayerName(inputStream);
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
    public void parseSetPlayerName(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        System.out.println("Parse SetPlayerName: " + (String)lastReadObject);
    }

}
