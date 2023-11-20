package QuizClientSide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizController extends Thread{

    QuizPlayer player;
    QuizGUI pGUI;
    QuizClient client;
    //Thread t = new Thread();
    String messageFromServer;
    String[] messageArrayFromServer;

    public QuizController(QuizPlayer player, QuizGUI pGUI, QuizClient client) {
        this.player = player;
        this.pGUI = pGUI;
        this.client = client;
        this.pGUI.addButtonListener(new MyButtonListener());
        this.pGUI.initCategoryButtonListener(new CategoryButtonListener());
       // t.start();
        startGame();
    }

    public QuizController(QuizPlayer player, QuizGUI pGUI) {
        this.player = player;
        this.pGUI = pGUI;
        this.pGUI.addButtonListener(new MyButtonListener());
        this.pGUI.initCategoryButtonListener(new CategoryButtonListener());
        //t.start();
    }

    public void startGame(){
        getQuestionFromServer();

    }

    public void getQuestionFromServer(){
        messageFromServer=client.sendAndGetMessage(null);
        pGUI.setQuestionLabelText(messageFromServer);
        messageArrayFromServer=client.sendAndGetArrayMessage(null);
        pGUI.setAnswerButtonText(messageArrayFromServer);
    }


    class MyButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == pGUI.answerButtons[0]){

                messageFromServer=client.sendAndGetMessage(pGUI.getButtonText(0));
                System.out.println(pGUI.getButtonText(0));
            }
            if(e.getSource() == pGUI.answerButtons[1]){
                System.out.println("Alt.2 Pushed");
                messageFromServer=client.sendAndGetMessage(pGUI.getButtonText(1));
            }
            if(e.getSource() == pGUI.answerButtons[2]){
                System.out.println("Alt.3 Pushed");
                messageFromServer=client.sendAndGetMessage(pGUI.getButtonText(2));
            }
            if(e.getSource() == pGUI.answerButtons[3]){
                System.out.println("Alt.4 Pushed");
                messageFromServer=client.sendAndGetMessage(pGUI.getButtonText(3));
            }
        }
    }
class CategoryButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Pick category window
            if(e.getSource() == pGUI.categoryButtons[0]){
                System.out.println("Chose category 1");
            }
            else if(e.getSource() == pGUI.categoryButtons[1]){
                System.out.println("Chose category 2");
            }
            else if(e.getSource() == pGUI.categoryButtons[2]){
                System.out.println("Chose category 3");
            }

        }
    }

 /*   @Override
    public void run(){
        String dataFromServer;
        System.out.println("I Controller run()...");
        dataFromServer = client.readMessage();
        pGUI.setQuestionLabelText(dataFromServer);
    }*/

    public static void main(String[] args) {
        QuizPlayer p = new QuizPlayer("Aron", 0);
        QuizGUI pGUI = new QuizGUI();
        QuizClient c = new QuizClient();
        new QuizController(p,pGUI, c);
    }
}
