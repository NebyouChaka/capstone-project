package capstone.project.recipes.database.dao;

import capstone.project.recipes.database.entity.Ingredient;;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface IngredientDAO extends JpaRepository<Ingredient, Integer> {
    // Additional custom methods if needed

}
