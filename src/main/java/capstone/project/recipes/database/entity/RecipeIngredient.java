package capstone.project.recipes.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "recipe_ingredients")
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    //@Column(name = "recipe_id")
    //rivate String recipe_id;

    //@Column(name = "ingredient_id")
    //private String ingredient_id;

    @Column(name = "measurement")
    private String measurement;

    @Column(name = "quantity")
    private Double quantity;


}
