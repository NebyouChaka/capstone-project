package capstone.project.recipes.database.dao;

import capstone.project.recipes.database.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDAOTest {

    @Mock
    private UserDAO userDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void findByEmailIgnoreCase() {

            // Arrange
            String userEmail = "nebyouchaka@gmail.com";
            User user = new User();
            user.setEmail(userEmail);

            when(userDAO.findByEmailIgnoreCase(userEmail)).thenReturn(user);

            // Act
            User result = userDAO.findByEmailIgnoreCase(userEmail);

            // Assert
            assertEquals(user, result);

    }

    @Test
    void findById() {

        // Arrange
        Integer userId = 1;
        User user = new User();
        user.setId(userId);

        when(userDAO.findById(userId)).thenReturn(List.of(user));

        // Act
        List<User> result = userDAO.findById(userId);

        // Assert
        Assertions.assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(user, result.get(0));
    }
    @Test
    public void testFindAll() {
        // Arrange
        User user1 = new User();
        User user2 = new User();
        List<User> users = Arrays.asList(user1, user2);

        when(userDAO.findAll()).thenReturn(users);

        // Act
        List<User> result = userDAO.findAll();

        // Assert
        assertEquals(users.size(), result.size());
        assertEquals(users, result);
    }

    @Test
    public void testDeleteUser() {
        // Arrange
        Long userId = 1L;

        // Act
        userDAO.deleteById(userId);

        // Assert
        verify(userDAO, times(1)).deleteById(userId);
    }
}