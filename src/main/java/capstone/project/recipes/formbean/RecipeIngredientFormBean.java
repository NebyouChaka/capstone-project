package capstone.project.recipes.formbean;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeIngredientFormBean {


    private Integer recipeId;
    private Integer ingredient_Id;
    private String measurement;
    private Double quantity;


}
