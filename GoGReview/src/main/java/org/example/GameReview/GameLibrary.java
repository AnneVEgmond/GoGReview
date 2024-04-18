package org.example.GameReview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.opencsv.CSVWriter;

import java.io.*;

import static org.example.Menu.ResourcePath;

public class GameLibrary {

    private ArrayList<Game> gamelijst = readGames();
    private Review review = new Review();

    public GameLibrary() {
        for (Game game : gamelijst) {
            review.readFile(game);
        }
    }

    public void writeGametofile(Game game) {
        String filename = ResourcePath + "/games.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(filename, true))) {
            String [] data = {String.valueOf(game.getNaam()), String.valueOf(game.getJaarRelease()), String.valueOf(game.getGenre()), String.valueOf(game.getPrijs()), String.valueOf(game.getSale()) };
            writer.writeNext(data);
            System.out.println();
            System.out.println("GameLijst succesvol bewerkt");
        }
        catch (IOException e) {
            System.err.println("Er is iets missgegaan " + e.getMessage());
        }
    }

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
                    
                    Game game = new Game(naam, prijs, jaarrelease , genre, sale);
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

    public ArrayList<Game> printgamesmetkorting() {
        ArrayList<Game> gamesmetkorting = new ArrayList<>();
        for (Game game : gamelijst) {
            if(game.getSale()) {
                gamesmetkorting.add (game);
            }
        }
        return gamesmetkorting;
    }

    public ArrayList<Game> printGamesByGenre(String genre) {
        
        Collections.sort(gamelijst, Comparator.comparingDouble(Game::calculateAverageRating).reversed());
        ArrayList<Game> gamesopgenre = new ArrayList<Game>();
        for (Game game : gamelijst) {
            if (game.getGenre().equalsIgnoreCase(genre)) {
                gamesopgenre.add(game);
            }
        }
        return gamesopgenre;
    }

    public Game getGame (int index) {
        return (gamelijst.get(index));
    }

    public void printGamelijst() {
        
        for (int i = 0; i < gamelijst.size(); i++) {
            System.out.println( ( i+1) + " " + gamelijst.get(i).getNaam());
        }
    }

    public ArrayList <Game> printGamesByRating() {
        Collections.sort(gamelijst, Comparator.comparingDouble(Game::calculateAverageRating).reversed());
        ArrayList <Game> games = new ArrayList<>();

        for (Game game : gamelijst) {
            games.add(game);
        }
        return games;
    }

    public ArrayList<Game> getGamelijst() {
        return gamelijst;
    }

    public void voegGameToe(Game game) {
        gamelijst.add(game);
        writeGametofile(game);
    }

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
