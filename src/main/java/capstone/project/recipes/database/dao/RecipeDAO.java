package capstone.project.recipes.database.dao;


import capstone.project.recipes.database.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeDAO extends JpaRepository<Recipe, Long> {
    List<Recipe> findByRecipesNameOrType(String recipesName, String type);
    Recipe findById(Integer id);
}

