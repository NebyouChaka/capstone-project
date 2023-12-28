package capstone.project.recipes.database.dao;

import capstone.project.recipes.database.entity.Favorite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
public class FavoriteDAOTest {

    private FavoriteDAO favoriteDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        favoriteDAO = mock(FavoriteDAO.class);
    }

    @Test
    public void testFindByUserId() {
        int userId = 1;
        Favorite favorite = new Favorite();
        favoriteDAO.save(favorite);

        List<Favorite> result = favoriteDAO.findByUserId(userId);

        assertEquals(1, result.size());
    }

    @Test
    public void testFindByUserIdAndRecipeId() {
        int userId = 1;
        int recipeId = 100;
        Favorite favorite = new Favorite();
        favoriteDAO.save(favorite);

        Optional<Favorite> result = favoriteDAO.findByUserIdAndRecipeId(userId, recipeId);

        assertTrue(result.isPresent()); // Check if the Optional is present
        assertEquals(userId, result.get().getUserId());
        assertEquals(recipeId, result.get().getRecipeId());
    }


    @Test
    public void testDeleteByUserIdAndRecipeId() {
        int userId = 1;
        int recipeId = 100;
        Favorite favorite = new Favorite();
        favoriteDAO.save(favorite);

        favoriteDAO.deleteByUserIdAndRecipeId(userId, recipeId);

        Optional<Favorite> deletedFavorite = favoriteDAO.findByUserIdAndRecipeId(userId, recipeId);
        assertFalse(deletedFavorite.isPresent());
    }
}
