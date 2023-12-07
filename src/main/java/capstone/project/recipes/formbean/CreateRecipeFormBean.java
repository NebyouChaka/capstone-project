package capstone.project.recipes.formbean;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class CreateRecipeFormBean {

    private Long id;

    @NotEmpty(message = "Recipe Name is required.")
    @Length(max = 45, message = "FRecipe Name must be less than 45 characters.")
    @Pattern(regexp = "[a-zA-Z]+", message = "Recipe Name must contain only letters.")
    private String recipesName;

    @NotEmpty(message = "First Name is required.")
    @Length(max = 45, message = "First Name must be less than 45 characters.")
    @Pattern(regexp = "[a-zA-Z]+", message = "First Name must contain only letters.")
    private String type;
    private String images;



}

