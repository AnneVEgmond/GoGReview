package org.example;

import com.opencsv.CSVWriter;

import java.util.Scanner;

public class OpenQuestion implements Question {
    private String text;

    public OpenQuestion(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public void answerQuestion(Scanner scanner, CSVWriter writer) {
        System.out.println(this.text);
        String answer = scanner.nextLine();
        writer.writeNext(new String[]{this.text, answer});
    }
}
