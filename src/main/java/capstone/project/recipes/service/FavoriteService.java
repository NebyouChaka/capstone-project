package capstone.project.recipes.service;

import capstone.project.recipes.database.dao.FavoriteDAO;
import capstone.project.recipes.database.entity.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class FavoriteService {

    @Autowired
    private FavoriteDAO favoriteDAO;

    @Transactional
    public List<Favorite> getFavoritesByUser(int userId) {
        return favoriteDAO.findByUserId(userId);
    }

    @Transactional
    public void addFavorite(int userId, int recipeId) {
        if (!favoriteDAO.findByUserIdAndRecipeId(userId, recipeId).isPresent()) {
            Favorite favorite = new Favorite();
            favorite.setUserId(userId);
            favorite.setRecipeId(recipeId);
            favoriteDAO.save(favorite);
        }
    }
    @Transactional
    public void removeFavorite(int userId, int recipeId) {
        favoriteDAO.deleteByUserIdAndRecipeId(userId, recipeId);
    }
}
