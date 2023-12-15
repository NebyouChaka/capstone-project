package capstone.project.recipes.formbean;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeIngredientFormBean {


    private Integer recipeId;
    private Integer ingredientId;
    private String measurement;
    private Double quantity;


}
