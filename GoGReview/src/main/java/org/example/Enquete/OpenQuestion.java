package org.example.Enquete;

import com.opencsv.CSVWriter;

import java.util.Scanner;

public class OpenQuestion implements Question {

    /**
     * Contains the question in text.
     */
    private final String text;

    /**
     * Constructs a new {@code OpenQuestion}.
     *
     * @param text The question in text
     */
    public OpenQuestion(String text) {
        this.text = text;
    }

    /**
     * Gives the question of this {@code OpenQuestion}.
     *
     * @return The text of this {@code OpenQuestion}
     */
    public String getText() {
        return this.text;
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
        System.out.println(this.text);
        String answer = scanner.nextLine();
        writer.writeNext(new String[]{this.text, answer});
    }
}
