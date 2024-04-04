package org.example;

import java.util.ArrayList;

public class Game {

    // variables
    private String naam;
    private int jaarRelease;
    private String genre;
    private int aantalSpelers;
    private double gemiddeldeRating;
    private double prijs;
    private int minimaleLeeftijd;
    private String maker;
    private String beschrijving;
    private ArrayList<Review> reviews;



    //constructors
    public Game(String naam, int jaarRelease, String genre, int aantalSpelers, double prijs, int minimaleLeeftijd, String maker, String beschrijving) {
        this.naam = naam;
        this.jaarRelease = jaarRelease;
        this.genre = genre;
        this.aantalSpelers = aantalSpelers;
        this.prijs = prijs;
        this.minimaleLeeftijd = minimaleLeeftijd;
        this.maker = maker;
        this.beschrijving = beschrijving;
        this.reviews = new ArrayList<>(); // Initialize ArrayList


    }

    public Game () {

    }




    //SETTERS AND GETTERS

    public void setMinimaleLeeftijd(int minimaleLeeftijd) {
        this.minimaleLeeftijd = minimaleLeeftijd;
    }


    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }


    public void setAantalSpelers(int aantalSpelers) {
        this.aantalSpelers = aantalSpelers;
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

    public void setMaker(String maker) {
        this.maker = maker;
    }


    public void setBeschrijving (String bescrhijving) {
        this.beschrijving = bescrhijving;
    }

    public int getMinimaleLeeftijd() {
        return minimaleLeeftijd;
    }

    public double getPrijs() {
        return prijs;
    }

    public int getAantalSpelers() {
        return aantalSpelers;
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

    public String getMaker() {
        return maker;
    }

    public String getBeschrijving() {
        return beschrijving;
    }




    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
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

        return totalRating / numReviews;
    }



    public void toonGegevens() {
        System.out.println();
        System.out.println(naam + " (" + jaarRelease + ")");
        System.out.println();
        System.out.println("Minimale leeftijd: " + minimaleLeeftijd);
        System.out.println();
        System.out.println("Maker: " + maker);
        System.out.println();
        System.out.printf("Rating: %.1f\n",calculateAverageRating());
        System.out.println();
        System.out.println("genre: " + genre);
        System.out.println();
        System.out.println("Beschrijving: ");
        System.out.println(beschrijving);
    }
}