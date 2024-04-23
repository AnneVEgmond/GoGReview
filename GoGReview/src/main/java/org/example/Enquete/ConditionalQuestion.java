package org.example.Enquete;

import com.opencsv.CSVWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class ConditionalQuestion implements Question {

    /**
     * Contains the question in text.
     */
    private final String text;

    /**
     * Contains all possible answers.
     */
    private final ArrayList<String> answers;

    /**
     * Contains all questions that will be asked if a specific answer is given.
     */
    private final ArrayList<Question> followupQuestions;

    /**
     * Constructs a new {@code ConditionalQuestion}.
     *
     * @param text The question in text
     */
    public ConditionalQuestion(String text) {
        this.text = text;
        answers = new ArrayList<>();
        followupQuestions = new ArrayList<>();
    }

    /**
     * Gives the question text of this {@code ConditionalQuestion}.
     *
     * @return The text of this {@code ConditionalQuestion}
     */
    public String getText() {
        return this.text;
    }

    /**
     * Appends a new answer to the possible answers of this
     * {@code ConditionalQuestion}.
     *
     * @param answer The text of this answer
     * @param question The {@code Question} that is associated with this
     * answer. Can be null, and that means that there is no followup question
     */
    public void addAnswer(String answer, Question question) {
        if (answers.size() < 4) {
            answers.add(answer);
            followupQuestions.add(question);
        } else {
            System.out.println("Je kan niet meer dan 4 antwoorden hebben!!");
        }
    }

    /**
     * Answer the question and write it to a file with the given
     * {@code CSVWriter}.
     *
     * @param scanner Takes the users' input to answer the question
     * @param writer Knows where to write the answer to
     */
    @Override
    public void answerQuestion(Scanner scanner, CSVWriter writer) {
        boolean running = true;
        int answer = -1;
        while (running) {
            System.out.println(this.text);
            for (int i = 0; i < answers.size(); i++) {
                System.out.println((i + 1) + ". " + this.answers.get(i));
            }
            System.out.println("Voer uw keuze in: (Kies een nummertje)");
            answer = scanner.nextInt();
            scanner.nextLine();
            if (answer > 0 && answer <= answers.size()) {
                running = false;
            } else {
                System.out.println("Ongeldige keuze. Probeer opnieuw.");
            }
        }
        writer.writeNext(new String[]{this.text, answers.get(answer - 1)});
        if (followupQuestions.get(answer - 1) != null) {
            followupQuestions.get(answer - 1).answerQuestion(scanner, writer);
        }
    }
}
