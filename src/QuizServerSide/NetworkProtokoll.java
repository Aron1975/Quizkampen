package QuizServerSide;

public class NetworkProtokoll {
    private static final int LOBBY = 0;
    private static final int PRESENT_QUESTIONS = 1;
    private static final int ANSWER = 2;
    private static final int REVIEW_ANSWER = 3;
    private static final int DISPLAY_WINNER = 4;

    private int state = LOBBY;
    private int currentQuestion = 0;
    private int currentRound = 0;

    public String processInput(String input) {

        if (state == LOBBY) {
            state = PRESENT_QUESTIONS;
        }
        if (state == PRESENT_QUESTIONS) {
            state = ANSWER;
        }
        if (state == ANSWER) {
            state = REVIEW_ANSWER;
        }
        if (state == REVIEW_ANSWER) {
            state = DISPLAY_WINNER;
        }
        if (state == DISPLAY_WINNER) {
            state = LOBBY;
        }
        return "No match";
    }


}

