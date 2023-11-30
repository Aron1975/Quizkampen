package QuizServerSide;


import QuizServerSide.Questions.ArrayOfQuestions;
import QuizServerSide.Questions.Questions;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

public class QuizServerGame implements Serializable {
    int totalRounds = 0;
    int numberOfQuestionsPerRound = 2;
    int nrOfCategories = 3;
    int delayStartNewQuestion;
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
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        categories = new String[nrOfCategories];
    }

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
}
