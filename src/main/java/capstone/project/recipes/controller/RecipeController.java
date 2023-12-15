package capstone.project.recipes.controller;


import capstone.project.recipes.database.dao.RecipeDAO;
import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.User;
import capstone.project.recipes.formbean.CreateRecipeFormBean;
import capstone.project.recipes.security.AuthenticatedUserService;
import capstone.project.recipes.service.RecipeService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeDAO recipeDAO;
    @Autowired
    private AuthenticatedUserService authenticatedUserService;


    @GetMapping("/recipe/search")
    public ModelAndView search(@RequestParam(required = false) String nameSearch) {
        ModelAndView response = new ModelAndView("recipe/search");

        log.debug("In the recipe search controller method: nameSearch = " + nameSearch);

        if (!StringUtils.isEmpty(nameSearch)) {
            response.addObject("nameSearch", nameSearch);

            nameSearch = "%" + nameSearch + "%";

            List<Recipe> recipes = recipeDAO.findByName(nameSearch);

            response.addObject("recipeVar", recipes);

            for (Recipe recipe : recipes) {
                log.debug("Recipe: id = " + recipe.getId() + " Name = " + recipe.getName());
            }
        }

        return response;
    }


    @GetMapping("/recipe/edit/{recipeId}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editRecipe(@PathVariable int recipeId) {
        // Fetch the currently logged-in user

        User currentUser = authenticatedUserService.loadCurrentUser();

        // Fetch the recipe
        Recipe recipe = recipeDAO.findById(recipeId);

        // Check if the recipe exists and belongs to the current user
        if (recipe == null || currentUser == null || !recipe.getUser_id().equals(currentUser.getId())) {
            log.warn("Unauthorized access attempt for recipe ID: " + recipeId);
            return new ModelAndView("redirect:/error/404"); // Redirect to an error or access denied page
        }

        // Populate the form for editing
        CreateRecipeFormBean form = new CreateRecipeFormBean();
        form.setId(recipe.getId());
        form.setName(recipe.getName());
        form.setDescription(recipe.getDescription());
        form.setImage_url(recipe.getImage_url());
        form.setCategory(recipe.getCategory());
        // Populate other fields as necessary

        ModelAndView response = new ModelAndView("recipe/create");
        response.addObject("form", form);
        return response;
    }

    @GetMapping("/recipe/create")
    public ModelAndView createRecipe() {
        ModelAndView response = new ModelAndView("recipe/create");

        log.debug("In create recipe with no args - log.debug");
        log.info("In create recipe with no args - log.info");

        return response;
    }

    @GetMapping("/recipe/createSubmit")
    public ModelAndView createRecipeSubmit(@Valid CreateRecipeFormBean form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("######################### In create recipe submit - has errors #########################");
            ModelAndView response = new ModelAndView("recipe/create");

            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info("error: " + error.getDefaultMessage());
            }

            response.addObject("form", form);
            response.addObject("errors", bindingResult);
            return response;
        }

        log.info("######################### In create recipe submit - no error found #########################");

        Recipe r = recipeService.createRecipe(form);

        ModelAndView response = new ModelAndView();
        response.setViewName("redirect:/recipe/edit/" + r.getId() + "?success=Recipe Saved Successfully");

        return response;
    }
    @RequestMapping("/recipe/detail")
    public ModelAndView viewRecipeDetail(@RequestParam Integer id) {
        ModelAndView response = new ModelAndView("recipe/detail");

        Recipe recipe = recipeDAO.findById(id);

        if (recipe == null) {
            log.warn("Recipe with ID " + id + " was not found");

            response.setViewName("redirect:/error/404");
            return response;
        }

        response.addObject("recipe", recipe);

        return response;
    }
    @GetMapping("/recipe/recipes")
    public ModelAndView viewAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        ModelAndView modelAndView = new ModelAndView("recipes");
        modelAndView.addObject("recipes", recipes);
        return modelAndView;
    }

    @GetMapping("/recipe/recipes/{category}")
    public ModelAndView viewRecipesByCategory(@PathVariable String category) {
        List<Recipe> recipes = recipeService.getRecipesByCategory(category);

        ModelAndView modelAndView = new ModelAndView("recipes");
        modelAndView.addObject("recipes", recipes);
        modelAndView.addObject("category", category); // Add the category to the model
        return modelAndView;
    }
    @GetMapping("/recipe/delete/{recipeId}")
    public String deleteRecipe(@PathVariable int recipeId) {
        log.info("Deleting recipe with ID: " + recipeId);
        Recipe recipe = recipeDAO.findById(recipeId);

        if (recipe != null) {
            recipeDAO.delete(recipe);
            log.info("Recipe deleted successfully.");
        } else {
            log.warn("Recipe with id " + recipeId + " was not found.");
        }

        return "redirect:/admin/index"; // Redirecting to the admin page or wherever appropriate
    }

}

