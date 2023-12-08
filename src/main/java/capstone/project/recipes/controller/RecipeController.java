package capstone.project.recipes.controller;


import capstone.project.recipes.database.dao.RecipeDAO;
import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.formbean.CreateRecipeFormBean;
import capstone.project.recipes.service.RecipeService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeDAO recipeDAO;

    @GetMapping("/recipe/search")
    public ModelAndView search(@RequestParam(required = false) String recipesNameSearch,
                               @RequestParam(required = false) String typeSearch) {
        ModelAndView response = new ModelAndView("recipe/search");

        log.debug("In the recipe search controller method: recipesName = " + recipesNameSearch + " type = " + typeSearch);

        if (!StringUtils.isEmpty(recipesNameSearch) || !StringUtils.isEmpty(typeSearch)) {
            response.addObject("recipesNameSearch", recipesNameSearch);
            response.addObject("typeSearch", typeSearch);

            if (!StringUtils.isEmpty(recipesNameSearch)) {
                recipesNameSearch = "%" + recipesNameSearch + "%";
            }

            if (!StringUtils.isEmpty(typeSearch)) {
                typeSearch = "%" + typeSearch + "%";
            }

            List<Recipe> recipes = recipeDAO.findByRecipesNameOrType(recipesNameSearch, typeSearch);

            response.addObject("recipeVar", recipes);

            for (Recipe recipe : recipes) {
                log.debug("Recipe: id = " + recipe.getId() + " recipesName = " + recipe.getRecipesName());
            }
        }

        return response;
    }

    @GetMapping("/recipe/edit/{recipeId}")
    public ModelAndView editRecipe(@PathVariable int recipeId, @RequestParam(required = false) String success) {
        log.info("######################### In /recipe/edit #########################");
        ModelAndView response = new ModelAndView("recipe/create");

        Recipe recipe = recipeDAO.findById(recipeId);

        if (!StringUtils.isEmpty(success)) {
            response.addObject("success", success);
        }

        CreateRecipeFormBean form = new CreateRecipeFormBean();

        if (recipe != null) {
            form.setId(recipe.getId());
            form.setRecipesName(recipe.getRecipesName());
            form.setType(recipe.getType());
            form.setImagesURL(recipe.getImagesURL());
        } else {
            log.warn("Recipe with id " + recipeId + " was not found");
        }

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
}
