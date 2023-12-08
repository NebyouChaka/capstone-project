package capstone.project.recipes.service;
import capstone.project.recipes.database.dao.RecipeDAO;
import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.formbean.CreateRecipeFormBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class RecipeService {

    @Autowired
    private RecipeDAO recipeDAO;

    public Recipe createRecipe(CreateRecipeFormBean form) {
        log.debug("id: " + form.getId());
        log.debug("recipesName: " + form.getRecipesName());
        log.info("type: " + form.getType());
        log.info("images: " + form.getImagesURL());

        // if the form.id is null then this is a create - if it is not null then it is an edit
        // first, we attempt to load it from the database (basically, we assume that it is going to be an edit)
        Recipe recipe = recipeDAO.findById(form.getId());

        if (recipe == null) {
            recipe = new Recipe();
        }


            // set the incoming values to be saved to the database
            recipe.setRecipesName(form.getRecipesName());
            recipe.setType(form.getType());
            recipe.setImagesURL(form.getImagesURL());

            return recipeDAO.save(recipe);

    }
}
