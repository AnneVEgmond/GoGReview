package org.example.GameReview;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.opencsv.CSVWriter;

import static org.example.Menu.ResourcePath;

public class GameLibrary {

    /**
     * Contains all {@code Game} objects which are stored in the games.csv
     * file.
     */
    private final ArrayList<Game> gamelijst = readGames();

    /**
     * Constructs a new GameLibrary. While doing this, it adds all
     * {@code Review} objects to the {@code Game} object they are associated
     * to.
     */
    public GameLibrary() {
        for (Game game : gamelijst) {
            Review review = new Review();
            review.readFile(game);
        }
    }

    /**
     * Adds the data of the given {@code Game} object to the games.csv file.
     *
     * @param game The {@code Game} object which will have its data written to
     *             the file.
     */
    public void writeGametofile(Game game) {
        String filename = ResourcePath + "/games.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(filename, true))) {
            String[] data = {String.valueOf(game.getNaam()), String.valueOf(game.getJaarRelease()), String.valueOf(game.getGenre()), String.valueOf(game.getPrijs()), String.valueOf(game.getSale()) };
            writer.writeNext(data);
            System.out.println();
            System.out.println("GameLijst succesvol bewerkt");
        } catch (IOException e) {
            System.err.println("Er is iets missgegaan " + e.getMessage());
        }
    }

    /**
     * Read all data stored in games.csv, put it into {@code Game} objects and
     * return them as an ArrayList.
     *
     * @return ArrayList with all {@code Game} objects from the games.csv file
     */
    public ArrayList<Game> readGames() {
        ArrayList<Game> games = new ArrayList<>();

        String fileName = "src/main/resources/games.csv";

        BufferedReader reader = null;
        String line;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null) {
                String[] allewaardes = line.split(",");
                if (allewaardes.length == 5) {
                    String naam = allewaardes[0].replaceAll("\"", "");
                    int jaarrelease = Integer.parseInt(allewaardes[1].replaceAll("\"", ""));
                    String genre = allewaardes[2].replaceAll("\"", "");
                    Double prijs = Double.parseDouble(allewaardes[3].replaceAll("\"", ""));
                    Boolean sale = Boolean.parseBoolean(allewaardes[4].replaceAll("\"", ""));
                    // Set the ratings to the values read from the file
                    
                    Game game = new Game(naam, prijs, jaarrelease, genre, sale);
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
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing file: " + e.getMessage());
            }
        }
        return games;
    }

    /**
     * Filter through all games in this {@code GameLibrary} and return only
     * the ones that are discounted.
     *
     * @return An ArrayList with all discounted games
     */
    public ArrayList<Game> printgamesmetkorting() {
        ArrayList<Game> gamesmetkorting = new ArrayList<>();
        for (Game game : gamelijst) {
            if (game.getSale()) {
                gamesmetkorting.add(game);
            }
        }
        return gamesmetkorting;
    }

    /**
     * Filters through all games in this {@code GameLibrary} and returns only the ones that have the same genre as the given input.
     *
     * @param genre The genre which you want to filter on
     * @return An ArrayList containing all games that have the given genre
     */
    public ArrayList<Game> printGamesByGenre(String genre) {
        Collections.sort(gamelijst, Comparator.comparingDouble(Game::calculateAverageRating).reversed());
        ArrayList<Game> gamesopgenre = new ArrayList<>();
        for (Game game : gamelijst) {
            if (game.getGenre().equalsIgnoreCase(genre)) {
                gamesopgenre.add(game);
            }
        }
        return gamesopgenre;
    }

    /**
     * Gives a game that corresponds with the given index.
     *
     * @param index Index of the game
     * @return The game with the given index
     */
    public Game getGame(int index) {
        return (gamelijst.get(index));
    }

    /**
     * Prints the names of all games in this {@code GameLibrary} in a numbered
     * list.
     */
    public void printGamelijst() {
        for (int i = 0; i < gamelijst.size(); i++) {
            System.out.println((i + 1) + " " + gamelijst.get(i).getNaam());
        }
    }

    /**
     * Returns a list of all games sorted by their average rating.
     *
     * @return ArrayList with games sorted by rating
     */
    public ArrayList<Game> printGamesByRating() {
        Collections.sort(gamelijst, Comparator.comparingDouble(Game::calculateAverageRating).reversed());
        ArrayList<Game> games = new ArrayList<>();

        for (Game game : gamelijst) {
            games.add(game);
        }
        return games;
    }

    /**
     * Gives the list of games of this {@code GameLibrary}
     *
     * @return The list of games of this {@code GameLibrary}
     */
    public ArrayList<Game> getGamelijst() {
        return gamelijst;
    }

    /**
     * Adds a {@code Game} object to the list of games of this
     * {@code GameLibrary} object.
     *
     * @param game The game to be added to the list
     */
    public void voegGameToe(Game game) {
        gamelijst.add(game);
        writeGametofile(game);
    }

    /**
     * Deletes the game on the given index from the list of games of this
     * {@code GameLibrary}. It also deletes the game from the games.csv file.
     *
     * @param gameIndex The index of the game to be deleted
     */
    public void verwijderGame(int gameIndex) {
        gamelijst.remove(gameIndex - 1);
        String temp = "temp.csv";
        String filepath = ResourcePath + "/games.csv";
        File oldfile = new File(filepath);
        File newfile = new File(temp);
        int line = 0;
        String currentLine;
        try {
            FileWriter fw = new FileWriter(temp);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            while ((currentLine = br.readLine()) != null) {
                line++;
                if (gameIndex != line) {
                    pw.println(currentLine);
                }
            }
            pw.flush();
            pw.close();
            fr.close();
            br.close();
            bw.close();
            fw.close();
            if (!oldfile.delete()) {
                System.out.println("Failed to delete the old file.");
                return;
            }
            File dump = new File(filepath);
            if (!newfile.renameTo(dump)) {
                System.out.println("Failed to rename the new file.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
