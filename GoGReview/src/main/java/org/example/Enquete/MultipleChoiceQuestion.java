package org.example.Enquete;

import com.opencsv.CSVWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class MultipleChoiceQuestion implements Question {

    /**
     * Contains the question in text.
     */
    private final String text;

    /**
     * Contains all possible answers.
     */
    private final ArrayList<String> answers;

    /**
     * Constructs a new {@code MultipleChoiceQuestion}.
     *
     * @param text The question in text.
     */
    public MultipleChoiceQuestion(String text) {
        this.text = text;
        answers = new ArrayList<>();
    }

    /**
     * Gives the question text of this {@code MultipleChoiceQuestion}.
     *
     * @return The text of this {@code MultipleChoiceQuestion}
     */
    public String getText() {
        return this.text;
    }

    /**
     * Appends a new answer to the possible of this
     * {@code MultipleChoiceQuestion}.
     *
     * @param answer The text of this answer
     */
    public void addAnswer(String answer) {
        if (answers.size() < 4) {
            answers.add(answer);
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
            System.out.println("Voer uw keuze in: ");
            answer = scanner.nextInt();
            scanner.nextLine();
            if (answer > 0 && answer <= answers.size()) {
                running = false;
            } else {
                System.out.println("Ongeldige keuze. Probeer opnieuw.");
            }
        }
        writer.writeNext(new String[]{this.text, answers.get(answer - 1)});
    }
}
