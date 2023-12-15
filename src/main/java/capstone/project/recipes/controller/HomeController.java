package capstone.project.recipes.controller;

import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class HomeController {

    private final RecipeService recipeService;

    public HomeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public ModelAndView showHomePage(@RequestParam(required = false) String success) {
        List<Recipe> recipes = recipeService.getAllRecipes(); // Fetch all recipes
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("recipes", recipes);
        if (success != null) {
            modelAndView.addObject("successMessage", success);
        }
        return modelAndView;
    }
}
