package capstone.project.recipes.database.dao;

import capstone.project.recipes.database.entity.Favorite;
import capstone.project.recipes.database.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteDAO extends JpaRepository<Favorite, Integer> {
    public List<Favorite> findByUserId(Integer user_Id);
}
