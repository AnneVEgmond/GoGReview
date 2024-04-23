package org.example.Enquete;

import com.opencsv.CSVWriter;

import java.io.Serializable;
import java.util.Scanner;

public interface Question extends Serializable {

    /**
     * Gives the question text of this {@code Question}.
     *
     * @return The text of this {@code Question}
     */
    String getText();

    /**
     * Answer the question and write it to a file with the given
     * {@code CSVWriter}.
     *
     * @param scanner Takes the users' input to answer the question
     * @param writer Knows where to write the answer to
     */
    void answerQuestion(Scanner scanner, CSVWriter writer);
}
