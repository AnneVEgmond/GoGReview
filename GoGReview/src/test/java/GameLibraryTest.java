import org.example.GameReview.GameLibrary;
import org.example.GameReview.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.ArrayList;

public class GameLibraryTest {
// Deze test kijkt of een game lijst die wordt gesorteerd niet null is en hiermee weten we dat er een lijst is met games
    @Test
    void testPrintGamesByRating() {
        
        GameLibrary gameLibrary = new GameLibrary();
        
        
        ArrayList<Game> sortedGames = gameLibrary.printGamesByRating();


        assertNotNull(sortedGames);
    }
}
