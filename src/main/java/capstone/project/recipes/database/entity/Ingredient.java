package capstone.project.recipes.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "id")
   // private Recipe id;

    @Column(name = "name")
    private String name;


}

