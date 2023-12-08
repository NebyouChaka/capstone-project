package capstone.project.recipes.controller;


import capstone.project.recipes.database.entity.Favorite;
import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.repository.FavoriteRepository;
import capstone.project.recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {

    // Autowire the necessary repositories/services
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    // ... Other methods ...

    @GetMapping("/select")
    public String showRecipeSelection(Model model) {
        // Fetch all available recipes
        List<Recipe> availableRecipes = recipeRepository.findAll();
        model.addAttribute("recipes", availableRecipes);

        // Add a new favorite object to the model
        model.addAttribute("favorite", new Favorite());

        return "favorite/select";
    }

    @PostMapping("/save")
    public String saveFavorite(@ModelAttribute("favorite") Favorite favorite, @RequestParam("recipeIds") List<Integer> recipeIds, Principal principal) {
        // Get the current logged-in username
        String username = principal.getName();

        // Set the username for the favorite (assuming you have a setFavoriteId method in Favorite entity)
        favorite.setFavoriteId(favoriteId);

        // Fetch the selected recipes from the database
        List<Recipe> selectedRecipes = recipeRepository.findAllById(recipeIds);

        // Set the selected recipes for the favorite
        favorite.setRecipe(selectedRecipes);

        // Save the favorite
        favoriteRepository.save(favorite);

        return "redirect:/favorite/select?success=Favorite recipe saved successfully";
    }
}


