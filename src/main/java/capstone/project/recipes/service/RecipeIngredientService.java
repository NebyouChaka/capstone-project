package capstone.project.recipes.service;


import capstone.project.recipes.database.dao.IngredientDAO;
import capstone.project.recipes.database.dao.RecipeDAO;
import capstone.project.recipes.database.dao.RecipeIngredientDAO;
import capstone.project.recipes.database.entity.Ingredient;
import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.RecipeIngredient;
import capstone.project.recipes.formbean.RecipeIngredientFormBean;
import capstone.project.recipes.security.AuthenticatedUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Autowired
    private AuthenticatedUserService authenticatedUserService;


    public RecipeIngredient saveOrUpdateIngredient(RecipeIngredientFormBean form) {
        RecipeIngredient ingredient;

        if (form.getRecipeId() == null) {
            ingredient = new RecipeIngredient();
        } else {
            Optional<RecipeIngredient> existingIngredient = recipeIngredientDAO.findById(form.getRecipeId());
            if (!existingIngredient.isPresent() || !existingIngredient.get().getRecipe().getId().equals(form.getRecipeId())) {
                return null;
            }
            ingredient = existingIngredient.get();
        }

        Recipe recipe = recipeDAO.findById(form.getRecipeId());

        ingredient.setRecipe(recipe);
        ingredient.setMeasurement(form.getMeasurement());
        ingredient.setQuantity(form.getQuantity());

        return recipeIngredientDAO.save(ingredient);
    }





}







