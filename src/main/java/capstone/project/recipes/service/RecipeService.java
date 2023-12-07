package capstone.project.recipes.service;

import capstone.project.recipes.database.dao.RecipeDAO;
import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.UserRole;
import capstone.project.recipes.formbean.CreateRecipeFormBean;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RecipeService {

    @Autowired
    private RecipeDAO recipeDAO;

    @Autowired
    private UserRole userRoleDao;

    public Recipe createRecipe(CreateRecipeFormBean form) {
        log.debug("id: " + form.getId());
        log.debug("recipesName: " + form.getRecipesName());
        log.info("type: " + form.getType());
        log.info("images: " + form.getImages());

        // if the form.id is null then this is a create - if it is not null then it is an edit
        // first we attempt to load it from the database ( basically we assume that it is going to be an edit )
        // if it was found in the database we know the incoming id was valid so we can edit it
        Recipe recipe = recipeDAO.findById(form.getId());

        // if the recipe is null then we know that this is a create and we have to make a new object
        if (recipe == null) {
            recipe = new Recipe();
        }

        // set the incoming values to be save to the database
        recipe.setRecipesName(form.getRecipesName());
        recipe.setType(form.getType());
        recipe.setImages(form.getImages());

        return recipeDAO.save(recipe);
    }
}
