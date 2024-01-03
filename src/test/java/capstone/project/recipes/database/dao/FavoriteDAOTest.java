package capstone.project.recipes.database.dao;

import capstone.project.recipes.database.entity.Favorite;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FavoriteDAOTest {

    @Autowired
    private FavoriteDAO favoriteDAO;

    @Test
    @Order(1)
    public void findFavoriteByUserIdAndRecipeIdTest() {
        // given
        int userId = 1; // Replace with a valid user ID
        int recipeId = 21; // Replace with a valid recipe ID

        // when
        Optional<Favorite> favorite = favoriteDAO.findByUserIdAndRecipeId(userId, recipeId);

        // then
        Assertions.assertTrue(favorite.isPresent());
        Assertions.assertEquals(userId, favorite.get().getUserId());
        Assertions.assertEquals(recipeId, favorite.get().getRecipeId());
    }

    @Test
    @Order(2)
    public void findByUserIdTest() {
        // given
        int userId = 1; // Replace with a valid user ID

        // when
        List<Favorite> favorites = favoriteDAO.findByUserId(userId);

        // then
        Assertions.assertFalse(favorites.isEmpty());
        for (Favorite fav : favorites) {
            Assertions.assertEquals(userId, fav.getUserId());
        }
    }

    @Test
    @Order(3)
    public void deleteFavoriteByUserIdAndRecipeIdTest() {
        // given
        int userId = 1; // Replace with a valid user ID
        int recipeId = 1; // Replace with a valid recipe ID

        // when
        favoriteDAO.deleteByUserIdAndRecipeId(userId, recipeId);
        Optional<Favorite> deletedFavorite = favoriteDAO.findByUserIdAndRecipeId(userId, recipeId);

        // then
        Assertions.assertFalse(deletedFavorite.isPresent());
    }

    @Test
    @Order(4)
    public void shouldNotExistTest() {
        // given
        int userId = 1; // Replace with a valid user ID
        int recipeId = 1; // Replace with a valid recipe ID

        // when
        Optional<Favorite> favorite = favoriteDAO.findByUserIdAndRecipeId(userId, recipeId);

        // then
        Assertions.assertFalse(favorite.isPresent());
    }
}

