package org.example;

import com.opencsv.CSVWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class ConditionalQuestion implements Question {

    private String text;
    private ArrayList<String> answers;
    private ArrayList<Question> followupQuestions;

    public ConditionalQuestion(String text) {
        this.text = text;
        answers = new ArrayList<>();
        followupQuestions = new ArrayList<>();
    }

    public String getText() {
        return this.text;
    }

    public void addAnswer(String answer, Question question) {
        if (answers.size() < 4) {
            answers.add(answer);
            followupQuestions.add(question);
        } else {
            System.out.println("Je kan niet meer dan 4 antwoorden hebben!!");
        }
    }

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
        if (followupQuestions.get(answer - 1) != null) {
            followupQuestions.get(answer - 1).answerQuestion(scanner, writer);
        }
    }
}
