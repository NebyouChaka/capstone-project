package capstone.project.recipes.repository;

import capstone.project.recipes.database.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

}

