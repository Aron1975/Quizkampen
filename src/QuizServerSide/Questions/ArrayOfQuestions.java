package QuizServerSide.Questions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayOfQuestions {
    ArrayList<Questions> questionsAndAlternatives = new ArrayList<>();
    ArrayList<String> categories = new ArrayList<>();

    public ArrayOfQuestions() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/QuizServerSide/Questions/Questions"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String category = line;
                if(!categories.contains(category)) {
                    categories.add(line);
                }
                String question = reader.readLine();
                String[] alternative = reader.readLine().split(";");
                String correctalternative = reader.readLine();

                questionsAndAlternatives.add(new Questions(category, question, alternative, correctalternative));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Questions generateRandomQuestion(String categoryFromUser, ArrayList<Questions> availableQuestions) {
       Questions question;

       for (Questions questions : questionsAndAlternatives){
            if ((questions.category).equals(categoryFromUser)){
                availableQuestions.add(questions);
            }
        }

       Collections.shuffle(availableQuestions);

        Questions tempQuestion = availableQuestions.get(0);
        availableQuestions.remove(0);
        System.out.println(tempQuestion.question);
       return tempQuestion;



    }

    /*public String[] randomizeAnswerAlternatives(String[] answers){





        return answers;
    }

     */

    public String[] randomizeAnswerAlternatives(String[] answers) {
        List<String> shuffledAlternatives = new ArrayList<>(Arrays.asList(answers));

        Collections.shuffle(shuffledAlternatives);

        return shuffledAlternatives.toArray(new String[0]);
    }

    public String[] randomizeCategoryAlternatives(int nrOfCategories) {
        Collections.shuffle(this.categories);
        String[] nrOfRandomCategories = new String[nrOfCategories];
        for(int i= 0; i<nrOfCategories; i++){
            nrOfRandomCategories[i] = this.categories.get(i);
        }
        return nrOfRandomCategories;
    }







    /*public static void main(String[] args) {
        //Skapar en arraylist för frågor samt svar
        //Läsa in frågor och svar
        ArrayOfQuestions aq=new ArrayOfQuestions();
        Questions q;

        q=aq.generateRandomQuestion("Sci-fi:", game.availableQuestions);
        System.out.println(q.question);
        System.out.println(q.correctAlternative);
        //if (aq.checkAnswer(q.correctAlternative, q.question))
            //System.out.println("Winner");



        String[] shuffledAlternatives = aq.randomizeAnswerAlternatives(q.alternative);

        System.out.println("");
        for (int i = 0; i < shuffledAlternatives.length; i++) {
            System.out.println((i + 1) + ". " + shuffledAlternatives[i]);
        }

        System.out.println("Categories");
        for (int i = 0; i < aq.categories.size(); i++) {
            System.out.println((i + 1) + ". " + aq.categories.get(i));
        }

        /*Scanner scanner = new Scanner(System.in);
        //Ställer vardera fråga, kontrollerar sedan svar
        for (Questions questions : aq.questionsAndAlternatives) {
            System.out.println(questions.category + "-" + questions.question);

            for (int i = 0; i < questions.alternative.length; i++) {
                System.out.println((i + 1) + "." + questions.alternative[i]);
            }
            //Knappar blir det ju i GUI men kanske kopplar dem till siffror först?
            System.out.print("Skriv svarsnummer: ");
            int userAnswerIndex = scanner.nextInt() - 1;

            if (questions.alternative[userAnswerIndex].equals(questions.correctalternative)) {
                System.out.println("Rätt\n");
            } else {
                System.out.println("Fel" + questions.correctalternative + "\n");
            }


        */

    }