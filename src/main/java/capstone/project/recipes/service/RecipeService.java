package capstone.project.recipes.service;
import capstone.project.recipes.database.dao.RecipeDAO;
import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.User;
import capstone.project.recipes.formbean.CreateRecipeFormBean;
import capstone.project.recipes.security.AuthenticatedUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Service
public class RecipeService {

    @Autowired
    private RecipeDAO recipeDAO;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    public Recipe createRecipe(CreateRecipeFormBean form) {
        log.debug("id: " + form.getId());
        log.debug("name: " + form.getName());
        log.info("description: " + form.getDescription());
        log.info("image_url: " + form.getImage_url());

        // if the form.id is null then this is a create - if it is not null then it is an edit
        // first, we attempt to load it from the database (basically, we assume that it is going to be an edit)
        Recipe recipe = recipeDAO.findById(form.getId());

        if (recipe == null) {
            recipe = new Recipe();


            User user = authenticatedUserService.loadCurrentUser();
             recipe.setUser_id(user.getId());
        }
            // set the incoming values to be saved to the database
            recipe.setName(form.getName());
            recipe.setDescription(form.getDescription());
            recipe.setImage_url(form.getImage_url());

        log.debug("Before saving recipe to the database");
        recipe = recipeDAO.save(recipe);
        log.debug("After saving recipe to the database. Recipe ID: " + recipe.getId());
        return recipeDAO.save(recipe);


    }
}
