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

    //Implement for keep track on which question and which round we are in.
    int nrOfQuestionsPerRound = 3;   // Hämta från server
    int nrOfRounds = 3;      // Hämta från server
    int questionNr = 0;
    int roundNr = 1;
    Boolean[][] answers = new Boolean[nrOfRounds][nrOfQuestionsPerRound];
    Boolean[][] opponentAnswers = new Boolean[nrOfRounds][nrOfQuestionsPerRound];

    public QuizPlayer(){}

    public QuizPlayer(String name, int score) {
        this.name = name;
        this.score = score;
    }

    //Räknar vilken fråga o round vi är på. Anropas från protokoll varje gång man tar emot en fråga.
    public void roundAndQuestionCounter(){
        this.questionNr++;
        if(this.questionNr>nrOfQuestionsPerRound) {
            this.roundNr++;
            this.questionNr = 1;
            if (this.roundNr>this.nrOfRounds){
                System.out.println("Fel. Vi ska inte kunna hamna här....");
            }
        }
        if((this.questionNr == nrOfQuestionsPerRound) && (this.roundNr==nrOfRounds)){
            this.questionNr=0;
            this.roundNr = 1;
        }
    }

    public boolean getAnswerResult(int round, int question) {
        return answers[round][question];
    }

    public void setAnswerResult(int round, int question, boolean answer) {
        this.answers[round][question] = answer;
    }

    public boolean getOpponentAnswerResult(int round, int question) {
        return answers[round][question];
    }

    public void setOpponentAnswerResult(int round, int question, boolean answer) {
        this.opponentAnswers[round][question] = answer;
    }

    public int getQuestionNr() {
        return questionNr;
    }

    public int getRoundNr() {
        return roundNr;
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
