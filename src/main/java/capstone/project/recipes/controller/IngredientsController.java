package capstone.project.recipes.controller;

import capstone.project.recipes.database.entity.Ingredients;
import capstone.project.recipes.service.IngredientsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/save")
    public Ingredients save(@RequestBody Ingredients ingredient) {
        return ingredientsService.save(ingredient);
    }

    @GetMapping("/all")
    public List<Ingredients> findAll() {
        return ingredientsService.findAll();
    }

    @GetMapping("/{id}")
    public Ingredients findById(@PathVariable Integer id) {
        return ingredientsService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        ingredientsService.deleteById(id);
    }
}

