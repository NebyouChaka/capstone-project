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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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


    @GetMapping("/recipe/edit/{recipeId}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editRecipe(@PathVariable int recipeId) {
        // Fetch the currently logged-in user

        User currentUser = authenticatedUserService.loadCurrentUser();

        // Fetch the recipe
        Recipe recipe = recipeDAO.findById(recipeId);

        log.debug("CurrentUser: {}", currentUser);
        log.debug("Recipe: {}", recipe);
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

        // Fetch ingredients for the recipe
        List<RecipeIngredient> ingredients = recipeIngredientDAO.findByRecipeId(id);

        response.addObject("recipe", recipe);
        response.addObject("ingredients", ingredients); // Add ingredients to the model
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
        modelAndView.addObject("category", category);
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

        return "redirect:/admin/index";
    }
    @GetMapping("/recipe/fileupload")
    public ModelAndView recipeFileUpload(@RequestParam Long id) {
        ModelAndView response = new ModelAndView("recipe/fileupload");

        Recipe recipe = recipeDAO.findById(id).orElse(null);
        response.addObject("recipe", recipe);

        log.info("In fileupload with no Args");
        return response;
    }

    @PostMapping("/recipe/fileUploadSubmit")
    public ModelAndView recipeFileUploadSubmit(@RequestParam("file") MultipartFile file,
                                               @RequestParam Long id) {
        ModelAndView response = new ModelAndView("redirect:/recipe/detail?id=" + id);

        log.info("Filename = " + file.getOriginalFilename());
        log.info("Size     = " + file.getSize());
        log.info("Type     = " + file.getContentType());

        // Get the file and save it somewhere
        File f = new File("./src/main/webapp/pub/images/" + file.getOriginalFilename());
        try (OutputStream outputStream = new FileOutputStream(f.getAbsolutePath())) {
            IOUtils.copy(file.getInputStream(), outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Recipe recipe = recipeDAO.findById(id).orElse(null);
        if (recipe != null) {
            recipe.setImage_url("/pub/images/" + file.getOriginalFilename());
            recipeDAO.save(recipe);
        }

        return response;
    }


}

