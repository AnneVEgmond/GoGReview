import org.example.GameReview.Game;
import org.example.GameReview.Review;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class RatingCalculatorTest {

    @Test
    void testCalculateAverageRating_NoReviews() {
        // Arrange: Create a game instance with an empty list of reviews
        Game game = new Game("Sample Game", 2022, "Action");

        // Act: Call the method under test
        double averageRating = game.calculateAverageRating();

        // Assert: Verify the result is 0.0 when there are no reviews
        assertEquals(0.0, averageRating);
    }

    @Test
    void testCalculateAverageRating_WithReviews() {
        // Arrange: Create a game instance with a list of reviews
        Game game = new Game("Sample Game", 2022, "Action");
        ArrayList<Review> reviews = new ArrayList<>();
        reviews.add(new Review(4, 3, 5)); // Graphics: 4, Story: 3, Gameplay: 5
        reviews.add(new Review(5, 5, 2)); // Graphics: 5, Story: 5, Gameplay: 2
        game.setReviews(reviews);

        // Act: Call the method under test
        double averageRating = game.calculateAverageRating();

        // Assert: Verify the correct average rating is calculated
        assertEquals(4.0, averageRating);
    }
}
