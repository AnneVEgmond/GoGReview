import org.example.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewTest {

    @Test
    public void testGetRatingGraphics() {
        Review review = new Review(1, 2, 3);
        Assertions.assertTrue(review.getRatingGraphics() == 1);
    }
}
