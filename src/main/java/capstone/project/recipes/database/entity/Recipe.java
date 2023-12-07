package capstone.project.recipes.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "recipes") // Assuming your table is named "recipes"
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "recipes_name")
    private String recipesName;

    @Column(name = "type")
    private String type;

    @Column(name = "images")
    private String images;

    // Other fields and methods...
}

