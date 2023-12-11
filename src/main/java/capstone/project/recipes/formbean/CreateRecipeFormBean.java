package capstone.project.recipes.formbean;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CreateRecipeFormBean {

    private Integer id;
    private String name;
    private String description;
    private String image_url;
    private String user_id;



}

