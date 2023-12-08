package capstone.project.recipes.repository;


import capstone.project.recipes.database.entity.Favorite;
import capstone.project.recipes.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUser(User user);
    // Add other query methods as needed
}