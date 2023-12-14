package capstone.project.recipes.controller;

import capstone.project.recipes.database.entity.UserFavorite;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.User;
import capstone.project.recipes.service.RecipeService;
import capstone.project.recipes.service.UserFavoriteService;
import capstone.project.recipes.service.UserService;

import java.util.List;


@Service
@RestController
@RequestMapping("/favorites")
public class UserFavoriteController {

    private static final Logger log = LoggerFactory.getLogger(UserFavoriteController.class);

    private final UserService userService;
    private final RecipeService recipeService;
    private final UserFavoriteService userFavoriteService;

    @Autowired
    public UserFavoriteController(UserService userService, RecipeService recipeService, UserFavoriteService userFavoriteService) {
        this.userService = userService;
        this.recipeService = recipeService;
        this.userFavoriteService = userFavoriteService;
    }

    @PostMapping("/recipe/favorites/addToFavorites")
    public ModelAndView addToFavorites(@RequestParam Long userId, @RequestParam Integer recipeId) {
        log.info("Attempting to add recipe with ID {} to user {}'s favorites", recipeId, userId);

        ModelAndView response = new ModelAndView("redirect:/userFavorite/?userId=" + userId);
        User user = userService.findById(userId);
        Recipe recipe = recipeService.findById(recipeId);

        if (user != null && recipe != null) {
            userFavoriteService.addFavorite(user, recipe);
            log.info("Successfully added recipe with ID {} to user {}'s favorites", recipeId, userId);
        } else {
            log.error("Failed to add recipe to favorites: User or Recipe not found");
        }

        return response;
    }

    @GetMapping("/recipe/favorites")
    public ModelAndView viewFavorites(@RequestParam Long userId) {
        log.info("Fetching favorite recipes for user with ID {}", userId);

        User user = userService.findById(userId);
        if (user == null) {
            log.error("User with ID {} not found", userId);
            return new ModelAndView("errorPage"); // Replace 'errorPage' with your actual error view name
        }

        List<Recipe> favoriteRecipes = userFavoriteService.findFavoritesByUser(user);
        ModelAndView modelAndView = new ModelAndView("userFavorite/favorites");
        modelAndView.addObject("user", user);
        modelAndView.addObject("favoriteRecipes", favoriteRecipes);

        log.info("Displayed favorite recipes for user with ID {}", userId);
        return modelAndView;
    }

    // Additional methods (update, delete, etc.) can be added here
    @PutMapping("/recipe/favorites/{favoriteId}")
    public ResponseEntity<?> updateFavorite(@PathVariable Integer favoriteId, @RequestBody Recipe updatedRecipeData) {
        log.info("Attempting to update favorite with ID {}", favoriteId);

        UserFavorite updatedFavorite = userFavoriteService.updateFavorite(favoriteId, updatedRecipeData);
        if (updatedFavorite != null) {
            log.info("Successfully updated favorite with ID {}", favoriteId);
            return ResponseEntity.ok("Favorite updated successfully");
        } else {
            log.error("Failed to update favorite with ID {}", favoriteId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Favorite not found");
        }
    }

    @DeleteMapping("/recipe/favorites/{favoriteId}")
    public ResponseEntity<?> deleteFavorite(@PathVariable Integer favoriteId) {
        log.info("Attempting to delete favorite with ID {}", favoriteId);

        boolean isDeleted = userFavoriteService.deleteFavorite(favoriteId);
        if (isDeleted) {
            log.info("Successfully deleted favorite with ID {}", favoriteId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            log.error("Failed to delete favorite with ID {}", favoriteId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Favorite not found");
        }
    }
}



