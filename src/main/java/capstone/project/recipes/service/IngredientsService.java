package capstone.project.recipes.service;

import capstone.project.recipes.database.dao.IngredientsDAO;
import capstone.project.recipes.database.entity.Ingredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsService {

    @Autowired
    private IngredientsDAO ingredientsDAO;

    public Ingredients save(Ingredients ingredient) {
        return ingredientsDAO.save(ingredient);
    }

    public List<Ingredients> findAll() {
        return ingredientsDAO.findAll();
    }

    public Ingredients findById(Integer id) {
        return ingredientsDAO.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        ingredientsDAO.deleteById(id);
    }
}
