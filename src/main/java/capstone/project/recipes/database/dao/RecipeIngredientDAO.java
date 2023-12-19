package capstone.project.recipes.database.dao;

import capstone.project.recipes.database.entity.Ingredient;
import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeIngredientDAO extends JpaRepository<RecipeIngredient, Long> {
    @Query("SELECT ri FROM RecipeIngredient ri WHERE ri.recipe.id = :recipe_id")
    List<RecipeIngredient> findByRecipeId(@Param("recipe_id") Integer recipeId);



}


