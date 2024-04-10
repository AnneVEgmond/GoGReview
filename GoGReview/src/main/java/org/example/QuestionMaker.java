package org.example;

import java.util.Scanner;

/**
 * Utility class
 *
 * Needs to be a separate class because a ConditionalQuestion can call on a different ConditionalQuestion.
 * To provide this recursive functionality, a crude factory class needs to be made.
 */
public class QuestionMaker {

    public static OpenQuestion createOpenQuestion(Scanner scanner) {
        System.out.println("Voer de vraag in:");
        String questionText = scanner.nextLine();
        return new OpenQuestion(questionText);
    }

    public static MultiplechoiceQuestion createMultiplechoiceQuestion(Scanner scanner) {
        System.out.println("Voer de vraag in:");
        String questionText = scanner.nextLine();
        MultiplechoiceQuestion question = new MultiplechoiceQuestion(questionText);
        System.out.println("Vul eerste antwoord in:");
        question.addAnswer(scanner.nextLine());
        System.out.println("Vul tweede antwoord in:");
        question.addAnswer(scanner.nextLine());
        boolean runningOuter = true;
        while(runningOuter) {
            System.out.println("Wilt u een derde antwoord toevoegen? (J/N)");
            String input = scanner.nextLine();
            if(input.equals("J")) {
                System.out.println("Vul het derde antwoord in:");
                question.addAnswer(scanner.nextLine());
                boolean runningInner = true;
                while(runningInner) {
                    System.out.println("Wilt u een vierde antwoord toevoegen? (J/N)");
                    input = scanner.nextLine();
                    if(input.equals("J")) {
                        System.out.println("Vul het vierde antwoord in:");
                        question.addAnswer(scanner.nextLine());
                        runningInner = false;
                    } else if (input.equals("N")) {
                        runningInner = false;
                    } else {
                        System.out.println("Ongeldige keuze. Probeer opnieuw.");
                    }
                }
                runningOuter = false;
            } else if(input.equals("N")) {
                runningOuter = false;
            } else {
                System.out.println("Ongeldige keuze. Probeer opnieuw.");
            }
        }
        return question;
    }

    public static ConditionalQuestion createConditionalQuestion(Scanner scanner) {
        return null;
    }
}
