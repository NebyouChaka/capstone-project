package capstone.project.recipes.database.dao;


import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeDAO extends JpaRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r WHERE r.name LIKE %:name%")
    List<Recipe> findByName(@Param("name") String name);

    List<Recipe> getRecipesByCategory(@Param("category") String category);
    List<Recipe> findAll();

    @Query("SELECT r FROM Recipe r WHERE r.id = :id")
    List<Recipe> findListById(@Param("id") Long id);

    Recipe findById(Integer id);
}

