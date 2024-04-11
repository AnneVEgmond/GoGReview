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
        gamelijst = readGames();
        Review review = new Review();
        readGames();

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
    public ArrayList<Game> readGames() {
        ArrayList<Game> games = new ArrayList<Game>();


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
                    games.add(game);



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
        return games;
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

        

        System.out.println ("Wat is het genre?");
        String genre = keyboard.nextLine();
        game.setGenre(genre);

        System.out.println ("Wat is de prijs?");
        double prijs = keyboard.nextDouble();
        game.setPrijs(prijs);

        gamelijst.add(game);
        writeGametofile(game);

        keyboard.close();
    }

    public void verwijderGame (int gameIndex) {
        gamelijst.remove(gameIndex-1);
    }
}