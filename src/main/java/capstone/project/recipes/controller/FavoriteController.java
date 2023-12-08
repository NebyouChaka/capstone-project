package capstone.project.recipes.controller;


import capstone.project.recipes.database.entity.Favorite;
import capstone.project.recipes.service.FavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/save")
    public Favorite save(@RequestBody Favorite favorite) {
        return favoriteService.save(favorite);
    }

    @GetMapping("/all")
    public List<Favorite> findAll() {
        return favoriteService.findAll();
    }

    @GetMapping("/{id}")
    public Favorite findById(@PathVariable Integer id) {
        return favoriteService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        favoriteService.deleteById(id);
    }
}

