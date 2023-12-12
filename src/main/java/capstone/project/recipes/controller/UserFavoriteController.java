package capstone.project.recipes.controller;

import capstone.project.recipes.database.dao.RecipeDAO;
import capstone.project.recipes.database.dao.UserFavoriteDAO;
import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.User;
import capstone.project.recipes.database.entity.UserFavorite;
import capstone.project.recipes.security.AuthenticatedUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class UserFavoriteController {

    @Autowired
    private UserFavoriteDAO userFavoriteDAO;

    @Autowired
    private RecipeDAO recipeDAO;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @PostMapping("/recipe/favorite/{recipeId}")
    public ResponseEntity<String> addFavorite(@PathVariable int recipeId) {
        User user = authenticatedUserService.loadCurrentUser();
        Recipe recipe = recipeDAO.findById(recipeId);

        if (user != null && recipe != null) {
            UserFavorite userFavorite = new UserFavorite();
            userFavorite.setUser(user);
            userFavorite.setRecipe(recipe);

            userFavoriteDAO.save(userFavorite);

            return ResponseEntity.ok("Recipe added to favorites");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or recipe not found");
        }
    }

    @GetMapping("/userFavorite/favorite")
    public ModelAndView viewFavorites() {
        User user = authenticatedUserService.loadCurrentUser();
        List<UserFavorite> userFavorites = userFavoriteDAO.findByUser(user);

        ModelAndView response = new ModelAndView("userFavorite/favorite");
        response.addObject("userFavorites", userFavorites);

        return response;
    }
}

