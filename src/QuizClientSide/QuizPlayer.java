package QuizClientSide;

public class QuizPlayer {

    private String name;
    private int score;
    private int partScore;

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
}
