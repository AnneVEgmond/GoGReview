package org.example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class EnqueteMenu {

    public static void Menu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while(running) {

            System.out.println("1. Vul enquête in");
            System.out.println("2. Maak enquête");
            System.out.println("0. Exit");
            System.out.println();
            System.out.println("Voer uw keuze in:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    optie1();
                    break;
                case 2:
                    optie2(scanner);
                    break;
                case 0:
                    running = false;
                    System.out.println("Enquête menu sluit.");
                    break;
                default:
                    System.out.println("Ongeldige keuze. Probeer opnieuw.");
                    break;

            }
        }
    }

    private static void optie1() {
        System.out.println("TODO");
    }

    private static void optie2(Scanner scanner) {
        boolean running = true;
        System.out.println("Geef een naam aan de enquête: ");
        String name = "Enquete_" + scanner.nextLine();
        Enquete enquete = new Enquete(name);
        while (running) {
            System.out.println("Wat voor vraag wilt u toevoegen?");
            System.out.println();
            System.out.println("1. Open vraag");
            System.out.println("2. Multiplechoice vraag");
            System.out.println("3. Conditionele vraag");
            System.out.println("0. Enquête afronden");
            System.out.println();
            System.out.println("Voer uw keuze in:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    enquete.addQuestion(QuestionMaker.createOpenQuestion(scanner));
                    System.out.println("Vraag is toegevoegd!");
                    break;
                case 2:
                    enquete.addQuestion(QuestionMaker.createMultiplechoiceQuestion(scanner));
                    System.out.println("Vraag is toegevoegd!");
                    break;
                case 3:
                    enquete.addQuestion(QuestionMaker.createConditionalQuestion(scanner));
                    System.out.println("Vraag is toegevoegd!");
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Ongeldige keuze. Probeer opnieuw.");
                    break;
            }
        }
        try {
            FileOutputStream fout = new FileOutputStream(name + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(enquete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
