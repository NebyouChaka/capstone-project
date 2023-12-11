package capstone.project.recipes.database.dao;


import capstone.project.recipes.database.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeDAO extends JpaRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r WHERE r.name LIKE %:name%")
    List<Recipe> findByName(@Param("name") String name);


    Recipe findById(Integer id);
}

