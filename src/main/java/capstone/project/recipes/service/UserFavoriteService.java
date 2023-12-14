package capstone.project.recipes.service;

import capstone.project.recipes.database.dao.UserFavoriteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import capstone.project.recipes.database.entity.UserFavorite;
import capstone.project.recipes.database.entity.User;
import capstone.project.recipes.database.entity.Recipe;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserFavoriteService {

    private final UserFavoriteDAO userFavoriteDAO; // Assuming UserFavoriteDAO is your JPA repository

    @Autowired
    public UserFavoriteService(UserFavoriteDAO userFavoriteDAO) {
        this.userFavoriteDAO = userFavoriteDAO;
    }

    public void addFavorite(User user, Recipe recipe) {
        if (!userFavoriteDAO.existsByUserAndRecipe(user, recipe)) {
            UserFavorite userFavorite = new UserFavorite();
            userFavorite.setUser(user);
            userFavorite.setRecipe(recipe);
            userFavoriteDAO.save(userFavorite);
        }
    }

    public List<Recipe> findFavoritesByUser(User user) {
        return userFavoriteDAO.findByUser(user).stream()
                .map(UserFavorite::getRecipe)
                .collect(Collectors.toList());
    }

    public UserFavorite updateFavorite(Integer favoriteId, Recipe updatedRecipeData) {
        Optional<UserFavorite> favoriteOptional = userFavoriteDAO.findById(favoriteId);
        if (favoriteOptional.isPresent()) {
            UserFavorite favorite = favoriteOptional.get();
            favorite.setRecipe(updatedRecipeData); // Assuming UserFavorite has a setRecipe method
            return userFavoriteDAO.save(favorite);
        }
        return null;
    }

    public boolean deleteFavorite(Integer favoriteId) {
        if (userFavoriteDAO.existsById(favoriteId)) {
            userFavoriteDAO.deleteById(favoriteId);
            return true;
        }
        return false;
    }

    // ... other methods ...
}


