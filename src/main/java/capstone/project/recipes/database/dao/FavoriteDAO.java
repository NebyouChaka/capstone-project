package capstone.project.recipes.database.dao;

import capstone.project.recipes.database.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteDAO  extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUserId(int userId);
    Optional<Favorite> findByUserIdAndRecipeId(int userId, int recipeId);
    void deleteByUserIdAndRecipeId(int userId, int recipeId);
}
