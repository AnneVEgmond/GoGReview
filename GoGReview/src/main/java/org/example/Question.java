package org.example;

import com.opencsv.CSVWriter;

import java.io.Serializable;
import java.util.Scanner;

public interface Question extends Serializable {
    String getText();

    void answerQuestion(Scanner scanner, CSVWriter writer);
}
