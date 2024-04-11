package org.example;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Enquete implements Serializable {
    private String name;
    private ArrayList<Question> questions;

    public Enquete(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public String getName() {
        return name;
    }

    public void takeQuiz(Scanner scanner) {
        File directory = new File("EnqueteAnswers");
        if (!directory.exists()) {
            directory.mkdir();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH-mm-ss_dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        try (CSVWriter writer = new CSVWriter(new FileWriter("EnqueteAnswers/" + name + "_" + dtf.format(now) + ".csv"))) {
            for (Question q : this.questions) {
                q.answerQuestion(scanner, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
