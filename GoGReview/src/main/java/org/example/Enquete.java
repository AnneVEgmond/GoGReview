package org.example;

import java.io.Serializable;
import java.util.ArrayList;

public class Enquete implements Serializable {
    private String name;
    private ArrayList<Question> questions;

    public Enquete (String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public String getName() {
        return name;
    }

    public void takeQuiz() {
        for (Question q :
                this.questions) {
            q.answerQuestion();
        }
    }

}
