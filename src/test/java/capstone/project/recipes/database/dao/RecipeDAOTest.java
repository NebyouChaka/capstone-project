package capstone.project.recipes.database.dao;

import capstone.project.recipes.database.entity.Recipe;
import org.hibernate.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;



import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
class RecipeDAOTest {



    @Mock
    private RecipeDAO recipeDAO;

//    @Mock
//    private Service service; // Injecting a mock of Service

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByName() {
        // Arrange
        String name = "Test Recipe";
        List<Recipe> expectedRecipes = Arrays.asList(new Recipe(), new Recipe());

        when(recipeDAO.findByName("%" + name + "%")).thenReturn(expectedRecipes);

        // Act
        List<Recipe> actualRecipes = recipeDAO.findByName(name);

        // Assert
        assertEquals(expectedRecipes.size(), actualRecipes.size());
        assertEquals(expectedRecipes, actualRecipes);
    }

    @Test
    public void testGetRecipesByCategory() {
        // Arrange
        String category = "Test Category";
        List<Recipe> expectedRecipes = Arrays.asList(new Recipe(), new Recipe());

        when(recipeDAO.getRecipesByCategory(category)).thenReturn(expectedRecipes);

        // Act
        List<Recipe> actualRecipes = recipeDAO.getRecipesByCategory(category);

        // Assert
        assertEquals(expectedRecipes.size(), actualRecipes.size());
        assertEquals(expectedRecipes, actualRecipes);
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<Recipe> expectedRecipes = Arrays.asList(new Recipe(), new Recipe());

        when(recipeDAO.findAll()).thenReturn(expectedRecipes);

        // Act
        List<Recipe> actualRecipes = recipeDAO.findAll();

        // Assert
        assertEquals(expectedRecipes.size(), actualRecipes.size());
        assertEquals(expectedRecipes, actualRecipes);
    }

    @Test
    public void testFindListById() {
        // Arrange
        Long id = 1L;
        List<Recipe> expectedRecipes = Arrays.asList(new Recipe(), new Recipe());

        when(recipeDAO.findListById(id)).thenReturn(expectedRecipes);

        // Act
        List<Recipe> actualRecipes = recipeDAO.findListById(id);

        // Assert
        assertEquals(expectedRecipes.size(), actualRecipes.size());
        assertEquals(expectedRecipes, actualRecipes);
    }

    @Test
    public void testFindById() {
        // Arrange
        Integer id = 1;
        Recipe expectedRecipe = new Recipe();

        when(recipeDAO.findById(id)).thenReturn(expectedRecipe);

        // Act
        Recipe actualRecipe = recipeDAO.findById(id);

        // Assert
        assertEquals(expectedRecipe, actualRecipe);
    }
}