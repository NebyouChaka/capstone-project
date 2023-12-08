package capstone.project.recipes.security;

import capstone.project.recipes.database.entity.Favorite;
import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.User;
import capstone.project.recipes.repository.FavoriteRepository;
import capstone.project.recipes.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl extends FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;


    public List<Recipe> findFavoriteRecipesByUser(User user) {
        List<Favorite> favorites = favoriteRepository.findByUser(user);
        return favorites.stream().map(Favorite::getRecipe).collect(Collectors.toList());
    }
}
