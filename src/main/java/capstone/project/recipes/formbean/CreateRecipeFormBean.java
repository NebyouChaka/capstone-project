package capstone.project.recipes.formbean;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CreateRecipeFormBean {

    private Integer id;
    private String name;
    private String description;
    private String image_url;
    private String category;
    private String user_id;



}

