package QuizClientSide;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class QuizGUI extends JFrame {

    //The different cards in CardLayout
    protected final String WELCOME = "0";
    protected final String CATEGORY = "1";
    protected final String PLAY = "2";
    protected final String SCORE = "3";
    String lastAnsweredQuestion;

    int screenX = 400;
    int screenY = 600;
    int imageIconSize = 20;
    int imageIconSize2 = 120;
    int imageIconSize3 = 80;
    Color panelsBackgroundColor = new Color(20, 80, 200);
    Font f = new Font(null, 3, 20);
    Font f2 = new Font(null, 3, 14);
    Dimension panelsDimension = new Dimension(screenX, screenY);

    int rounds = 1;
    int questionsPerRound = 6;
    int nrOfCategories = 4;
    int progressBarSekunder = 5;

    int scorePlayer1 = 0;
    int scorePlayer2 = 0;

    //Avatar images
    Icon avatar1 = new ImageIcon(new ImageIcon("Images/Avatar4.png").
            getImage().getScaledInstance(imageIconSize2, imageIconSize2, Image.SCALE_SMOOTH));
    Icon avatar2 = new ImageIcon(new ImageIcon("Images/Avatar8.png").
            getImage().getScaledInstance(imageIconSize2, imageIconSize2, Image.SCALE_SMOOTH));

    //Category Images
    Icon categorySpace = new ImageIcon(new ImageIcon("Images/Category_Space.png").
            getImage().getScaledInstance(imageIconSize3, imageIconSize3, Image.SCALE_SMOOTH));
    Icon categoryFood = new ImageIcon(new ImageIcon("Images/Category_Food.png").
            getImage().getScaledInstance(imageIconSize3, imageIconSize3, Image.SCALE_SMOOTH));
    Icon categoryGeography = new ImageIcon(new ImageIcon("Images/Category_Geography.png").
            getImage().getScaledInstance(imageIconSize3, imageIconSize3, Image.SCALE_SMOOTH));
    Icon categoryDeafult = new ImageIcon(new ImageIcon("Images/Category_Geography.png").
            getImage().getScaledInstance(imageIconSize3, imageIconSize3, Image.SCALE_SMOOTH));

    //Scoreoard Images
    ImageIcon emptyscore = new ImageIcon(new ImageIcon("Images/emptyAnswerWithBorder.png").
            getImage().getScaledInstance(imageIconSize, imageIconSize, Image.SCALE_SMOOTH));
    ImageIcon winIcon = new ImageIcon(new ImageIcon("Images/correctAnswerWithBorder.png").
            getImage().getScaledInstance(imageIconSize, imageIconSize, Image.SCALE_SMOOTH));
    ImageIcon loseIcon = new ImageIcon(new ImageIcon("Images/wrongAnswerWithBorder.png").
            getImage().getScaledInstance(imageIconSize, imageIconSize, Image.SCALE_SMOOTH));
    ImageIcon emptyscore2 = new ImageIcon(new ImageIcon("Images/emptyAnswerWithBorder.png").
            getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH));

    //CardLayout Panel
    JPanel mainQuizPanel = new JPanel();
    CardLayout cardLo = new CardLayout();

    //------------ Welcome Window ------------------------
    JPanel welcomePanelMain = new JPanel();
    JPanel welcomePanel = new JPanel();
    JPanel welcomeInputPanel = new JPanel();
    JLabel quizLabel = new JLabel("QUIZKAMPEN");
    JLabel nameText = new JLabel("Namn: ");
    JTextField welcomeInput = new JTextField(20);
    JButton welcomeStartButton = new JButton("Starta nytt spel");

    //------------ Category window ------------------------
    JPanel categoryMainPanel = new JPanel();
    JLabel categoryLabelPickCategory = new JLabel("Välj en kategori");
    JPanel categoryButtonPanel = new JPanel();
    JButton[] categoryButtons = {new JButton(""),new JButton(""),new JButton(""),new JButton(""),new JButton(), new JButton()};

    //------------ Play Window ---------------------------
    JPanel playerPanelMain = new JPanel();
    JPanel playerPanel = new JPanel();
    JPanel questionPanel = new JPanel();
    JPanel answerPanel = new JPanel();
    JPanel playerAvatarPanel1 = new JPanel();
    JPanel playerAvatarPanel2 = new JPanel();
    JPanel playerCategoryPanel = new JPanel();
    JLabel player1 = new JLabel(avatar1);
    JLabel player2 = new JLabel(avatar2);
    JLabel categoryLabel = new JLabel(categoryFood);
    JLabel player1NameLabel = new JLabel("Zorro");
    JLabel player2NameLabel = new JLabel("Dumbo");
    JLabel categoryNameLabel = new JLabel("Film");
    JPanel roundQuestionSpotsPanel1 = new JPanel();
    JPanel roundQuestionSpotsPanel2 = new JPanel();
    JLabel[] roundQuestionSpotsLabel1 = new JLabel[questionsPerRound];
    JLabel[] roundQuestionSpotsLabel2 = new JLabel[questionsPerRound];

    JLabel questionLabel = new JLabel("", SwingConstants.CENTER);
    JButton[] answerButtons = {new JButton(), new JButton(), new JButton(), new JButton()};
    JProgressBar progressBar = new JProgressBar(SwingConstants.HORIZONTAL);
    LayoutManager answerLayout = new GridLayout(2, 2);
    Color answButtonColor = new Color(20, 40, 100);

    //------------Scoreboard Window------------------------
    String playerName1;
    String playerName2;
    String whoTurn;

    JPanel[][] roundPanel = new JPanel[3][rounds];
    JLabel[][] plupparLabel;
    JPanel scoreboardMainPanel = new JPanel();
    JPanel player1Panel;
    JPanel player2Panel;
    //--Adding extra panel to show categories--
    JPanel scoreCategoryPanel = new JPanel();
    JLabel[] scoreboardCategoryLabels;
    //-----------------------------------------
    String scoreBoardStartButtonText = "Starta ny runda";
    JButton scoreBoardStartButton = new JButton(scoreBoardStartButtonText);
    JPanel borderPanel = new JPanel(new BorderLayout());
    JLabel whoTurnLabel = new JLabel(whoTurn);
    JLabel scoreLabel = new JLabel(scorePlayer1 + " - " + scorePlayer2);
    JLabel[] nameLabel = new JLabel[3];
    JLabel[] playerIconLabel = new JLabel[3];


    public QuizGUI(){
        setTitle("QUIZKAMPEN");
        //Nytt för CardLayout
        mainQuizPanel.setLayout(cardLo);
        mainQuizPanel.setPreferredSize(panelsDimension);

        mainQuizPanel.add(welcomePanelMain, WELCOME);
        mainQuizPanel.add(playerPanelMain, PLAY);
        mainQuizPanel.add(categoryMainPanel, CATEGORY);
        mainQuizPanel.add(scoreboardMainPanel, SCORE);
        cardLo.show(mainQuizPanel, WELCOME);
        add(mainQuizPanel);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void initWelcomeWindow() {

        welcomePanelMain.setPreferredSize(panelsDimension);
        welcomePanelMain.setBackground(panelsBackgroundColor);
        welcomePanel.setPreferredSize(new Dimension((int) (screenX * 0.8), (int) (screenY * 0.8)));
        welcomePanel.setLayout(new BorderLayout());
        welcomePanel.setBackground(Color.orange);
        welcomePanel.add(quizLabel, BorderLayout.NORTH);
        welcomeInputPanel.setLayout(new FlowLayout());
        welcomeInputPanel.add(nameText);
        welcomeInputPanel.add(welcomeInput);
        welcomeInputPanel.setPreferredSize(new Dimension((int) (screenX * 0.5), (int) (screenY * 0.2)));
        Color c = new Color(210, 170, 30);
        welcomeInputPanel.setBackground(c);
        welcomePanel.add(welcomeInputPanel, BorderLayout.CENTER);

        welcomePanel.add(welcomeStartButton, BorderLayout.SOUTH);

        quizLabel.setHorizontalAlignment(JLabel.CENTER);
        quizLabel.setFont(f);
        nameText.setFont(f);
        welcomeStartButton.setFont(f);
        welcomePanelMain.add(welcomePanel);

    }

    public void initPlayWindow() {
        setPlayerPanelProperties();
        setQuestionPanelProperties();
        setAnswerPanelProperties();
        playerPanelMain.setLayout(new BoxLayout(playerPanelMain, BoxLayout.PAGE_AXIS));
        playerPanelMain.setPreferredSize(panelsDimension);
        playerPanelMain.setBackground(Color.orange);
        playerPanelMain.add(playerPanel);
        playerPanelMain.add(questionPanel);
        playerPanelMain.add(progressBar);
        playerPanelMain.add(answerPanel);

    }

    public void setPlayerPanelProperties() {
        roundQuestionSpotsLabel1 = new JLabel[questionsPerRound];
        roundQuestionSpotsLabel2 = new JLabel[questionsPerRound];
        playerPanel.setPreferredSize(new Dimension(340, 180));
        playerPanel.setBackground(panelsBackgroundColor);
        playerAvatarPanel1.setBackground(panelsBackgroundColor);
        playerAvatarPanel2.setBackground(panelsBackgroundColor);
        playerCategoryPanel.setBackground(panelsBackgroundColor);
        playerPanel.setBackground(panelsBackgroundColor);
        player1NameLabel.setFont(f2);
        player2NameLabel.setFont(f2);
        categoryNameLabel.setFont(f2);
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.X_AXIS));

        playerAvatarPanel1.setLayout(new BoxLayout(playerAvatarPanel1, BoxLayout.Y_AXIS));
        playerAvatarPanel2.setLayout(new BoxLayout(playerAvatarPanel2, BoxLayout.Y_AXIS));
        playerCategoryPanel.setLayout(new BoxLayout(playerCategoryPanel, BoxLayout.Y_AXIS));
        categoryNameLabel.setAlignmentX(0.5f);
        categoryLabel.setAlignmentX(0.5f);

        player1.setAlignmentX(0.5f);
        player1NameLabel.setAlignmentX(0.5f);
        playerAvatarPanel1.add(Box.createRigidArea(new Dimension(0, 10)));
        playerAvatarPanel1.add(player1);
        playerAvatarPanel1.add(player1NameLabel);
        playerAvatarPanel1.add(roundQuestionSpotsPanel1);

        playerCategoryPanel.add(categoryLabel);

        playerCategoryPanel.add(categoryNameLabel);

        player2.setAlignmentX(0.5f);
        player2NameLabel.setAlignmentX(0.5f);
        playerAvatarPanel2.add(Box.createRigidArea(new Dimension(50, 10)));
        playerAvatarPanel2.add(player2);
        playerAvatarPanel2.add(player2NameLabel);
        playerAvatarPanel2.add(roundQuestionSpotsPanel2);

        playerPanel.add(playerAvatarPanel1, BorderLayout.WEST);
        playerPanel.add(playerCategoryPanel, BorderLayout.CENTER);
        playerPanel.add(playerAvatarPanel2, BorderLayout.EAST);

        roundQuestionSpotsPanel1.setPreferredSize(new Dimension(90, 20));

        for (int i = 0; i < roundQuestionSpotsLabel1.length; i++) {

            roundQuestionSpotsLabel1[i] = new JLabel();
            roundQuestionSpotsLabel2[i] = new JLabel();
            roundQuestionSpotsLabel1[i].setIcon(emptyscore2);
            roundQuestionSpotsLabel2[i].setIcon(emptyscore2);
            roundQuestionSpotsPanel1.add(roundQuestionSpotsLabel1[i]);
            roundQuestionSpotsPanel2.add(roundQuestionSpotsLabel2[i]);

        }
        roundQuestionSpotsPanel1.setBackground(panelsBackgroundColor);
        roundQuestionSpotsPanel2.setBackground(panelsBackgroundColor);


    }
    public void setQuestionPanelProperties() {
        String questionText = "Fråga?";
        questionPanel.setPreferredSize(new Dimension(screenX, screenY / 2));
        questionPanel.setBackground(panelsBackgroundColor);
        questionLabel.setPreferredSize(new Dimension(screenX, screenY / 3));
        questionLabel.setBackground(Color.ORANGE);
        questionLabel.setText(questionText);
        questionLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        questionLabel.setVerticalTextPosition(SwingConstants.CENTER);
        questionLabel.setOpaque(true);
        questionLabel.setFont(f);
        progressBar.setMinimum(0);
        //progressBar.setMaximum(20 * 400);
        progressBar.setMaximum(progressBarSekunder*1000);
        progressBar.setPreferredSize(new Dimension(screenX, 16));
        progressBar.setForeground(Color.RED);
        progressBar.setBackground(Color.GRAY);
        questionPanel.add(questionLabel);
        questionPanel.add(Box.createVerticalStrut(10));
    }

    public void setAnswerPanelProperties() {
        String answerText = "Svar";
        answerPanel.setPreferredSize(new Dimension(screenX / 2, screenX / 2 + screenY / 10));
        answerPanel.setLayout(answerLayout);
        SoftBevelBorder border = new SoftBevelBorder(SoftBevelBorder.RAISED);

        for (JButton jb : answerButtons) {
            jb.setBackground(answButtonColor);
            jb.setOpaque(true);
            jb.setFont(f);
            jb.setText(answerText);
            jb.setLayout(new BorderLayout());
            jb.setHorizontalTextPosition(JLabel.CENTER);
            jb.setVerticalTextPosition(JLabel.CENTER);
            jb.setBackground(answButtonColor);
            jb.setForeground(Color.WHITE);
            jb.setBorder(border);
            jb.setFocusPainted(false);
            answerPanel.add(jb);
        }
    }
    //---------------- Category window ---------------------------------
    public void initCategoryWindow() {
        //Change
        categoryMainPanel.setPreferredSize(panelsDimension);
        categoryMainPanel.setBackground(panelsBackgroundColor);
        categoryLabelPickCategory.setBackground(panelsBackgroundColor);
        categoryButtonPanel.setBackground(panelsBackgroundColor);
        //MAIN FRAME

        // MAIN PANEL
        categoryMainPanel.setLayout(new BoxLayout(categoryMainPanel, BoxLayout.PAGE_AXIS));

        // PICK A CATEGORY LABEL
        categoryLabelPickCategory.setOpaque(true);
        categoryLabelPickCategory.setFont(f);
        categoryLabelPickCategory.setAlignmentX(0.5f);

        // BUTTONS
        categoryButtonPanel.setLayout(new BoxLayout(categoryButtonPanel, BoxLayout.Y_AXIS));
        for (int i = 0; i < categoryButtons.length; i++) {
            categoryButtons[i].setBackground(answButtonColor);
            categoryButtons[i].setOpaque(true);
            categoryButtons[i].setFont(f);
            categoryButtons[i].setLayout(new BorderLayout());
            categoryButtons[i].setBackground(answButtonColor);
            categoryButtons[i].setForeground(Color.WHITE);
            categoryButtons[i].setFocusPainted(false);
            categoryButtons[i].setAlignmentX(0.5f);
            categoryButtons[i].setVisible(false);
            categoryButtonPanel.add(categoryButtons[i].getText(), categoryButtons[i]);
            categoryButtonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        categoryMainPanel.add(Box.createRigidArea(new Dimension(0, 150)));
        categoryMainPanel.add(categoryLabelPickCategory);
        categoryMainPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        categoryMainPanel.add(categoryButtonPanel);
    }

    //---------- Scoreboard --------------------------
    public void initScoreboardWindow() {
        roundPanel = new JPanel[3][rounds];
        plupparLabel = new JLabel[3][rounds * questionsPerRound];

        scoreboardMainPanel.setPreferredSize(panelsDimension);
        borderPanel.setPreferredSize(panelsDimension);

        borderPanel.setBackground(panelsBackgroundColor);

        whoTurnLabel.setFont(f);
        whoTurnLabel.setHorizontalAlignment(JLabel.CENTER);
        borderPanel.add(whoTurnLabel, BorderLayout.NORTH);

        //----FONT

        // Score display
        scoreLabel.setFont(f);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        //Changing to show categories

        scoreCategoryPanel = createScoreCategoryPanel();
        borderPanel.add(scoreCategoryPanel, BorderLayout.CENTER);

        //Player Panels---------
        player1Panel = createPlayerPanel(1);
        player2Panel = createPlayerPanel(2);
        borderPanel.add(player1Panel, BorderLayout.WEST);
        borderPanel.add(player2Panel, BorderLayout.EAST);


        // Start button
        scoreBoardStartButton.setBackground(Color.GREEN);
        scoreBoardStartButton.setOpaque(true);
        scoreBoardStartButton.setContentAreaFilled(true);
        scoreBoardStartButton.setFont(f);
        scoreBoardStartButton.setPreferredSize(new Dimension(400, 40));
        borderPanel.add(scoreBoardStartButton, BorderLayout.SOUTH);
        scoreboardMainPanel.add(borderPanel);
    }

    public JPanel createScoreCategoryPanel(){
        JPanel scoreCatPanel = new JPanel();
        scoreCatPanel.add(scoreLabel);
        scoreCatPanel.setLayout(new BoxLayout(scoreCatPanel, BoxLayout.Y_AXIS));
        scoreCatPanel.setBackground(panelsBackgroundColor);
        scoreboardCategoryLabels = new JLabel[nrOfCategories];
        for(int i = 0; i<nrOfCategories; i++){
            scoreboardCategoryLabels[i] = new JLabel(categoryGeography);
            scoreCatPanel.add(scoreboardCategoryLabels[i]);
        }
        return scoreCatPanel;
    }

    public JPanel createPlayerPanel(int player) {
        String name;
        Icon playerIcon;
        if (player == 1) {
            name = playerName1;
            playerIcon = avatar1;
        } else {
            name = playerName2;
            playerIcon = avatar2;
        }

        //
        JPanel playerNamePanel = new JPanel();
        playerNamePanel.setLayout(new BoxLayout(playerNamePanel, BoxLayout.Y_AXIS));
        playerNamePanel.setBackground(panelsBackgroundColor);

        nameLabel[player] = new JLabel(name);
        JLabel roundScore = new JLabel("     ");
        playerIconLabel[player] = new JLabel(playerIcon);
        playerIconLabel[player].setAlignmentX(0.5f);
        nameLabel[player].setAlignmentX(0.5f);

        nameLabel[player].setFont(f2);

        playerNamePanel.add(playerIconLabel[player]);
        playerNamePanel.add(nameLabel[player]);
        playerNamePanel.add(Box.createHorizontalGlue());  // Pushes the two labels apart
        playerNamePanel.add(roundScore);
        playerNamePanel.add(Box.createHorizontalGlue());

        JPanel scorePanel = addScorePanel(player);
        playerNamePanel.add(scorePanel);

        return playerNamePanel;
    }

    public JPanel addScorePanel(int player) {
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setBackground(panelsBackgroundColor);

        int count = 0;
        for (int i = 0; i < rounds; i++) {
            this.roundPanel[player][i] = new JPanel(new FlowLayout());
            this.roundPanel[player][i].setBackground(panelsBackgroundColor);

            for (int j = 0; j < questionsPerRound; j++) {

                this.plupparLabel[player][count] = new JLabel(emptyscore);
                this.roundPanel[player][i].add(plupparLabel[player][count]);

                count++;
            }
            scorePanel.add(this.roundPanel[player][i]);
        }
        return scorePanel;
    }

    //------------ Button Listeners -------------------------------------------
    void addButtonListener(ActionListener aListener) {
        for (JButton jB : answerButtons) {
            jB.addActionListener(aListener);
        }
        welcomeStartButton.addActionListener(aListener);
        scoreBoardStartButton.addActionListener(aListener);
    }

    public void initCategoryButtonListener(ActionListener aListener) {
        for (JButton jB : categoryButtons) {
            jB.addActionListener(aListener);
        }
    }
    //-------------- GUI Methods ----------------------------------------------

    //CATEGORY

    public void changeAnsweredButtonColor(boolean correctAnswer, int buttonIndex) {

        if (correctAnswer) {
            answerButtons[buttonIndex].setBackground(Color.GREEN);

        } else {
            answerButtons[buttonIndex].setBackground(Color.RED);

        }
        for (JButton jb : answerButtons) {
            jb.setEnabled(false);
        }
    }

    public void changeAnsweredButtonReset(int buttonIndex) {
        for (JButton jb : answerButtons) {
            jb.setEnabled(true);
            jb.setBackground(answButtonColor);
        }
    }


    public void setCategoryButtonText(String[] textArray) {
        for (int i = 0; i < textArray.length; i++) {
            categoryButtons[i].setText(textArray[i]);
        }
    }

    public void setCategoryIcon(String category) {
        if (category.equals("Mat")) {
            categoryLabel.setIcon(categoryFood);
        }
        if (category.equals("Sci-fi")) {
            categoryLabel.setIcon(categorySpace);
        }
        if (category.equals("Geografi")) {
            categoryLabel.setIcon(categoryGeography);
        }
        if (category.equals("Rymden")) {
            categoryLabel.setIcon(categorySpace);
        }
    }

    //GAME
    public void setCategoryNameLabel(String category) {
        categoryNameLabel.setText(category);
    }


    //SCOREBOARD
    public void setScoreBoard(int player, int roundNr, int questionNr, boolean correctAnswer) {
        int index = questionNr + (roundNr * questionsPerRound);
        if (correctAnswer) {
            plupparLabel[player][index].setIcon(this.winIcon);
        } else if (correctAnswer == false) {
            plupparLabel[player][index].setIcon(this.loseIcon);
        }
    }

    public void setCurrentScoreBoard(int questionNr, boolean correctAnswer) {
        if (correctAnswer) {
            roundQuestionSpotsLabel1[questionNr].setIcon(this.winIcon); //buggfix:
        } else {
            roundQuestionSpotsLabel1[questionNr].setIcon(this.loseIcon);
        }
    }

    public void resetCurrentScoreBoard() {
        for (int i = 0; i < roundQuestionSpotsLabel1.length; i++) {
            roundQuestionSpotsLabel1[i].setIcon(emptyscore2);
            roundQuestionSpotsLabel2[i].setIcon(emptyscore2);
        }
    }
    //General
    public void changeWindow(String category) {
        cardLo.show(mainQuizPanel, category);
    }
    public void setNameLabels(String name1) {
        player1NameLabel.setText(" "+name1+" ");
        nameLabel[1].setText(name1);
    }
    public void setOpponentName(String catName) {
        player2NameLabel.setText(" "+catName+" ");
        nameLabel[2].setText(catName);
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }
    public void setScorePlayer1(int scorePlayer1) {
        this.scorePlayer1 = scorePlayer1;
        scoreLabel.setText(scorePlayer1 + " - " + getScorePlayer2());
    }
    public int getScorePlayer2() {
        return scorePlayer2;
    }
    public void setScorePlayer2(int scorePlayer2) {
        this.scorePlayer2 = scorePlayer2;
        scoreLabel.setText(getScorePlayer1() + " - " + scorePlayer2);
    }
    public void setLastAnsweredQuestion(String lastAnsweredQuestion) {
        this.lastAnsweredQuestion = lastAnsweredQuestion;
    }

    public String getScoreBoardStartButtonText() {
        return scoreBoardStartButtonText;
    }

    public void changeCategoryWindowState(boolean toChooseCategory) {

        if (toChooseCategory) {
            categoryLabelPickCategory.setText("Välj Kategori");

            for (int i = 0; i < nrOfCategories; i++) {
                categoryButtons[i].setVisible(true);
            }
        } else {
            categoryLabelPickCategory.setText("Motståndaren väljer kategori");
            for (int i = 0; i < categoryButtons.length; i++) {
                categoryButtons[i].setVisible(false);
            }
        }
    }

    public void setNrOfRounds(int rounds) {
        this.rounds = rounds;
    }

    public void setNrOfQuestionsPerRound(int questionsPerRound) {
        this.questionsPerRound = questionsPerRound;
    }

    public void setNrOfCategories(int nrOfCategories) {

        this.nrOfCategories = nrOfCategories;
    }

    public void setProgressBarSekunder(int sekunder){
        this.progressBarSekunder = sekunder;
    }

}