package QuizServerSide.Questions;

import QuizServerSide.QuizServerGame;
import QuizServerSide.QuizServerPlayer;

public class Questions {
    String category;
    String question;
    String[] alternative;
    String correctAlternative;



    Questions(String category, String question, String[] alternative, String correctAlternative) {
        this.category = category;
        this.question = question;
        this.alternative = alternative;
        this.correctAlternative = correctAlternative;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAlternative() {
        return alternative;
    }

    public void setAlternative(String[] alternative) {
        this.alternative = alternative;
    }

    public String getCorrectAlternative() {
        return correctAlternative;
    }

    public void setCorrectAlternative(String correctAlternative) {
        this.correctAlternative = correctAlternative;
    }

    public boolean checkAnswer(String answer, QuizServerPlayer player, QuizServerGame game){
        boolean result = answer.equals(correctAlternative);
        player.getAnswers()[player.getCurrentRound()][player.getCurrentQuestionWithinRound()] = result;
        return result;
    }
    public int correctAnswerIndex(){
        for(int i = 0; i < alternative.length; i++){
            if(alternative[i].equals(correctAlternative)){
                return i;
            }
        }
        return 0;
    }
}






