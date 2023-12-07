package capstone.project.recipes.database.dao;

import capstone.project.recipes.database.entity.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {

    public List<UserRole> findByUserId(Integer userId);

}
