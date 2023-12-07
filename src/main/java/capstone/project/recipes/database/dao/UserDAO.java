package capstone.project.recipes.database.dao;


import capstone.project.recipes.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {

    public User findByEmailIgnoreCase(String email);


}