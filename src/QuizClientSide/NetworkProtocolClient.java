package QuizClientSide;

import QuizServerSide.NetworkMessage;
import QuizServerSide.NetworkProtocolServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Timer;

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
        CHOSEN_CATEGORY,
        PLAYER_READY,

    }

    public void parsePacket(ObjectInputStream inputStream, NetworkMessage networkMessage) throws IOException, ClassNotFoundException {
        int networkCode = networkMessage.getNetworkCode();

        switch (networkCode) {
            case 0:
                parsePlayerReady(inputStream);
                break;
            case 1:
                parseGetCategories(inputStream);
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
                parseGetChosenCategory(inputStream);
                break;
            case 10:
                parseButtonResetColor(inputStream);
                break;
            case 11:
                parseUpdateWinnerLabel(inputStream);
                break;
            case 12:
                parseResetStartNewRoundButton();
                break;
            case 13:
                parseDisableStartNewRoundButton();
                break;
            case 14:
                parseCorrectAnswerIndex(inputStream);
                break;
            case 15:
                break;
            case 16:
                break;
        }
    }

    public void sendAnswer(ObjectOutputStream outputStream, String answer, int buttonIndex) throws IOException {
        outputStream.writeObject(new NetworkMessage(NetworkProtocolClient.PROTOCOL_SEND.SEND_ANSWER.ordinal()));
        outputStream.writeObject(answer);
        outputStream.writeObject(buttonIndex);
        quizController.pGUI.setLastAnsweredQuestion(answer);
        System.out.println("Send answer to server");
    }

    public void sendPlayerName(ObjectOutputStream outputStream, String name) throws IOException {
        outputStream.writeObject(new NetworkMessage(NetworkProtocolClient.PROTOCOL_SEND.SET_PLAYERNAME.ordinal()));
        outputStream.writeObject(name);
        quizController.pGUI.setNameLabels(name);

        //quizController.pGUI.player1NameLabel.setText(name);
    }

    public void sendChosenCategory(ObjectOutputStream outputStream, String name) throws IOException {
        outputStream.writeObject(new NetworkMessage(NetworkProtocolClient.PROTOCOL_SEND.CHOSEN_CATEGORY.ordinal()));
        outputStream.writeObject(name);
        quizController.pGUI.setCategoryNameLabel(name);
    }

    public void sendPlayerReady(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeObject(new NetworkMessage(NetworkProtocolClient.PROTOCOL_SEND.PLAYER_READY.ordinal()));
        outputStream.writeObject((boolean)true);
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

    public void parseGetCategories(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        String[] categories = (String[]) lastReadObject;
        quizController.pGUI.setCategoryButtonText(categories);
        //System.out.println("Categories: " + categories[0] + " " + categories[1] + " " + categories[2]);
        quizController.pGUI.resetCurrentScoreBoard();//Nollställ plupparna vid ny questionrunda
    }

    public void parseGetOpponentName(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        quizController.pGUI.setOpponentName((String) lastReadObject);
        System.out.println("UPDATE OPPONENT NAME LABEL: " + (String) lastReadObject);

    }

    public void parseChangeWindow(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        quizController.pGUI.changeWindow((String) lastReadObject);
        System.out.println("CHANGE WINDOW: " + (String) lastReadObject);
    }

    public void parseScore(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        quizController.pGUI.setScorePlayer1((int) lastReadObject);
        System.out.println("SCORE: " + (int) lastReadObject);
    }

    public void parseOpponentNotReady(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        quizController.player.setOpponentReady((boolean) lastReadObject);
        quizController.pGUI.categoryLabelPickCategory.setText("Waiting for other player");
        System.out.println("OPPONENT NOT READY: " + (boolean) lastReadObject);
    }

    public void parseSendQuestion(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        //Skickar från klient till server
        Object lastReadObject = (String) inputStream.readObject();
        String str = (String) lastReadObject;
        quizController.player.setCurrentQuestion("<html><center>" + str + "</center></html>");
        quizController.pGUI.questionLabel.setText("<html><center>" + str + "</center></html>");
        System.out.println("Sending questions parse" + (String) lastReadObject);
        lastReadObject = inputStream.readObject();
        String[] alternatives = (String[]) lastReadObject;
        quizController.player.setCurrentAlternatives(alternatives);
        quizController.pGUI.answerButtons[0].setText(alternatives[0]);
        quizController.pGUI.answerButtons[1].setText(alternatives[1]);
        quizController.pGUI.answerButtons[2].setText(alternatives[2]);
        quizController.pGUI.answerButtons[3].setText(alternatives[3]);

        if(quizController.timer != null)
        {
            quizController.timer.getTimer().stop();
        }
        quizController.timer = new AnswerTimer(quizController);
        System.out.println("R: " + quizController.player.getRoundNr() + " Q: " + quizController.player.getQuestionNr());
        quizController.player.roundAndQuestionCounter(); // Updatera Player current round and question
        System.out.println("R: " + quizController.player.getRoundNr() + " Q: " + quizController.player.getQuestionNr());
    }

    public void parseAnswerResult(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        boolean result = ((boolean) lastReadObject);
        int buttonIndex = (int) inputStream.readObject();
        quizController.player.setCurrentAnsweredResult(result);
        if (buttonIndex != 4) {
            quizController.pGUI.changeAnsweredButtonColor(result, buttonIndex);
        }  // Uppdater score för spelaren i frågeomgången
        quizController.pGUI.setCurrentScoreBoard(quizController.player.getQuestionNr(), result);
        quizController.player.setAnswerResult(quizController.player.getRoundNr(),quizController.player.getQuestionNr(), result);

    }

    public void parseIsPlayerToChooseCategory(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        quizController.pGUI.changeCategoryWindowState((boolean) lastReadObject);
        System.out.println("Ändra CatWin till: " + (boolean)lastReadObject);
    }

    public void parseGetChosenCategory(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        quizController.pGUI.setCategoryNameLabel((String) lastReadObject);
        System.out.println("Receive chosen Category: " + (String) lastReadObject);
    }

    public void parseButtonResetColor(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        quizController.pGUI.changeAnsweredButtonReset((int) lastReadObject);
    }

    public void parseUpdateWinnerLabel(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        if ((boolean) lastReadObject) {
            quizController.pGUI.whoTurnLabel.setText(quizController.pGUI.player2NameLabel.getText() + " disconnected");
        }
    }
    public void parseResetStartNewRoundButton() throws IOException, ClassNotFoundException {
        quizController.pGUI.scoreBoardStartButton.setText("Starta");
    }
    public void parseDisableStartNewRoundButton() throws IOException, ClassNotFoundException {
        quizController.pGUI.scoreBoardStartButton.setVisible(false);
    }
    public void parseCorrectAnswerIndex(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
        quizController.pGUI.changeAnsweredButtonColor(true, (int) lastReadObject);
    }
    public void parseOpponentScoresArray(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        Object lastReadObject = inputStream.readObject();
    }
}