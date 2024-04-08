package org.example;

import java.io.IOException;
import java.util.Scanner;

public class MenuExample {
    static GameLibrary games = new GameLibrary();
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public void menuStart() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            clearScreen();
            printMenu();
            System.out.print("Voer uw keuze in: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    optie1();
                    break;
                case 2:
                    optie2();
                    break;
                case 3:
                    optie3();
                    break;
                case 4:
                    optie4();
                    break;

                case 5:
                    running = false;
                    System.out.println("Programma wordt afgesloten.");
                    break;
                case 9:
                    optie9();
                    break;
                default:
                    System.out.println("Ongeldige keuze. Probeer opnieuw.");
            }
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("           \\.   \\.      __,-\"-.__      ./   ./\n" +
                "       \\.   \\`.  \\`.-'\"\" _,=\"=._ \"\"`-.'/  .'/   ./\n" +
                "        \\`.  \\_`-''      _,=\"=._      ``-'_/  .'/\n" +
                "         \\ `-',-._   _.  _,=\"=._  ,_   _.-,`-' /\n" +
                "      \\. /`,-',-._\"\"\"  \\ _,=\"=._ /  \"\"\"_.-,`-,'\\ ./\n" +
                "       \\`-'  /    `-._  \"       \"  _.-'    \\  `-'/\n" +
                "       /)   (         \\    ,-.    /         )   (\\\n" +
                "    ,-'\"     `-.       \\  /   \\  /       .-'     \"`-,\n" +
                "  ,'_._         `-.____/ /  _  \\ \\____.-'         _._`,\n" +
                " /,'   `.                \\_/ \\_/                .'   `,\\\n" +
                "/'       )                  _                  (       `\\\n" +
                "        /   _,-'\"`-.  ,++|T|||T|++.  .-'\"`-,_   \\\n" +
                "       / ,-'        \\/|`|`|`|'|'|'|\\/        `-, \\\n" +
                "      /,'             | | | | | | |             `\\");
        System.out.println("MENU:");

        System.out.println("1. Alle games bekijken");
        System.out.println("2. Ranglijst games");
        System.out.println("3. Uitverkoop");
        System.out.println("4. Retro game spelen");
        System.out.println("5. Afsluiten");
    }

    private static void optie1() {
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
        scanner.nextLine();
        Game gekozenGame = games.getGame(gekozenGameIndex);
        if (gekozenGameIndex >= 0 && gekozenGameIndex < games.gamelijst.size()) {

            clearScreen();
            review.readFile(gekozenGame);
            System.out.println("U heeft gekozen voor het volgende spel: " + gekozenGame.getNaam());
            
            gekozenGame.toonGegevens();
            }
            
      
            
        System.out.println();
        System.out.println("Wilt u deze spel beoordelen? (J/N)");
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


        } else if (keuze.equalsIgnoreCase("N")){ terugNaarHoofdmenu();}
        


    }

    private static void optie2() {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        Review review = new Review();
        System.out.println("U heeft gekozen voor ranglijst games.");
        System.out.println();
        games.printGamesByRating();
        System.out.println();
        System.out.println("Kies een game uit: ");
        int gekozenGameIndex = scanner.nextInt() - 1;
        Game gekozenGame = games.getGame(gekozenGameIndex);
        if (gekozenGameIndex > 0 && gekozenGameIndex < games.gamelijst.size()) {

            clearScreen();
            System.out.println("U heeft gekozen voor het volgende spel: " + gekozenGame.getNaam());
            gekozenGame.toonGegevens();
        }
        System.out.println();
        System.out.println("Wilt u deze spel beoordelen? (J/N)");
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


            // try {
            review.writeReviewToFile(gekozenGame); // Write the review to a file
            System.out.println();
            System.out.println("Bedankt voor uw review!");
            // } catch (IOException e) {
            //System.out.println("Er is een fout opgetreden bij het opslaan van uw review.");
            //e.printStackTrace();
            //  }

        } else { terugNaarHoofdmenu();}


    }




    private static void optie3() {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        System.out.println("U heeft gekozen voor uitverkoop.");
        System.out.println();

       // games.printGamesByGenre();

        terugNaarHoofdmenu();
    }

    private static void optie4() {
        clearScreen();
        System.out.println("U heeft gekozen voor Retro-game.");
        System.out.println();
        // Voeg hier de functionaliteit toe voor Optie 4
        terugNaarHoofdmenu();
    }

    private static void optie9() {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        System.out.println("U heeft gekozen voor admin");
        System.out.println("1: Voeg game toe");
        System.out.println("2: Verwijder game");
        System.out.println("3: Bekijk ");
        System.out.println("9: Terug naar hoofdmenu");

        System.out.print("Voer uw keuze in: ");
        int keuze = scanner.nextInt();
        scanner.nextLine();

        switch (keuze) {
            case 1:
                games.voegGameToe();
                break;
            case 2:
                games.printGamelijst();
                System.out.println("Welke game wilt u verwijderen?");
                int gameIndex = scanner.nextInt();
                scanner.nextLine();
                games.verwijderGame(gameIndex);
                break;
            case 3:
                games.printGamelijst();
                System.out.println("Van welke game wilt u de reviews lezen?");
                int gameIndex2 = scanner.nextInt();
                scanner.nextLine();
                // Hier moet verder gewerkt worden aan review klasse

                break;
            case 9:
                break;
            default:
                System.out.println("Ongeldige keuze. Probeer opnieuw.");
        }

        terugNaarHoofdmenu();
    }

    private static void terugNaarHoofdmenu() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Druk op Enter om terug te gaan naar het hoofdmenu.");
        scanner.nextLine();
        clearScreen();
    }

    private static void terugNaarHoofdmenu2() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        clearScreen();

    }
}