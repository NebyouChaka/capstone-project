package capstone.project.recipes.database.dao;

import capstone.project.recipes.database.entity.Ingredient;;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface IngredientDAO extends JpaRepository<Ingredient, Long> {


List<Ingredient>findById(Integer id);
    List<Ingredient>findByName(String ingredientName);
}
