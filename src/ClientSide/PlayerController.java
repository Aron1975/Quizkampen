package ClientSide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerController extends Thread{

    Player player;
    PlayerGUI pGUI;
    Client client;
    //Thread t = new Thread();
    String messageFromServer;

    public PlayerController(Player player, PlayerGUI pGUI, Client client) {
        this.player = player;
        this.pGUI = pGUI;
        this.client = client;
        this.pGUI.addButtonListener(new MyButtonListener());
       // t.start();
    }

    public PlayerController(Player player, PlayerGUI pGUI) {
        this.player = player;
        this.pGUI = pGUI;
        this.pGUI.addButtonListener(new MyButtonListener());
        //t.start();
    }

    class MyButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == pGUI.answers[0]){
                System.out.println("Alt.1 Pushed");
                messageFromServer=client.sendMessage("Alt.1 Pushed");
            }
            if(e.getSource() == pGUI.answers[1]){
                System.out.println("Alt.2 Pushed");
                messageFromServer=client.sendMessage("Alt.2 Pushed");
            }
            if(e.getSource() == pGUI.answers[2]){
                System.out.println("Alt.3 Pushed");
                messageFromServer=client.sendMessage("Alt.3 Pushed");
            }
            if(e.getSource() == pGUI.answers[3]){
                System.out.println("Alt.4 Pushed");
                messageFromServer=client.sendMessage("Alt.4 Pushed");
            }
            pGUI.progressBar.setValue(pGUI.progressBar.getValue()+1);
            pGUI.setQuestionLabelText(messageFromServer);
        }
    }

    @Override
    public void run(){
        String dataFromServer;
        System.out.println("I Controller run()...");
        dataFromServer = client.readMessage();
        pGUI.setQuestionLabelText(dataFromServer);
    }

    public static void main(String[] args) {
        Player p = new Player("Aron", 0);
        PlayerGUI pGUI = new PlayerGUI();
        Client c = new Client();
        new PlayerController(p,pGUI, c);
    }
}
