import org.example.GameReview.GameLibrary;
import org.example.GameReview.Game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;

public class GameLibraryTest {

    @Test
    void testPrintGamesByRating() {
        // Arrange: Create a game library instance
        GameLibrary gameLibrary = new GameLibrary();
        
        // Act: Call the method under test
        ArrayList<Game> sortedGames = gameLibrary.printGamesByRating();
        
        // Assert: Verify that the returned list is not null
        assertTrue(sortedGames != null);
    }
}
