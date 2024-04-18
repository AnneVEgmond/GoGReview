package org.example.GameReview;

import java.util.ArrayList;

public class Game {

    private String naam;
    private int jaarRelease;
    private String genre;
    private boolean sale;
    private double gemiddeldeRating;
    private double prijs;
    private ArrayList<Review> reviews;

    public Game(String naam, Double prijs, int jaarRelease, String genre, boolean sale) {
        this.naam = naam;
        this.jaarRelease = jaarRelease;
        this.genre = genre;
        this.prijs = prijs;
        this.sale = sale;
        this.reviews = new ArrayList<>();
    }

    public Game() { }

    public Game(String naam, int jaarRelease, String genre) {
        this.naam = naam;
        this.jaarRelease = jaarRelease;
        this.genre = genre;
        this.reviews = new ArrayList<>();
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs; 
    }

    public void setJaarRelease(int jaarRelease) {
        this.jaarRelease = jaarRelease;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrijs() {
        return prijs;
    }

    public int getJaarRelease() {
        return jaarRelease;
    }

    public String getNaam() {
        return naam;
    }

    public String getGenre() {
        return genre;
    }

    public boolean getSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public void setRating(double rating) {
        this.gemiddeldeRating = rating;
    }

    public double getRating() {
        return gemiddeldeRating;
    }

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

    public void toonGegevens() {
        System.out.println();
        System.out.println(naam + " (" + jaarRelease + ")");
        System.out.println();
        System.out.printf("Rating: %.1f\n", calculateAverageRating());
        System.out.println();
        System.out.println("genre: " + genre);
    }
}

