package org.example.GameReview;

import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.example.Menu.ResourcePath;

public class Review {
    private int ratingGraphics;
    private int ratingStory;
    private int ratingGameplay;

    public Review(int ratingGraphics, int ratingStory, int ratingGameplay) {
        this.ratingGraphics = ratingGraphics;
        this.ratingStory = ratingStory;
        this.ratingGameplay = ratingGameplay;

    }

    public Review() { }

    public int getRatingGraphics() {
        return ratingGraphics;
    }

    public int getRatingStory() {
        return ratingStory;
    }

    public int getRatingGameplay() {
        return ratingGameplay;
    }

    public void setRatingGameplay(int ratingGameplay) {
        this.ratingGameplay = ratingGameplay;
    }

    public void setRatingStory(int ratingStory) {
        this.ratingStory = ratingStory;
    }

    public void setRatingGraphics(int ratingGraphics) {
        this.ratingGraphics = ratingGraphics;
    }

    /**
     * Deze method schrijft de data van deze review naar een file.
     *
     * @param gekozenGame Specificeerd voor welke game de review is
     */
    public void writeReviewToFile(Game gekozenGame) {
        String fileName = ResourcePath + "/Reviews/" + gekozenGame.getNaam() + "_reviews.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName, true))) {
            String[] data = {
                    String.valueOf(ratingGraphics),
                    String.valueOf(ratingStory),
                    String.valueOf(ratingGameplay)
            };
            writer.writeNext(data);
            System.out.println();
            System.out.println("Review is succesvol opgeslagen.");
        } catch (IOException e) {
            System.err.println("Er is iets missgegaan " + e.getMessage());
        }
    }

    public void readFile(Game gekozenGame) {
        String fileName = ResourcePath + "/Reviews/" + gekozenGame.getNaam() + "_reviews.csv";
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null) {
                String[] allewaardes = line.split(",");
                if (allewaardes.length == 3) { // Ensure there are exactly three values in each line
                    int ratingGraphics = Integer.parseInt(allewaardes[0].replaceAll("\"", ""));
                    int ratingStory = Integer.parseInt(allewaardes[1].replaceAll("\"", ""));
                    int ratingGameplay = Integer.parseInt(allewaardes[2].replaceAll("\"", ""));

                    // Set the ratings to the values read from the file
                    Review review = new Review(ratingGraphics, ratingStory, ratingGameplay);
                    gekozenGame.getReviews().add(review);

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
    }
}
