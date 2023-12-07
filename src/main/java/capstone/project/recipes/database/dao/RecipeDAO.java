package capstone.project.recipes.database.dao;


import capstone.project.recipes.database.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeDAO extends JpaRepository<Recipe, Long> {
    List<Recipe> findByRecipesNameOrType(String recipesName, String type);
    // You can add more custom query methods as needed

    // Example: Find a recipe by its ID
    Recipe findByRecipeId(Long recipeId);

    @Query("SELECT r FROM Recipe r WHERE r.recipesName LIKE :recipesName or r.type LIKE :type")
    List<Recipe> findByFirstNameOrLastName(String recipesName, String type);
}

