package capstone.project.recipes.controller;


import capstone.project.recipes.database.dao.RecipeDAO;
import capstone.project.recipes.database.dao.RecipeIngredientDAO;
import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.RecipeIngredient;
import capstone.project.recipes.database.entity.User;
import capstone.project.recipes.formbean.CreateRecipeFormBean;
import capstone.project.recipes.security.AuthenticatedUserService;
import capstone.project.recipes.service.RecipeService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeDAO recipeDAO;
    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @Autowired
    private RecipeIngredientDAO recipeIngredientDAO;

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


    // In RecipeController.java

    @GetMapping("/recipe/edit/{recipeId}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editRecipe(@PathVariable int recipeId) {
        User currentUser = authenticatedUserService.loadCurrentUser();
        Optional<Recipe> recipeOptional = recipeDAO.findById((long) recipeId);

        if (!recipeOptional.isPresent() || currentUser == null || !recipeOptional.get().getUser_id().equals(currentUser.getId())) {
            log.warn("Unauthorized access attempt for recipe ID: " + recipeId);
            return new ModelAndView("redirect:/error/404");
        }

        Recipe recipe = recipeOptional.get();
        CreateRecipeFormBean form = new CreateRecipeFormBean();
        // Populate the form with recipe data
        form.setId(recipe.getId());
        form.setName(recipe.getName());
        form.setDescription(recipe.getDescription());
        form.setImage_url(recipe.getImage_url());
        form.setCategory(recipe.getCategory());

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

        // Fetch ingredients for the recipe
        List<RecipeIngredient> ingredients = recipeIngredientDAO.findByRecipeId(id);

        response.addObject("recipe", recipe);
        response.addObject("ingredients", ingredients); // Add ingredients to the model
        return response;
    }


    @GetMapping("/recipe/category/{category}")
    public String viewRecipesByCategory(@PathVariable String category, Model model) {
        List<Recipe> recipes;
        if ("All".equalsIgnoreCase(category)) {
            // Fetch all recipes if the category is 'All'
            recipes = recipeDAO.findAll(); // Assuming findAll() fetches all recipes
        } else {
            // Fetch recipes for a specific category
            recipes = recipeDAO.getRecipesByCategory(category);
        }
        model.addAttribute("recipes", recipes);
        model.addAttribute("category", category);
        return "recipe/category"; // Return the path to your category.jsp
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

        return "redirect:/admin/index";
    }





}

