import org.example.GameReview.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewTest {
// hier wordt gekeken of een speciefiek criteria de juiste rating terug krijgt als er om wordt gevraagd
    @Test
    public void testGetRatingGraphics() {
        Review review = new Review(1, 2, 3);
        Assertions.assertTrue(review.getRatingGraphics() == 1);
    }
}
