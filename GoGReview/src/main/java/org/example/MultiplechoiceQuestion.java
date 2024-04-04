package org.example;

public class MultiplechoiceQuestion implements Question {
    private String text;

    public MultiplechoiceQuestion(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public void answerQuestion() {

    }
}
