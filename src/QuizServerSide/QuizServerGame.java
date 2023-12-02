package QuizServerSide;

import QuizServerSide.Questions.ArrayOfQuestions;
<<<<<<< HEAD

=======
>>>>>>> main
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Properties;

public class QuizServerGame implements Serializable {
    int totalRounds = 0;
    int numberOfQuestionsPerRound = 2;
    int nrOfCategories = 3;
    int delayStartNewQuestion;
    int timeToAnswer;
    String[] categories;
    String currentCategory;
    ArrayOfQuestions aq;
    Properties p = new Properties();
    String filename = "src/Quizkampen.properties";

    QuizServerGame() {
        aq = new ArrayOfQuestions();
        readPropertyFile();
        try {
            this.numberOfQuestionsPerRound = Integer.parseInt(p.getProperty("NrOfQuestions", "2"));
            this.totalRounds = Integer.parseInt(p.getProperty("NrOfRounds", "2"));
            this.nrOfCategories = Integer.parseInt(p.getProperty("nrOfCategories", "3"));
            this.delayStartNewQuestion = Integer.parseInt(p.getProperty("DelayStartNewQuestion", "3000"));
            this.timeToAnswer = Integer.parseInt(p.getProperty("TimeToAnswer", "10"));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        checkValuesFromPropertiesFile();
        categories = new String[nrOfCategories];
    }
    public void checkValuesFromPropertiesFile(){
        if(this.numberOfQuestionsPerRound<1 || this.numberOfQuestionsPerRound>5){
            this.numberOfQuestionsPerRound = 5;
        }
        if(this.totalRounds<1 || this.totalRounds>10){
            this.totalRounds = 10;
        }
        if(this.nrOfCategories<1 || this.nrOfCategories>6){
            this.nrOfCategories = 6;
        }
        if (this.timeToAnswer < 5 || this.timeToAnswer > 20) {
            this.timeToAnswer = 10;
        }

    }

=======
    public void checkValuesFromPropertiesFile(){
        if(this.numberOfQuestionsPerRound<1 || this.numberOfQuestionsPerRound>5){
            this.numberOfQuestionsPerRound = 5;
        }
        if(this.totalRounds<1 || this.totalRounds>10){
            this.totalRounds = 10;
        }
        if(this.nrOfCategories<1 || this.nrOfCategories>6){
            this.nrOfCategories = 6;
        }
    }
>>>>>>> main
    public void readPropertyFile() {
        try {
            p.load(new FileInputStream(filename));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    public int getNumberOfQuestionsPerRound() {
        return numberOfQuestionsPerRound;
    }

    public void setCurrentCategory(String currentCategory) {
        this.currentCategory = currentCategory;
    }

    public String getCurrentCategory() {
        return currentCategory;
    }

    public ArrayOfQuestions getAq() {
        return aq;
    }

    public int getNrOfCategories() {
        return nrOfCategories;
    }

    public int getTimeToAnswer(){
        return timeToAnswer;
    }
}
