package capstone.project.recipes.database.dao;

import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.User;
import capstone.project.recipes.database.entity.UserFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFavoriteDAO extends JpaRepository<UserFavorite, Integer> {

    boolean existsByUserAndRecipe(User user, Recipe recipe);
    List<UserFavorite> findByUser(User user);
    List<UserFavorite> findById(Long id);
}

