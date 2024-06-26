package org.example.Enquete;

import java.util.Scanner;

/**
 * Utility class
 * <p>
 * Dit moet een aparte klasse zijn zodat het mogelijk is voor een conditionele
 * vraag altijd een vraag toe te voegen bij een antwoord.
 */
public final class QuestionMaker {

    /**
     * Empty, private constructor to make sure this class can not be
     * instantiated.
     */
    private QuestionMaker() { }

    /**
     * Provides the proces of creating an {@code OpenQuestion} object.
     *
     * @param scanner Takes the users' input to create the question
     * @return A new {@code OpenQuestion} according to the users' input
     */
    public static OpenQuestion createOpenQuestion(Scanner scanner) {
        System.out.println("Voer de vraag in:");
        String questionText = scanner.nextLine();
        return new OpenQuestion(questionText);
    }

    /**
     * Provides the process of creating a {@code MultipleChoiceQuestion}
     * object.
     *
     * @param scanner Takes the users' input to create the question
     * @return A new {@code MultipleChoiceQuestion} according to the users'
     * input
     */
    public static MultipleChoiceQuestion createMultiplechoiceQuestion(Scanner scanner) {
        System.out.println("Voer de vraag in:");
        String questionText = scanner.nextLine();
        MultipleChoiceQuestion question = new MultipleChoiceQuestion(questionText);
        System.out.println("Vul eerste antwoord in:");
        question.addAnswer(scanner.nextLine());
        System.out.println("Vul tweede antwoord in:");
        question.addAnswer(scanner.nextLine());

        boolean runningOuter = true;
        while (runningOuter) {
            System.out.println("Wilt u een derde antwoord toevoegen? (J/N)");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("J")) {
                System.out.println("Vul het derde antwoord in:");
                question.addAnswer(scanner.nextLine());
                boolean runningInner = true;
                while (runningInner) {
                    System.out.println("Wilt u een vierde antwoord toevoegen? (J/N)");
                    input = scanner.nextLine();
                    if (input.equalsIgnoreCase("J")) {
                        System.out.println("Vul het vierde antwoord in:");
                        question.addAnswer(scanner.nextLine());
                        runningInner = false;
                    } else if (input.equalsIgnoreCase("N")) {
                        runningInner = false;
                    } else {
                        System.out.println("Ongeldige keuze. Probeer opnieuw.");
                    }
                }
                runningOuter = false;
            } else if (input.equalsIgnoreCase("N")) {
                runningOuter = false;
            } else {
                System.out.println("Ongeldige keuze. Probeer opnieuw.");
            }
        }
        return question;
    }

    /**
     * Provides the processof creating a {@code ConditionalQuestion} object.
     *
     * @param scanner Takes the users' input to create the question.
     * @return A new {@code ConditionalQuestion} according to the users' input
     */
    public static ConditionalQuestion createConditionalQuestion(Scanner scanner) {
        System.out.println("Voer de vraag in:");
        String questionText = scanner.nextLine();
        ConditionalQuestion question = new ConditionalQuestion(questionText);
        System.out.println("Vul eerste antwoord in:");
        question.addAnswer(scanner.nextLine(), follupQuestion(scanner));
        System.out.println("Vul tweede antwoord in:");
        question.addAnswer(scanner.nextLine(), follupQuestion(scanner));

        boolean runningOuter = true;
        while (runningOuter) {
            System.out.println("Wilt u een derde antwoord toevoegen? (J/N)");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("J")) {
                System.out.println("Vul het derde antwoord in:");
                question.addAnswer(scanner.nextLine(), follupQuestion(scanner));
                boolean runningInner = true;
                while (runningInner) {
                    System.out.println("Wilt u een vierde antwoord toevoegen? (J/N)");
                    input = scanner.nextLine();
                    if (input.equalsIgnoreCase("J")) {
                        System.out.println("Vul het vierde antwoord in:");
                        question.addAnswer(scanner.nextLine(), follupQuestion(scanner));
                        runningInner = false;
                    } else if (input.equalsIgnoreCase("N")) {
                        runningInner = false;
                    } else {
                        System.out.println("Ongeldige keuze. Probeer opnieuw.");
                    }
                }
                runningOuter = false;
            } else if (input.equalsIgnoreCase("N")) {
                runningOuter = false;
            } else {
                System.out.println("Ongeldige keuze. Probeer opnieuw.");
            }
        }
        return question;
    }

    /**
     * Whenever an answer is added to a {@code ConditionalQuestion}, it has to
     * be asked if the user wants another question to be asked when this answer
     * is chosen.
     *
     * @param scanner Takes the users' input
     * @return {@code Null} if the user does not want to have another question
     * asked when this answer is chosen. Another {@code Question} if they do.
     */
    private static Question follupQuestion(Scanner scanner) {
        Question result = null;
        boolean runningOuter = true;
        while (runningOuter) {
            System.out.println("Wilt u bij dit antwoord doorvragen? (J/N)");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("J")) {
                boolean runningInner = true;
                while (runningInner) {
                    System.out.println("Wat voor vraag wilt u toevoegen?");
                    System.out.println();
                    System.out.println("1. Open vraag");
                    System.out.println("2. Multiplechoice vraag");
                    System.out.println("3. Conditionele vraag");
                    System.out.println();
                    System.out.println("Voer uw keuze in:");
                    String choice = scanner.nextLine();

                    switch (choice) {
                        case "1":
                            result = QuestionMaker.createOpenQuestion(scanner);
                            System.out.println("Vraag is toegevoegd!");
                            runningInner = false;
                            break;
                        case "2":
                            result = QuestionMaker.createMultiplechoiceQuestion(scanner);
                            System.out.println("Vraag is toegevoegd!");
                            runningInner = false;
                            break;
                        case "3":
                            result = QuestionMaker.createConditionalQuestion(scanner);
                            System.out.println("Vraag is toegevoegd!");
                            runningInner = false;
                            break;
                        default:
                            System.out.println("Ongeldige keuze. Probeer opnieuw.");
                            break;
                    }
                }
                runningOuter = false;
            } else if (input.equalsIgnoreCase("N")) {
                runningOuter = false;
            } else {
                System.out.println("Ongeldige keuze. Probeer opnieuw.");
            }
        }
        return result;
    }
}
