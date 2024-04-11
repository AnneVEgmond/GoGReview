package org.example;

public class OpenQuestion implements Question {
    private String text;

    public OpenQuestion(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public String[] answerQuestion() {
        // TODO
        return null;
    }
}
