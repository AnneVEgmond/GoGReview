package org.example;

import java.util.List;

public class Enquete {
    private String name;
    private List<Question> questions;

    public Enquete (String name) {
        this.name = name;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void removeQuestion(Question question) {
        this.questions.remove(question);
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    public int getAmountOfQuestions() {
        return this.questions.size();
    }

    public void answerQuestion(int questionIndex) {

    }
}
