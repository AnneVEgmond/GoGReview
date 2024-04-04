package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class GameLibrary {

    ArrayList<Game> gamelijst = new ArrayList<Game>();

    public GameLibrary() {
        Game gta3= new Game ("Grand Theft auto 3", 2001, "Action", 1, 60.0, 18, "Rockstar Games", "Welcome to Liberty City. Where it all began. The critically acclaimed blockbuster Grand Theft Auto III brings to life the dark and seedy underworld of Liberty City. With a massive and diverse open world, a wild cast of characters from every walk of life, and the freedom to explore at will, Grand Theft Auto III puts the dark, intriguing, and ruthless world of crime at your fingertips." );
        gamelijst.add(gta3);

        Game doom = new Game ("Doom", 1993, "Action", 1, 60.0, 18, "Bethesda Softworks", "Doom is a first-person shooter game developed and published by id Software. Released on December 10, 1993, for DOS, it is the first installment in the Doom franchise. The player assumes the role of a space marine, later unofficially referred to as Doomguy, fighting through hordes of undead humans and invading demons. The game begins on the moons of Mars and finishes in hell, with the player traversing each level to find its exit or defeat its final boss. It is an early example of 3D graphics in video games, and has enemies and objects as 2D images, a technique sometimes referred to as 2.5D graphics.");
        gamelijst.add(doom);
    }

    /*public void printGamesByRating() {
        // Sort the games by rating
        Collections.sort(gamelijst, Comparator.comparingDouble(Game::getRating).reversed());
        // Print the sorted list
        for (Game game : gamelijst) {
            System.out.println(game.getNaam() + " - Rating: " + game.getRating());
        }
    }
     */

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
    }

    public void verwijderGame (int gameIndex) {
        gamelijst.remove(gameIndex);
    }
}