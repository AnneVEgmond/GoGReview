package org.example;

import java.util.ArrayList;

public class MultiplechoiceQuestion implements Question {
    private String text;
    private ArrayList<String> answers;

    public MultiplechoiceQuestion(String text) {
        this.text = text;
        answers = new ArrayList<>();
    }

    public String getText() {
        return this.text;
    }

    public void addAnswer(String answer) {
        if(answers.size() < 4) {
            answers.add(answer);
        } else {
            System.out.println("Je kan niet meer dan 4 antwoorden hebben!!");
        }
    }

    @Override
    public String[] answerQuestion() {
        // TODO
        return null;
    }
}
