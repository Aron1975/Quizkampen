package QuizServerSide.Questions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

class ArrayOfQuestions {
    ArrayList<Questions> questionsAndalternatives = new ArrayList<>();

    public ArrayOfQuestions() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/QuizServerSide/Questions/Questions"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String category = line;
                String question = reader.readLine();
                String[] alternative = reader.readLine().split(";");
                String correctalternative = reader.readLine();

                questionsAndalternatives.add(new Questions(category, question, alternative, correctalternative));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Questions generateRandomQuestion(String categoryFromUser){
       Questions question;
       ArrayList <Questions> qn=new ArrayList<>();

       for (Questions questions : questionsAndalternatives){
            if ((questions.category).equals(categoryFromUser)){
                qn.add(questions);
            }
        }

       Collections.shuffle(qn);

       return qn.get(0);



    }

    public String[] randomizeAnswerAlternativs(String[] answers){
        Questions question;
        ArrayList<Questions>qn=new ArrayList<>();

        for (Questions questions:questionsAndalternatives){
            if((questions.correctalternative).equals(answers));
        }







        return answers;
    }





    public static void main(String[] args) {
        //Skapar en arraylist för frågor samt svar
        //Läsa in frågor och svar
        ArrayOfQuestions aq=new ArrayOfQuestions();
        Questions q;

        q=aq.generateRandomQuestion("Sci-fi:");
        System.out.println(q.question);


        /*Scanner scanner = new Scanner(System.in);
        //Ställer vardera fråga, kontrollerar sedan svar
        for (Questions questions : aq.questionsAndalternatives) {
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


        }*/

    }
}