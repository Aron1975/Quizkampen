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
}




