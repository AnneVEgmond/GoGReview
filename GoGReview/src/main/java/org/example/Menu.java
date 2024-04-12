package org.example;

import org.example.Enquete.Enquete;
import org.example.Enquete.QuestionMaker;
import org.example.GameReview.Game;
import org.example.GameReview.GameLibrary;
import org.example.GameReview.Review;
import org.example.SnakeGame.GameFrame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    private final static String ResourcePath = "src/main/resources";

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public Menu() {
        // Initialize the resource folder
        File directory = new File(ResourcePath);
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Initialize the review folder
        directory = new File(ResourcePath + "/Reviews");
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Initialize the quiz folder
        directory = new File(ResourcePath + "/Enquetes");
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Initialize the quiz answers folder
        directory = new File(ResourcePath + "/EnqueteAnswers");
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Initialize the games.csv file for reading and writing
        directory = new File(ResourcePath + "/games.csv");
        try {
            if (!directory.exists()) {
                directory.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void menuStart() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        GameLibrary games = new GameLibrary();

        while (running) {
            printMenu();
            System.out.print("Voer uw keuze in: ");
            String choice = scanner.nextLine();
            System.out.println();

            switch (choice) {
                case "1":
                    alleGamesBekijken(games);
                    break;
                case "2":
                    ranglijstGames(games);
                    break;
                case "3":
                    uitverkoop(games);
                    break;
                case "4":
                    genresBekijken(games);
                    break;
                case "5":
                    vulEnqueteIn();
                    break;
                case "6":
                    retroGameSpelen();
                    break;
                case "8":
                    running = false;
                    System.out.println("Programma wordt afgesloten.");
                    break;
                case "9":
                    adminMenu(games);
                    break;
                default:
                    System.out.println("Ongeldige keuze. Probeer opnieuw.");
            }
        }
    }

    private void printMenu() {
        System.out.println("           \\.   \\.      _,-\"-._      ./   ./\n" +
                "       \\.   \\.  \\.-'\"\" ,=\"=. \"\"`-.'/  .'/   ./\n" +
                "        \\.  \\_-''      ,=\"=.      ``-'_/  .'/\n" +
                "         \\ -',-._   _.  _,=\"=._  ,_   _.-,-' /\n" +
                "      \\. /,-',-._\"\"\"  \\ _,=\"=._ /  \"\"\"_.-,-,'\\ ./\n" +
                "       \\`-'  /    `-._  \"       \"  _.-'    \\  `-'/\n" +
                "       /)   (         \\    ,-.    /         )   (\\\n" +
                "    ,-'\"     -.       \\  /   \\  /       .-'     \"-,\n" +
                "  ,'.         -.____/ /  _  \\ \\____.-'         _._,\n" +
                " /,'   `.                \\/ \\/                .'   `,\\\n" +
                "/'       )                  _                  (       `\\\n" +
                "        /   _,-'\"-.  ,++|T|||T|++.  .-'\"-,_   \\\n" +
                "       / ,-'        \\/|||`|'|'|'|\\/        `-, \\\n" +
                "      /,'             | | | | | | |             `\\");
        System.out.println("MENU:");
        System.out.println("1. Alle games bekijken");
        System.out.println("2. Ranglijst games");
        System.out.println("3. Uitverkoop");
        System.out.println("4. Genres bekijken");
        System.out.println("5. Maak enquête");
        System.out.println("6. Retro game spelen");
        System.out.println("8. Afsluiten");
    }

    private static void alleGamesBekijken(GameLibrary games) {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        Review review = new Review();
        System.out.println("U heeft gekozen voor alle games bekijken.");
        // Voeg hier de functionaliteit toe voor Optie 1
        System.out.println();
        games.printGamelijst();
        System.out.println();
        System.out.println("Kies een game uit: ");
        
        int gekozenGameIndex = scanner.nextInt()-1;
            
        Game gekozenGame = games.getGame(gekozenGameIndex);
        if (gekozenGameIndex >= 0 && gekozenGameIndex < games.getGamelijst().size()) {

            clearScreen();
            review.readFile(gekozenGame);
            System.out.println("U heeft gekozen voor het volgende spel: " + gekozenGame.getNaam());

            gekozenGame.toonGegevens();
        }

        System.out.println();
        System.out.println("Wilt u deze spel beoordelen? (J/N)");
        scanner.nextLine();
        String keuze = scanner.nextLine();

        if (keuze.equalsIgnoreCase("J")) {
            // hier functionaliteit om review te geven op een game.
            review = new Review();

            System.out.println("Geef uw beoordeling voor graphics (1-5): ");
            int graphicsRating = scanner.nextInt();

            if (graphicsRating <6 && graphicsRating > 0 ) {review.setRatingGraphics(graphicsRating);}

            System.out.println("Geef uw beoordeling voor story (1-5): ");
            int storyRating = scanner.nextInt();
            review.setRatingStory(storyRating);

            System.out.println("Geef uw beoordeling voor gameplay (1-5): ");
            int gameplayRating = scanner.nextInt();
            review.setRatingGameplay(gameplayRating);

            review.writeReviewToFile(gekozenGame); // Write the review to a file
            System.out.println();
            System.out.println("Bedankt voor uw review!");
            terugNaarHoofdmenu();
        } else if (keuze.equalsIgnoreCase("N")){ terugNaarHoofdmenu();}
    }

    private static void ranglijstGames(GameLibrary games) {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        Review review = new Review();
        System.out.println("U heeft gekozen voor ranglijst games.");
        System.out.println();
        ArrayList <Game> gamesoprating = games.printGamesByRating();
        int i = 1;
        for (Game game : gamesoprating) {
            System.out.printf(i + ". " + game.getNaam() + "  %.1f  $"  + game.getPrijs() + "\n", game.getRating());
            i++;
        }
        System.out.println();
        System.out.println("Kies een game uit: ");
        int gekozenGameIndex = scanner.nextInt() - 1;
        Game gekozenGame = games.getGame(gekozenGameIndex);
        if (gekozenGameIndex >= 0 && gekozenGameIndex < gamesoprating.size()) {

            clearScreen();
            System.out.println("U heeft gekozen voor het volgende spel: " + gekozenGame.getNaam());
            gekozenGame.toonGegevens();
        }
        System.out.println();
        System.out.println("Wilt u deze spel beoordelen? (J/N)");
        scanner.nextLine();
        String keuze = scanner.nextLine();

        if (keuze.equalsIgnoreCase("J")) {
            // hier functionaliteit om review te geven op een game.
            System.out.println("Geef uw beoordeling voor graphics (1-5): ");
            int graphicsRating = scanner.nextInt();
            review.setRatingGraphics(graphicsRating);

            System.out.println("Geef uw beoordeling voor story (1-5): ");
            int storyRating = scanner.nextInt();
            review.setRatingStory(storyRating);

            System.out.println("Geef uw beoordeling voor gameplay (1-5): ");
            int gameplayRating = scanner.nextInt();
            review.setRatingGameplay(gameplayRating);

            review.writeReviewToFile(gekozenGame); // Write the review to a file
            System.out.println();
            System.out.println("Bedankt voor uw review!");
            terugNaarHoofdmenu();
        }
    }

    private static void uitverkoop(GameLibrary games) {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        Review review = new Review();
        System.out.println("U heeft gekozen voor uitverkoop.");
         
        ArrayList<Game> gamesmetkorting = games.printgamesmetkorting();
        
        int i = 1;
        for (Game game : gamesmetkorting) {
            System.out.printf(i +" " + game.getNaam() + " " + game.getJaarRelease() + " genre: " + game.getGenre() + " $"  + game.getPrijs() +" %.1f\n", game.calculateAverageRating());
            i++;
        }
        System.out.println("Kies een game uit");
        int gekozenGameIndex = scanner.nextInt() - 1;
        Game gekozenGame = gamesmetkorting.get(gekozenGameIndex);
        if (gekozenGameIndex >= 0 && gekozenGameIndex <= gamesmetkorting.size()) {
            clearScreen();
            System.out.println("U heeft gekozen voor het volgende spel: " + gekozenGame.getNaam());
            gekozenGame.toonGegevens();
        }
        System.out.println();
        System.out.println("Wilt u deze spel beoordelen? (J/N)");
        scanner.nextLine();
        String keuze = scanner.nextLine();

        if (keuze.equalsIgnoreCase("J")) {
            // hier functionaliteit om review te geven op een game.
            review = new Review();

            System.out.println("Geef uw beoordeling voor graphics (1-5): ");
            int graphicsRating = scanner.nextInt();
            review.setRatingGraphics(graphicsRating);

            System.out.println("Geef uw beoordeling voor story (1-5): ");
            int storyRating = scanner.nextInt();
            review.setRatingStory(storyRating);

            System.out.println("Geef uw beoordeling voor gameplay (1-5): ");
            int gameplayRating = scanner.nextInt();
            review.setRatingGameplay(gameplayRating);


            review.writeReviewToFile(gekozenGame); // Write the review to a file
            System.out.println();
            System.out.println("Bedankt voor uw review!");
            terugNaarHoofdmenu();
        }
    }

    private static void genresBekijken(GameLibrary games) {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        Review review = new Review();
        System.out.println("U heeft gekozen voor genres bekijken");
        System.out.println("Welke genre wilt u bekijken?");
        String genre = scanner.nextLine();

        ArrayList<Game> gamesopgenre = games.printGamesByGenre(genre);
        int size = gamesopgenre.size();
        if (size == 0) {
            System.out.println("Er bestaan geen games van dit genre");
            terugNaarHoofdmenu();
        } else {
            int i = 1;
            for (Game game : gamesopgenre) {
                System.out.printf(i +" %s, %.1f, $%.2f\n", game.getNaam(), game.calculateAverageRating(), game.getPrijs());
                i++;
            }
            System.out.println("");
            System.out.println("Welke game kiest u");
            int gekozenGameIndex = scanner.nextInt() - 1;
            Game gekozenGame = gamesopgenre.get(gekozenGameIndex);
            if (gekozenGameIndex >= 0 && gekozenGameIndex <= gamesopgenre.size()) {
                clearScreen();
                System.out.println("U heeft gekozen voor het volgende spel: " + gekozenGame.getNaam());
            }
            System.out.println();
            System.out.println("Wilt u deze spel beoordelen? (J/N)");
            scanner.nextLine();
            String keuze = scanner.nextLine();

            if (keuze.equalsIgnoreCase("J")) {
                // hier functionaliteit om review te geven op een game.
                review = new Review();

                System.out.println("Geef uw beoordeling voor graphics (1-5): ");
                int graphicsRating = scanner.nextInt();
                review.setRatingGraphics(graphicsRating);

                System.out.println("Geef uw beoordeling voor story (1-5): ");
                int storyRating = scanner.nextInt();
                review.setRatingStory(storyRating);

                System.out.println("Geef uw beoordeling voor gameplay (1-5): ");
                int gameplayRating = scanner.nextInt();
                review.setRatingGameplay(gameplayRating);

                review.writeReviewToFile(gekozenGame); // Write the review to a file
                System.out.println();
                System.out.println("Bedankt voor uw review!");
                terugNaarHoofdmenu();
            }
        }
    }

    private static void vulEnqueteIn() {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Enquete> enquetes = new ArrayList<>();
        File directory = new File(ResourcePath + "/Enquetes");
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

    private static void retroGameSpelen() {
        new GameFrame();
    }

    private static void adminMenu(GameLibrary games) {
        boolean running = true;
        while(running) {
            clearScreen();
            Scanner scanner = new Scanner(System.in);
            System.out.println("U heeft gekozen voor admin");
            System.out.println("1: Voeg game toe");
            System.out.println("2: Verwijder game");
            System.out.println("3: Bekijk reviews");
            System.out.println("4: Maak enquête");
            System.out.println("9: Terug naar hoofdmenu");

            System.out.print("Voer uw keuze in: ");
            String keuze = scanner.nextLine();

            switch (keuze) {
                case "1":
                    Game game = new Game();
                    Scanner keyboard = new Scanner(System.in);
                    System.out.println("Welke game wilt u toevoegen?");
                    String naam = keyboard.nextLine();
                    game.setNaam(naam);

                    System.out.println("Wat is het releaseJaar?");
                    int jaarRelease = keyboard.nextInt();
                    game.setJaarRelease(jaarRelease);

                    keyboard.nextLine();

                    System.out.println("Wat is het genre?");
                    String genre = keyboard.nextLine();
                    game.setGenre(genre);

                    System.out.println("Wat is de prijs?");
                    double prijs = keyboard.nextDouble();
                    game.setPrijs(prijs);
                    scanner.nextLine();

                    System.out.println("Is deze game in de sale? J/N?");

                    String korting = scanner.nextLine();

                    if (korting.equalsIgnoreCase("j")) {
                        game.setSale(true);
                    } else {
                        game.setSale(false);
                    }
                    games.voegGameToe(game);
                    break;
                case "2":
                    games.printGamelijst();
                    System.out.println("Welke game wilt u verwijderen?");
                    int gameIndex = scanner.nextInt();

                    games.verwijderGame(gameIndex);
                    break;
                case "3":
                    clearScreen();
                    System.out.println("U heeft gekozen voor alle reviews bekijken");
                    System.out.println("");
                    scanner.nextLine();
                    ArrayList<Game> gamess = games.getGamelijst();
                    for (Game gamen : gamess) {
                        System.out.printf("%s: %.1f $%.2f\n", gamen.getNaam(), gamen.calculateAverageRating(), gamen.getPrijs());

                    }
                    System.out.println("");
                    System.out.println("Druk op enter om terug te gaan");
                    scanner.nextLine();
                    break;
                case "4":
                    maakEnquete(scanner);
                    break;
                case "9":
                    running = false;
                    break;
                default:
                    System.out.println("Ongeldige keuze. Probeer opnieuw.");
            }
        }
        terugNaarHoofdmenu();
    }
    
    private static void maakEnquete(Scanner scanner) {
        clearScreen();
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
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    enquete.addQuestion(QuestionMaker.createOpenQuestion(scanner));
                    System.out.println("Vraag is toegevoegd!");
                    break;
                case "2":
                    enquete.addQuestion(QuestionMaker.createMultiplechoiceQuestion(scanner));
                    System.out.println("Vraag is toegevoegd!");
                    break;
                case "3":
                    enquete.addQuestion(QuestionMaker.createConditionalQuestion(scanner));
                    System.out.println("Vraag is toegevoegd!");
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Ongeldige keuze. Probeer opnieuw.");
                    break;
            }
        }
        try {
            FileOutputStream fout = new FileOutputStream(ResourcePath + "/Enquetes/" + name + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(enquete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void terugNaarHoofdmenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Druk op Enter om terug te gaan naar het hoofdmenu.");
        scanner.nextLine();
        clearScreen();
    }
}
