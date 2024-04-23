package org.example.Enquete;

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

    /**
     * Contains the name of the {@code Enquete}.
     */
    private final String name;

    /**
     * Contains all {@code Question} objects associated with this
     * {@code Enquete}.
     */
    private final ArrayList<Question> questions;

    /**
     * Constructs a new {@code Enquete}.
     *
     * @param name The name of this {@code Enquete}
     */
    public Enquete(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }

    /**
     * Appends a {@code Question} to the questions.
     *
     * @param question {@code Question} that needs to be added
     */
    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    /**
     * Gives the name of this {@code Enquete}.
     *
     * @return The name of this {@code Enquete}
     */
    public String getName() {
        return name;
    }

    /**
     * Run the {@code answerQuestion} method of every {@code Question} object
     * associated with this {@code Enquete}.
     *
     * @param scanner Takes the users' input to answer all the questions
     */
    public void takeQuiz(Scanner scanner) {
        File directory = new File("EnqueteAnswers");
        if (!directory.exists()) {
            directory.mkdir();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH-mm-ss_dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        try (CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/EnqueteAnswers/" + name + "_" + dtf.format(now) + ".csv"))) {
            for (Question q : this.questions) {
                q.answerQuestion(scanner, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
