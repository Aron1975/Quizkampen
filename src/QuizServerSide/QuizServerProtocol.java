package QuizServerSide;

public class QuizServerProtocol {

    private static final int WAITING = 0;
    private static final int SENTQUESTION = 1;
    private static final int SENTANSWER = 2;
    private static final int ANOTHER = 3;

    private int state = WAITING;

    private String[] questions = {"Vem uppfann elektriciteten?"};
    private String[] answerAlternatives = {"Robert Downey Jr.", "Ingen", "Tesla", "Gud"};
    private String[] answer = {"Juste...", "Rätt", "Han kan ha blivit träffad av blixten, medans han körde runt i sin elektriska bil.", "Ok, så du är religiös"};

    public QuizServerProtocol() {

    }

    public String[] getQuestions() {
        return questions;
    }

    public void setQuestions(String[] questions) {
        this.questions = questions;
    }

    public String[] getAnswerAlternatives() {
        return answerAlternatives;
    }

    public void setAnswerAlternatives(String[] answerAlternatives) {
        this.answerAlternatives = answerAlternatives;
    }

    public String[] getAnswer() {
        return answer;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }

    public String sendQuestion(){
        return questions[0];
    }

    public String[] sendAnswers(){
        return answerAlternatives;
    }

    public String processInput(String dataIn) {
        String dataOut = "No match";
       /* if (state == WAITING) {
            dataOut = questions[0];
            state = SENTQUESTION;
        }
        if (state == SENTQUESTION) {
            //.....
            state = SENTANSWER;
        }
        if (state == SENTANSWER) {
            //....
            state = WAITING;
        }*/
        for (int i=0; i<answerAlternatives.length; i++){
            if(dataIn.equals(answerAlternatives[i])){
                return answer[i];
            }
        }
        return dataOut;
    }
}
