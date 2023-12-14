package capstone.project.recipes.repository;

import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.User;
import capstone.project.recipes.database.entity.UserFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserFavoriteRepository extends JpaRepository<UserFavorite, Integer> {

    // Check if a favorite entry already exists for a user and a recipe
    boolean existsByUserAndRecipe(User user, Recipe recipe);

    // Find all favorite entries for a given user
    List<UserFavorite> findByUser(User user);
}
