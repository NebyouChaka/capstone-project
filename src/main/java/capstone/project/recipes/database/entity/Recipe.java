package capstone.project.recipes.database.entity;

import jakarta.persistence.*;



@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "recipesName")
    private String recipesName;

    @Column(name = "type")
    private String type;

    @Column(name = "imagesURL")
    private String imagesURL;

    // Constructors, getters, and setters



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getImagesURL() {
        return imagesURL;
    }

    public void setImagesURL(String imageURL) {
        this.imagesURL = imageURL;
    }
}



