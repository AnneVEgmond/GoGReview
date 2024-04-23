package org.example.GameReview;

import java.util.ArrayList;

public class Game {

    /**
     * Name of this {@code Game}.
     */
    private String naam;

    /**
     * The year the {@code Game} is released.
     */
    private int jaarRelease;

    /**
     * The genre the {@code Game} belongs to.
     */
    private String genre;

    /**
     * Whether the {@code Game} is on sale or not.
     */
    private boolean sale;

    /**
     * Contains the average rating of a game.
     */
    private double gemiddeldeRating;

    /**
     * Contains the price of the {@code Game}.
     */
    private double prijs;

    /**
     * Contains all {@code Review} objects associated with this {@code Game}.
     */
    private ArrayList<Review> reviews;

    /**
     * Constructs a new, empty {@code Game}.
     */
    public Game() {
        this.reviews = new ArrayList<>();
    }

    /**
     * Constructs a new {@code Game}.
     *
     * @param naam The name of this {@code Game}
     * @param jaarRelease The release year of this {@code Game}
     * @param genre The genre of this {@code Game}
     */
    public Game(String naam, int jaarRelease, String genre) {
        this();
        this.naam = naam;
        this.jaarRelease = jaarRelease;
        this.genre = genre;
    }

    /**
     * Constructs a new {@code Game}.
     *
     * @param naam The name of this {@code Game}
     * @param prijs The price of this {@code Game}
     * @param jaarRelease The release year of this {@code Game}
     * @param genre The genre of this {@code Game}
     * @param sale Whether this {@code Game} is on sale or not
     */
    public Game(String naam, Double prijs, int jaarRelease, String genre, boolean sale) {
        this(naam, jaarRelease, genre);
        this.prijs = prijs;
        this.sale = sale;
    }

    /**
     * Gives the name of this {@code Game}.
     *
     * @return The name of this {@code Game}
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Gives the release year of this {@code Game}.
     *
     * @return The release year of this {@code Game}
     */
    public int getJaarRelease() {
        return jaarRelease;
    }

    /**
     * Gives the genre of this {@code Game}.
     *
     * @return The genre of this {@code Game}
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Gives the sale of this {@code Game}.
     *
     * @return The sale of this {@code Game}
     */
    public boolean getSale() {
        return sale;
    }

    /**
     * Gives the average rating of this {@code Game}.
     *
     * @return The average rating of this {@code Game}
     */
    public double getRating() {
        return gemiddeldeRating;
    }

    /**
     * Gives the price of this {@code Game}.
     *
     * @return The price of this {@code Game}
     */
    public double getPrijs() {
        return prijs;
    }

    /**
     * Gives the reviews of this {@code Game}.
     *
     * @return The reviews of this {@code Game}
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * Change the name of this {@code Game} to the given String.
     *
     * @param naam The new name of this {@code Game}
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * Change the release year of this {@code Game} to the given int.
     *
     * @param jaarRelease The new release year of this {@code Game}
     */
    public void setJaarRelease(int jaarRelease) {
        this.jaarRelease = jaarRelease;
    }

    /**
     * Change the genre of this {@code Game} to the given String.
     *
     * @param genre The new genre of this {@code Game}
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Change the sale status of this {@code Game} to the given boolean.
     *
     * @param sale The new status of the sale of this {@code Game}
     */
    public void setSale(boolean sale) {
        this.sale = sale;
    }

    /**
     * Change the average rating of this {@code Game} to the given double.
     *
     * @param rating The new average rating of this {@code Game}
     */
    public void setRating(double rating) {
        this.gemiddeldeRating = rating;
    }

    /**
     * Change the price of this {@code Game} to the given double.
     *
     * @param prijs The new price of this {@code Game}
     */
    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    /**
     * Change the collection of reviews of this {@code Game} to the given
     * ArrayList.
     *
     * @param reviews A new collection of {@code Review} objects
     */
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Calculates the average of all rating categories amoung all
     * {@code Review} objects associated to this {@code Game}.
     * @return The average rating
     */
    public double calculateAverageRating() {
        double totalRating = 0.0;
        int numReviews = reviews.size();

        if (numReviews == 0) {
            return 0.0; // Return 0 if there are no reviews
        }

        for (Review review : reviews) {
            totalRating += (review.getRatingGraphics() + review.getRatingStory() + review.getRatingGameplay()) / 3.0;
        }

        setRating(totalRating / numReviews);
        return totalRating / numReviews;
    }

    /**
     * Print out the data of this {@code Game} object.
     */
    public void toonGegevens() {
        System.out.println();
        System.out.println(naam + " (" + jaarRelease + ")");
        System.out.println();
        System.out.printf("Rating: %.1f\n", calculateAverageRating());
        System.out.println();
        System.out.println("genre: " + genre);
    }
}

