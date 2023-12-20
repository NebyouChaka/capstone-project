package capstone.project.recipes.controller;

import capstone.project.recipes.database.dao.IngredientDAO;
import capstone.project.recipes.database.dao.RecipeIngredientDAO;
import capstone.project.recipes.database.entity.Ingredient;
import capstone.project.recipes.database.entity.RecipeIngredient;
import capstone.project.recipes.formbean.RecipeIngredientFormBean;
import capstone.project.recipes.service.RecipeIngredientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeIngredientController {

    @Autowired
    private IngredientDAO ingredientDAO;

    @Autowired
    private RecipeIngredientDAO recipeIngredientDAO;

    @Autowired
    private RecipeIngredientService recipeIngredientService;

    @GetMapping("/recipe/addIngredient")
    public ModelAndView addIngredientToRecipe(@RequestParam("recipeId") Integer recipeId) {
        ModelAndView modelAndView = new ModelAndView("addIngredient");
        List<RecipeIngredient> currentIngredients = recipeIngredientDAO.findByRecipeId(recipeId);
        List<Ingredient> allIngredients = ingredientDAO.findAll();

        modelAndView.addObject("currentIngredients", currentIngredients);
        modelAndView.addObject("allIngredients", allIngredients);
        modelAndView.addObject("recipeId", recipeId);

        return modelAndView;
    }

    @PostMapping("/addIngredientSubmit")
    public ModelAndView addIngredientSubmit(@RequestParam("recipeId") Integer recipeId,
                                            @RequestParam("ingredientName") String[] ingredientNames,
                                            @RequestParam("measurement") String[] measurements,
                                            @RequestParam("quantity") Double[] quantities,
                                            RedirectAttributes redirectAttributes) {
        // Process each set of ingredient data
        for (int i = 0; i < ingredientNames.length; i++) {
            String ingredientName = ingredientNames[i];
            String measurement = measurements[i];
            Double quantity = quantities[i];

            // Implement logic to process each ingredient
            // Example: Check if ingredient exists, create if not, and then add to recipe
            List<Ingredient> foundIngredients = ingredientDAO.findByName(ingredientName);
            Ingredient ingredient;
            if (foundIngredients.isEmpty()) {
                // Create new Ingredient if it does not exist
                ingredient = new Ingredient();
                ingredient.setName(ingredientName);
                ingredient = ingredientDAO.save(ingredient);
            } else {
                // Use the existing ingredient
                ingredient = foundIngredients.get(0); // Assuming first match is taken
            }

            // Now add ingredient to the recipe
            recipeIngredientService.addIngredientToRecipe(recipeId, ingredient.getId(), measurement, quantity);
        }

        redirectAttributes.addFlashAttribute("success", "Ingredients added successfully!");
        return new ModelAndView("redirect:/recipe/addIngredient?recipeId=" + recipeId);
    }

}
