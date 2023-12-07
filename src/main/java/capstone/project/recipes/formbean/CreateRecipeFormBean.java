package capstone.project.recipes.formbean;

public class CreateRecipeFormBean {

    private Long id;
    private String recipesName;
    private String type;
    private String images;

    // Getter and setter methods for each property

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipesName() {
        return recipesName;
    }

    public void setRecipesName(String recipesName) {
        this.recipesName = recipesName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}

