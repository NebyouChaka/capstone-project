package capstone.project.recipes.database.dao;

import capstone.project.recipes.database.entity.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsDAO extends JpaRepository<Ingredients, Integer> {
    // You can add custom query methods here if needed
}

