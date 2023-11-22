package QuizClientSide;

import javax.swing.*;
import java.awt.*;

public class QuizGUI_Scoreboard {
    String playerName1 = "Scaramanga";
    String playerName2 = "James Bond";
    ImageIcon player1Icon = new ImageIcon("Images/Avatar_1n.jpg");
    ImageIcon player2Icon = new ImageIcon("Images/Avatar_2n.jpg");
    Font f = new Font(null, 3, 20);

    static int rounds = 15;
    int questionsPerRound = 3;
    static int currentRound = 3;
    int scorePlayer1 = 0;

    int scorePlayer2 = 5;
    int roundPointsPlayer1 = 2;
    int roundPointsPlayer2 = 3;
    String whoTurn = "Your turn";

    JPanel[] roundPanel = new JPanel[rounds];
    JLabel[] winLabel = new JLabel[rounds];
    JLabel[] loseLabel = new JLabel[rounds];
    JLabel[][] plupparLabel = new JLabel[3][rounds * questionsPerRound];


    //--------------
    ImageIcon winIcon = new ImageIcon(new ImageIcon("Images/rightanswer.jpg").
            getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));
    ImageIcon loseIcon = new ImageIcon(new ImageIcon("Images/wronganswer.jpg").
            getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));

    QuizGUI_Scoreboard() {
        //SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quizkampen");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 600);
            frame.setBackground(Color.ORANGE);
            JPanel borderPanel = new JPanel(new BorderLayout());
            //borderPanel.setPreferredSize(new Dimension(400,600));
            borderPanel.setBackground(Color.ORANGE);
            JLabel whoTurnLabel = new JLabel(whoTurn);
            whoTurnLabel.setFont(f);
            whoTurnLabel.setHorizontalAlignment(JLabel.CENTER);
            borderPanel.add(whoTurnLabel, BorderLayout.NORTH);

            // Score display
            JLabel scoreLabel = new JLabel(scorePlayer1 + " - " + scorePlayer2);
            scoreLabel.setFont(f);
            scoreLabel.setHorizontalAlignment(JLabel.CENTER);
            borderPanel.add(scoreLabel, BorderLayout.CENTER);


            // Player profiles
            JPanel player1Panel = createPlayerPanel(playerName1, roundPointsPlayer1, player1Icon, 1);
            JPanel player2Panel = createPlayerPanel(playerName2, roundPointsPlayer2, player2Icon, 2);
            borderPanel.add(player1Panel, BorderLayout.WEST);
            borderPanel.add(player2Panel, BorderLayout.EAST);



            // Kör button
            JButton button = new JButton("Starta");
            button.setBackground(Color.GREEN);
            button.setOpaque(true);
            button.setContentAreaFilled(true);
            borderPanel.add(button, BorderLayout.SOUTH);
            // Add more components as needed

            frame.getContentPane().add(borderPanel, BorderLayout.NORTH);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);


        //});
    }

    public JPanel createPlayerPanel(String name, int roundPointsPlayer, ImageIcon playerIcon, int player) {
        JPanel playerNamePanel = new JPanel();
        playerNamePanel.setLayout(new BoxLayout(playerNamePanel, BoxLayout.Y_AXIS));
        playerNamePanel.setBackground(Color.ORANGE);


        //ImageIcon win = new ImageIcon("Images/check.png");
        ImageIcon win = new ImageIcon(new ImageIcon("Images/rightanswer.jpg").
                getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // -------
        //ImageIcon lose = new ImageIcon("Images/delete.png");
        ImageIcon lose = new ImageIcon(new ImageIcon("Images/emptyscore2.jpg").
                getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)); // ----------
        ImageIcon emptyscore = new ImageIcon(new ImageIcon("Images/emptyscore2.jpg").
                getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
        ImageIcon star = new ImageIcon("Images/star.png");

        JLabel nameLabel = new JLabel(" " + name);
        //JLabel starLabel = new JLabel(star);
        JLabel roundScore = new JLabel("   " + (Integer.toString(roundPointsPlayer)));
        JLabel playerIconLabel = new JLabel(playerIcon);

        playerNamePanel.add(playerIconLabel);
        playerNamePanel.add(nameLabel);
        playerNamePanel.add(Box.createHorizontalGlue());  // Pushes the two labels apart
        //playerNamePanel.add(starLabel);
        playerNamePanel.add(roundScore);
        playerNamePanel.add(Box.createHorizontalGlue());//--------

        //JPanel scorePanel = addScorePanel(rounds, win, lose);
        JPanel scorePanel = addScorePanel2(rounds, emptyscore, player);
        playerNamePanel.add(scorePanel);


        return playerNamePanel;
    }

    public JPanel addScorePanel(int rounds, ImageIcon win, ImageIcon lose) {
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setBackground(Color.orange);

        for (int i = 0; i < rounds; i++) {
            roundPanel[i] = new JPanel(new FlowLayout());
            roundPanel[i].setBackground(Color.orange);

            winLabel[i] = new JLabel(win);
            loseLabel[i] = new JLabel(lose);


            roundPanel[i].add(winLabel[i]);
            roundPanel[i].add(loseLabel[i]);
            scorePanel.add(roundPanel[i]);
        }

        return scorePanel;
    }

    public JPanel addScorePanel2(int rounds, ImageIcon empty, int player) {
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setBackground(Color.orange);

        int count = 0;
        for (int i = 0; i < rounds; i++) {
            this.roundPanel[i] = new JPanel(new FlowLayout());
            this.roundPanel[i].setBackground(Color.orange);

            for (int j = 0; j < questionsPerRound; j++) {

                this.plupparLabel[player][count] = new JLabel(empty);
                this.roundPanel[i].add(plupparLabel[player][count]);

                count++;
            }
            scorePanel.add(this.roundPanel[i]);
        }

        return scorePanel;
    }

    public void showWinner(int round, boolean player1Won) {
        if (player1Won) {
            winLabel[round].setVisible(true);
            loseLabel[round].setVisible(false);
        } else {
            loseLabel[round].setVisible(true);
            winLabel[round].setVisible(false);
        }
    }

    public void setScoreBoard(int player, int questionNr, boolean correctAnswer){
        if(correctAnswer) {
            plupparLabel[player][questionNr].setIcon(this.winIcon);
        }else{
            plupparLabel[player][questionNr].setIcon(this.loseIcon);
        }
    }

    public static void main(String[] args) {
        QuizGUI_Scoreboard qs = new QuizGUI_Scoreboard();
       // qs.setScoreBoard(1,4,true);
       // qs.setScoreBoard(2,6,true);
        qs.setScoreBoard(2,0,false);
        qs.setScoreBoard(2,4,false);
    }
}