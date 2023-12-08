package capstone.project.recipes.formbean;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CreateRecipeFormBean {

    private Integer id;
    private String recipesName;
    private String type;
    private String imagesURL;



}

