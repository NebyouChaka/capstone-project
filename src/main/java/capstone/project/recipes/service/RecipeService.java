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

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RecipeService {

    @Autowired
    private RecipeDAO recipeDAO;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    public Recipe createRecipe(CreateRecipeFormBean form) {
        Recipe recipe;

        if (form.getId() == null) {
            recipe = new Recipe();
            User user = authenticatedUserService.loadCurrentUser();
            if (user == null) {
                // Handle the case where the user is not authenticated
                return null;
            }
            recipe.setUser_id(user.getId());
        } else {
            recipe = recipeDAO.findById(form.getId());
            if (recipe == null || !recipe.getUser_id().equals(authenticatedUserService.loadCurrentUser().getId())) {
                // If the recipe doesn't exist or doesn't belong to the current user
                return null;
            }
        }

        recipe.setName(form.getName());
        recipe.setDescription(form.getDescription());
        recipe.setImage_url(form.getImage_url());
        recipe.setCategory(form.getCategory());
        return recipeDAO.save(recipe);
    }



    public List<Recipe> getAllRecipes() {
            return recipeDAO.findAll(); // Implement this method in your DAO
        }

        public List<Recipe> getRecipesByCategory(String category) {
            return recipeDAO.getRecipesByCategory(category); // Implement this method in your DAO

        }
    public Recipe findById(int recipeId) {
        return recipeDAO.findById(recipeId);
    }

}
