package capstone.project.recipes.database.dao;

import capstone.project.recipes.database.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    @Order(1)
    public void findUserByEmailTest() {
        // given
        String email = "nebyouchaka@gmail.com"; // Replace with a valid email in your database

        // when
        User user = userDAO.findByEmail(email);

        // then
        Assertions.assertNotNull(user);
        Assertions.assertEquals(email, user.getEmail());
    }

    @Test
    @Order(2)
    public void findUserByEmailIgnoreCaseTest() {
        // given
        String email = "nebyouchaka@gmail.com"; // Use a case-insensitive version of a valid email

        // when
        User user = userDAO.findByEmailIgnoreCase(email);

        // then
        Assertions.assertNotNull(user);
        Assertions.assertTrue(email.equalsIgnoreCase(user.getEmail()));
    }

    @Test
    @Order(3)
    public void findUserByIdTest() {
        // given
        Integer userId = 1; // Replace with a valid user ID in your database

        // when
        List<User> users = userDAO.findById(userId);

        // then
        Assertions.assertFalse(users.isEmpty());
        User user = users.get(0);
        Assertions.assertEquals(userId, user.getId());
    }
}
