package QuizServerSide.Questions;

public class Questions {
    /*ArrayList<String> questions =new ArrayList<>();
    ArrayList<String[]> alternativ =new ArrayList<>();
    ArrayList<String> alternative =new ArrayList<>();
    ArrayList<String> kategori =new ArrayList<>();*/
    //Konstruktor
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

    public boolean checkAnswer(String answer){
        return answer.equals(correctAlternative);
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






