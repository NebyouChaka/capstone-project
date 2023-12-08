package capstone.project.recipes.database.dao;


import capstone.project.recipes.database.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeDAO extends JpaRepository<Recipe, Integer> {

     Recipe findById(Integer Id);


    @Query("SELECT r FROM Recipe r WHERE r.recipesName LIKE :recipesName or r.type LIKE :type")
    List<Recipe> findByRecipesNameOrType(String recipesName, String type);
}

