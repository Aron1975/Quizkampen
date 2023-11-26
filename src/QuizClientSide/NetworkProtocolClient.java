package QuizClientSide;

import QuizServerSide.NetworkMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NetworkProtocolClient {

    NetworkProtocolClient(QuizController quizController) {
        this.quizController = quizController;
    }

    QuizController quizController;

    NetworkProtocolClient() {
    }

    //THIS NUMBER GETS SENT TO SERVER, ONLY RELATED TO SERVER PARSE
    //IF YOU WANT NUMBERS RELATED TO CLIENT PARSE, LOOK AT NetworkProtocolServer enum
    enum PROTOCOL_SEND {
        SET_PLAYERNAME,
        GET_CATEGORY,
        SEND_ANSWER,

    }

    public void parsePacket(ObjectInputStream inputStream, NetworkMessage networkMessage) throws IOException, ClassNotFoundException {
        int networkCode = networkMessage.getNetworkCode();

        switch (networkCode) {
            case 0:
                parsePlayerReady(inputStream);
                break;
            case 1:
                parseGetCategory(inputStream);
                break;
            case 2:
                parseGetOpponentName(inputStream);
                break;
            case 3:
                parseSendQuestion(inputStream);
                break;
            case 4:
                parseAnswerResult(inputStream);
                break;
            case 5:
                parseChangeWindow(inputStream);
                break;
            case 6:
                parseScore(inputStream);
                break;
            case 7:
                parseOpponentNotReady(inputStream);
                break;
            case 8:
                parseIsPlayerToChooseCategory(inputStream);
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

    public void sendAnswer(ObjectOutputStream outputStream, String answer) throws IOException {
        outputStream.writeObject(new NetworkMessage(NetworkProtocolClient.PROTOCOL_SEND.SEND_ANSWER.ordinal()));
        outputStream.writeObject(answer);
        quizController.pGUI.setLastAnsweredQuestion(answer);
    }
    public void sendPlayerName(ObjectOutputStream outputStream, String name) throws IOException {
        outputStream.writeObject(new NetworkMessage(NetworkProtocolClient.PROTOCOL_SEND.SET_PLAYERNAME.ordinal()));
        outputStream.writeObject(name);
        quizController.pGUI.setNameLabels(name);
        //quizController.pGUI.player1NameLabel.setText(name);
    }


    //public static void sendPacket(ObjectOutputStream objectOutputStream, NetworkMessage networkMessage) throws IOException {
    //    objectOutputStream.writeObject(networkMessage);
    //}
    public void parsePlayerReady(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        System.out.println("Parse player ready: " + (boolean) lastReadObject);
        System.out.println("PLAYER READY B4 READ FROM SERVER: " + quizController.player.getReadyToStartGame());
        quizController.player.setReadyToStartGame((boolean) lastReadObject);
        System.out.println("PLAYER READY AFTER READ FROM SERVER: " + quizController.player.getReadyToStartGame());
    }

    public void parseGetCategory(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        quizController.pGUI.setCategoryName((String) lastReadObject);
        System.out.println("Category: " + (String) lastReadObject);

    }

    public void parseGetOpponentName(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        quizController.pGUI.setOpponentName((String) lastReadObject);
        System.out.println("UPDATE OPPONENT NAME LABEL: " + (String) lastReadObject);

    }

    public void parseChangeWindow(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        quizController.pGUI.changeWindow((String) lastReadObject);
    }

    public void parseScore(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        quizController.pGUI.setScorePlayer1((int) lastReadObject);
    }
    public void parseOpponentNotReady(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        quizController.player.setOpponentReady((boolean) lastReadObject);
        quizController.pGUI.categoryLabelPickCategory.setText("Waiting for other player");
    }
    public void parseSendQuestion(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        //Skickar från klient till server
        Object lastReadObject = inputStream.readObject();
        quizController.player.setCurrentQuestion((String) lastReadObject);
        quizController.pGUI.questionLabel.setText((String) lastReadObject);
        lastReadObject = inputStream.readObject();
        String[] alternatives = (String[]) lastReadObject;
        quizController.player.setCurrentAlternatives(alternatives);
        quizController.pGUI.answerButtons[0].setText(alternatives[0]);
        quizController.pGUI.answerButtons[1].setText(alternatives[1]);
        quizController.pGUI.answerButtons[2].setText(alternatives[2]);
        quizController.pGUI.answerButtons[3].setText(alternatives[3]);
    }

    public void parseAnswerResult(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        quizController.player.setCurrentAnsweredResult((boolean) lastReadObject);
        quizController.pGUI.changeAnsweredButtonColor((boolean) lastReadObject);
    }

    public void parseIsPlayerToChooseCategory(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        quizController.pGUI.changeCategoryWindowState((boolean) lastReadObject);
        System.out.println("Ändra CatWin till: " + (boolean)lastReadObject);
    }
}