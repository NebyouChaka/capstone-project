package capstone.project.recipes.controller;


import capstone.project.recipes.database.entity.Favorite;
import capstone.project.recipes.database.entity.User;
import capstone.project.recipes.security.AuthenticatedUserService;
import capstone.project.recipes.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @GetMapping("/favorites")
    public ModelAndView viewFavorites() {
        User currentUser = authenticatedUserService.loadCurrentUser();
        int userId = currentUser.getId();

        List<Favorite> favorites = favoriteService.getFavoritesByUser(userId);

        ModelAndView modelAndView = new ModelAndView("recipe/favorites");
        modelAndView.addObject("favorites", favorites);
        return modelAndView;
    }

    @PostMapping("/addFavorite")
    public String addFavorite(@RequestParam("recipeId") int recipeId) {
        User currentUser = authenticatedUserService.loadCurrentUser();
        int userId = currentUser.getId();
        favoriteService.addFavorite(userId, recipeId);
        return "redirect:/favorites";
    }

    @GetMapping("/removeFavorite")
    public String removeFavorite(@RequestParam("recipeId") int recipeId) {
        User currentUser = authenticatedUserService.loadCurrentUser();
        int userId = currentUser.getId();
        favoriteService.removeFavorite(userId, recipeId);
        return "redirect:/favorites";
    }
}
