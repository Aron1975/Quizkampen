package QuizClientSide;

public class QuizPlayer {

    private String name;
    private int score;
    private int partScore;
    private boolean readyToStartGame = false;
    String currentQuestion;
    String[] currentAlternatives;
    boolean currentAnsweredResult;
    boolean opponentReady;
    QuizPlayer opponent;

    public QuizPlayer(){}

    public QuizPlayer(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPartScore() {
        return partScore;
    }

    public void setPartScore(int partScore) {
        this.partScore = partScore;
    }

    public boolean getReadyToStartGame() {
        return readyToStartGame;
    }

    public void setReadyToStartGame(boolean readyToStartGame) {
        this.readyToStartGame = readyToStartGame;
    }
    public String getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(String currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public String[] getCurrentAlternatives() {
        return currentAlternatives;
    }

    public void setCurrentAlternatives(String[] currentAlternatives) {
        this.currentAlternatives = currentAlternatives;
    }

    public boolean isCurrentAnsweredResult() {
        return currentAnsweredResult;
    }

    public void setCurrentAnsweredResult(boolean currentAnsweredResult) {
        this.currentAnsweredResult = currentAnsweredResult;
    }
    public boolean getOpponentReady() {
        return opponentReady;
    }
    public void setOpponentReady(boolean opponentReady) {
        this.opponentReady = opponentReady;
    }
    public QuizPlayer getOpponent() {
        return opponent;
    }

    public void setOpponent(QuizPlayer opponent) {
        this.opponent = opponent;
    }
}
