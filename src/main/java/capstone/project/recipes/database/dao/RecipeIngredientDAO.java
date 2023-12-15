package capstone.project.recipes.database.dao;

import capstone.project.recipes.database.entity.Ingredient;
import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeIngredientDAO extends JpaRepository<RecipeIngredient, Integer> {

    List<RecipeIngredient> findByRecipeId(@Param("recipe") Integer recipe);
}


