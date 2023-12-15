package capstone.project.recipes.controller;

import capstone.project.recipes.database.dao.IngredientDAO;
import capstone.project.recipes.database.dao.RecipeIngredientDAO;
import capstone.project.recipes.database.entity.Ingredient;
import capstone.project.recipes.database.entity.RecipeIngredient;
import capstone.project.recipes.service.RecipeIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j // Add this annotation to enable Lombok-generated logger
@Service
@Controller
@RequestMapping("/recipe")
public class RecipeIngredientController {

    @Autowired
    private IngredientDAO ingredientDAO;

    @Autowired
    private RecipeIngredientDAO recipeIngredientDAO;

    @GetMapping("/recipe/addIngredient")
    public ModelAndView addIngredientToRecipe(@RequestParam("recipeId") Integer recipeId) {
        ModelAndView response = new ModelAndView("recipe/ingredient");
        log.debug("In create addIngredient with no args - log.debug");
        log.info("In create addIngredient with no args - log.info");


        List<RecipeIngredient> ingredients = recipeIngredientDAO.findByRecipeId(recipeId);

        response.addObject("ingredients", ingredients);
        response.addObject("recipeId", recipeId);

        return response;
    }

    @PostMapping("/recipe/addIngredientSubmit")
    public ModelAndView addIngredientSubmit(@RequestParam("recipeId") Long recipeId,
                                            @RequestParam("ingredientIds") List<Long> ingredientIds,
                                            @RequestParam("measurement") String measurement) {

        // Process the form submission here
        // You can use the recipeId, ingredientIds, and measurement
        // to add ingredients to the recipe

        // Add your database insertion logic here
        // ...

        ModelAndView response = new ModelAndView();
        response.setViewName("redirect:/recipe/edit/" + recipeId + "?success=Ingredient(s) Added Successfully");

        return response;
    }
}