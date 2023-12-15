package capstone.project.recipes.controller;

import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.User;
import capstone.project.recipes.service.RecipeService;
import capstone.project.recipes.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    private final RecipeService recipeService;
    private final UserService userService;

    @Autowired
    public AdminController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @GetMapping("/index")
    public ModelAndView adminDashboard() {
        log.debug("Admin index page requested");

        List<Recipe> recipes = recipeService.getAllRecipes();
        List<User> users = userService.getAllUsers();

        ModelAndView modelAndView = new ModelAndView("admin/index");
        modelAndView.addObject("recipes", recipes);
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    // Additional methods for edit, update, delete functionality
}

