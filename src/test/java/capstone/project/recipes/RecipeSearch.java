package capstone.project.recipes;

import capstone.project.recipes.controller.RecipeController;
import capstone.project.recipes.database.dao.RecipeDAO;
import capstone.project.recipes.database.dao.RecipeIngredientDAO;
import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.RecipeIngredient;
import capstone.project.recipes.database.entity.User;
import capstone.project.recipes.formbean.CreateRecipeFormBean;
import capstone.project.recipes.security.AuthenticatedUserService;
import capstone.project.recipes.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class RecipeSearch {

    @InjectMocks
    private RecipeController recipeController;

    @Mock
    private RecipeService recipeService;

    @Mock
    private RecipeDAO recipeDAO;

    @Mock
    private AuthenticatedUserService authenticatedUserService;

    @Mock
    private RecipeIngredientDAO recipeIngredientDAO;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.jupiter.api.Test
    public void testSearch() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        ModelAndView expectedModelAndView = new ModelAndView("recipe/search");

        String nameSearch = "TestRecipe";
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setName(nameSearch);
        recipes.add(recipe);

        when(recipeDAO.findByName(anyString())).thenReturn(recipes);

        // Act
        ModelAndView modelAndView = recipeController.search(nameSearch);

        // Assert
        assertEquals(expectedModelAndView.getViewName(), modelAndView.getViewName());
        assertEquals(nameSearch, modelAndView.getModel().get("nameSearch"));
        assertEquals(recipes, modelAndView.getModel().get("recipeVar"));
    }


}
