package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import com.opencsv.CSVParserWriter;
import com.opencsv.CSVWriter;

import java.io.*;

public class GameLibrary {

    ArrayList<Game> gamelijst;

    public GameLibrary() {
        gamelijst = new ArrayList<Game>();
        Review review = new Review();
        Game gta3= new Game ("Grand Theft auto 3", 2001, "Action", 1, 60.0, 18, "Rockstar Games", "Welcome to Liberty City. Where it all began. The critically acclaimed blockbuster Grand Theft Auto III brings to life the dark and seedy underworld of Liberty City. With a massive and diverse open world, a wild cast of characters from every walk of life, and the freedom to explore at will, Grand Theft Auto III puts the dark, intriguing, and ruthless world of crime at your fingertips." );
        gamelijst.add(gta3);

        Game doom = new Game ("Doom", 1993, "Action", 1, 60.0, 18, "Bethesda Softworks", "Doom is a first-person shooter game developed and published by id Software. Released on December 10, 1993, for DOS, it is the first installment in the Doom franchise. The player assumes the role of a space marine, later unofficially referred to as Doomguy, fighting through hordes of undead humans and invading demons. The game begins on the moons of Mars and finishes in hell, with the player traversing each level to find its exit or defeat its final boss. It is an early example of 3D graphics in video games, and has enemies and objects as 2D images, a technique sometimes referred to as 2.5D graphics.");
        gamelijst.add(doom);

        Game starwars = new Game("starwars Battlefornt 2", 2005, "Shooter", 64, 15.99, 12, "Pandemic Studios" ,"Star Wars: Battlefront II is a 2005 first and third-person shooter video game based on the Star Wars film franchise. Developed by Pandemic Studios and published by LucasArts, it is a sequel to 2004's Star Wars: Battlefront and the second installment in the Star Wars: Battlefront series." );
        gamelijst.add(starwars);

        for (Game game : gamelijst) {
            review.readFile(game);
        }
        

        
    
    }

    public void writeGametofile(Game game) {
        String filename = "games.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(filename))) {
            String [] data = {String.valueOf(game.getNaam()), String.valueOf(game.getJaarRelease()), String.valueOf(game.getGenre()), String.valueOf(game.getPrijs()) };
            writer.writeNext(data);
            System.out.println();
            System.out.println("Game succesvol opgeslagen");
        }
        catch (IOException e) {
            System.err.println("Er is iets missgegaan " + e.getMessage());

        }
        
    }
    public void readGames() {


        String fileName = "games.csv";


        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null) {
                String[] allewaardes = line.split(",");
                if (allewaardes.length == 4) { // Ensure there are exactly three values in each line
                    String naam = allewaardes[0].replaceAll("\"", "");
                    int jaarrelease = Integer.parseInt(allewaardes[1].replaceAll("\"", ""));
                    String genre = allewaardes[2].replaceAll("\"", "");
                    Double prijs = Double.parseDouble(allewaardes[3].replaceAll("\"", ""));
                    // Set the ratings to the values read from the file
                    
                    Game game = new Game(naam, prijs, jaarrelease , genre);
                    

                } else {
                    System.err.println("Invalid data format in CSV file: " + fileName);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing integer from file: " + e.getMessage());
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                System.err.println("Error closing file: " + e.getMessage());
            }
        }
    }











    public ArrayList <Game> printGamesByRating() {
        ArrayList <Game> gamesoprating = new ArrayList<Game>();

        for (Game game : gamelijst) {
            gamesoprating.add(game);

        }
        Collections.sort(gamesoprating, Comparator.comparingDouble(Game::toonGegevens2).reversed());
        return gamesoprating;
    }
    

    public void printGamesByGenre(String genre) {
        // Print games sharing the specified genre
        for (Game game : gamelijst) {
            if (game.getGenre().equalsIgnoreCase(genre)) {
                System.out.println(game.getNaam() + " - Genre: " + game.getGenre());
            }
        }
    }

    public Game getGame (int index) {
        return (gamelijst.get(index));
    }

    public void printGamelijst() {
        for (int i = 0; i < gamelijst.size(); i++) {
            System.out.println( ( i+1) + " " + gamelijst.get(i).getNaam());
        }
    }


    //Dit is de methode om nieuwe games toe te voegen aan de lijst
    public void voegGameToe() {
        Game game = new Game();
        Scanner keyboard = new Scanner(System.in);
        System.out.println ("Welke game wilt u toevoegen?");
        String naam = keyboard.nextLine();
        game.setNaam(naam);

        System.out.println ("Wat is het releaseJaar?");
        int jaarRelease = keyboard.nextInt();
        game.setJaarRelease(jaarRelease);

        keyboard.nextLine(); // consume newline left-over

        System.out.println ("Wat is het genre?");
        String genre = keyboard.nextLine();
        game.setGenre(genre);

        System.out.println ("Hoeveel spelers zijn er?");
        int aantalSpelers = keyboard.nextInt();
        game.setAantalSpelers(aantalSpelers);

        System.out.println ("Wat is de prijs?");
        double prijs = keyboard.nextDouble();
        game.setPrijs(prijs);

        System.out.println ("Wat is de minimale leeftijd?");
        int minimaleLeeftijd = keyboard.nextInt();
        game.setMinimaleLeeftijd(minimaleLeeftijd);

        keyboard.nextLine(); // consume newline left-over

        System.out.println ("Wie is de maker?");
        String maker = keyboard.nextLine();
        game.setMaker(maker);

        System.out.println("Geef een beschrijving van de game");
        String beschrijving = keyboard.nextLine();
        game.setBeschrijving(beschrijving);

        gamelijst.add(game);
        keyboard.close();
    }

    public void verwijderGame (int gameIndex) {
        gamelijst.remove(gameIndex);
    }
}