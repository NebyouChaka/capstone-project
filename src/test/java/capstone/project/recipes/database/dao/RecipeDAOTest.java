package capstone.project.recipes.database.dao;
import capstone.project.recipes.database.entity.Recipe;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecipeDAOTest {

    @Autowired
    private RecipeDAO recipeDAO;

    @Test
    @Order(1)
    public void createRecipeTest() {
        // given
        Recipe recipe = new Recipe();
        recipe.setName("Test Recipe");
        recipe.setDescription("Test Description");
        recipe.setImage_url("Test Image URL");
        recipe.setCategory("Test Category");
        recipe.setUser_id(1);  // Assuming a user with ID 1 exists

        // when
        recipe = recipeDAO.save(recipe);

        // then
        Assertions.assertNotNull(recipe.getId());
        Assertions.assertEquals("Test Recipe", recipe.getName());
        Assertions.assertEquals("Test Description", recipe.getDescription());
        Assertions.assertEquals("Test Image URL", recipe.getImage_url());
        Assertions.assertEquals("Test Category", recipe.getCategory());
        Assertions.assertEquals(1, recipe.getUser_id());
    }

    @Test
    @Order(2)
    public void findByNameTest() {
        // given
        String name = "Test Recipe";

        // when
        List<Recipe> recipes = recipeDAO.findByName(name);

        // then
        Assertions.assertFalse(recipes.isEmpty());
        Recipe foundRecipe = recipes.get(0);
        Assertions.assertEquals("Test Recipe", foundRecipe.getName());
    }

    @Test
    @Order(3)
    public void findByCategoryTest() {
        // given
        String category = "Test Category";

        // when
        List<Recipe> recipes = recipeDAO.getRecipesByCategory(category);

        // then
        Assertions.assertFalse(recipes.isEmpty());
        for (Recipe recipe : recipes) {
            Assertions.assertEquals("Test Category", recipe.getCategory());
        }
    }

    @Test
    @Order(4)
    public void deleteRecipeTest() {
        // given
        String name = "Test Recipe";

        // when
        List<Recipe> recipes = recipeDAO.findByName(name);
        recipes.forEach(recipe -> recipeDAO.delete(recipe));

        // then
        List<Recipe> deletedRecipes = recipeDAO.findByName(name);
        Assertions.assertTrue(deletedRecipes.isEmpty());
    }
}
