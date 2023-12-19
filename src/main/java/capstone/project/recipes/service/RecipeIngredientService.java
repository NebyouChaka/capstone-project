package capstone.project.recipes.service;


import capstone.project.recipes.database.dao.IngredientDAO;
import capstone.project.recipes.database.dao.RecipeDAO;
import capstone.project.recipes.database.dao.RecipeIngredientDAO;
import capstone.project.recipes.database.entity.Ingredient;
import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.RecipeIngredient;
import capstone.project.recipes.formbean.RecipeIngredientFormBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class RecipeIngredientService {

    @Autowired
    private RecipeIngredientDAO recipeIngredientDAO;

    @Autowired
    private RecipeDAO recipeDAO;

    @Autowired
    private IngredientDAO ingredientDAO;

    public void addIngredientToRecipe(Integer recipeId, Integer ingredientId, String measurement, Double quantity) {
        // Fetching the recipe
        Optional<Recipe> recipeOptional = recipeDAO.findById(recipeId.longValue());
        if (!recipeOptional.isPresent()) {
            log.error("Recipe not found for ID: " + recipeId);
            throw new RuntimeException("Recipe not found");
        }
        Recipe recipe = recipeOptional.get();

        // Fetching the ingredient
        Optional<Ingredient> ingredientOptional = ingredientDAO.findById(ingredientId.longValue());
        if (!ingredientOptional.isPresent()) {
            log.error("Ingredient not found for ID: " + ingredientId);
            throw new RuntimeException("Ingredient not found");
        }
        Ingredient ingredient = ingredientOptional.get();

        // Creating and saving the RecipeIngredient entity
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setMeasurement(measurement);
        recipeIngredient.setQuantity(quantity);

        recipeIngredientDAO.save(recipeIngredient);
        log.info("Ingredient added to recipe successfully");
    }
    public List<RecipeIngredient> getIngredientsByRecipeId(Integer recipeId) {
        return recipeIngredientDAO.findByRecipeId(recipeId);
    }

}







