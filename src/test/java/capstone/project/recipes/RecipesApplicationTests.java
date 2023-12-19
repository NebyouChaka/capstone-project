package capstone.project.recipes;

import capstone.project.recipes.database.dao.RecipeIngredientDAO;
import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.RecipeIngredient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest
class RecipesApplicationTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private RecipeIngredientDAO recipeIngredientDAO;

	@Test
	public void testFindByRecipeId() {
		// Set up data
		Recipe testRecipe = new Recipe();
		// Set properties for recipe...
		testRecipe = entityManager.persistFlushFind(testRecipe);

		RecipeIngredient ingredient = new RecipeIngredient();
		ingredient.setRecipe(testRecipe);
		// Set other properties...
		entityManager.persist(ingredient);
		entityManager.flush();

		// Execute the test
		List<RecipeIngredient> result = recipeIngredientDAO.findByRecipeId(testRecipe.getId());
		assertFalse(result.isEmpty(), "Result should not be empty");
	}
}
