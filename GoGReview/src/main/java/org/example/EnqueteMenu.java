package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EnqueteMenu {

    private final static String EnquetePath = "Enquetes";

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
                    optie1(scanner);
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

    private static void optie1(Scanner scanner) {
        ArrayList<Enquete> enquetes = new ArrayList<>();
        File directory = new File(EnquetePath);
        if(!directory.exists()) {
            directory.mkdir();
        }
        File[] files = directory.listFiles();
        if(files != null) {
            for(File file : files) {
                try {
                    FileInputStream fin = new FileInputStream(file);
                    ObjectInputStream in = new ObjectInputStream(fin);
                    Enquete enquete = (Enquete) in.readObject();
                    enquetes.add(enquete);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if(enquetes.isEmpty()) {
            System.out.println("Er zijn op het moment geen enquêtes beschikbaar.");
        } else {
            System.out.println("Beschikbare enquêtes:");
            for (int i = 0; i < enquetes.size(); i++) {
                System.out.println((i+1) + ". " + enquetes.get(i).getName());
            }
            boolean running = true;
            int choice = -1;
            while(running) {
                System.out.println("Voer uw keuze in:");
                choice = scanner.nextInt();
                scanner.nextLine();
                if(choice <= enquetes.size() && choice >= 0) {
                    running = false;
                }
            }
            enquetes.get(choice - 1).takeQuiz(scanner);
        }
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
            File directory = new File(EnquetePath);
            if(!directory.exists()) {
                directory.mkdir();
            }
            FileOutputStream fout = new FileOutputStream(EnquetePath + "/" + name + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(enquete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
