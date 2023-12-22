package capstone.project.recipes.controller;

import capstone.project.recipes.database.dao.RecipeDAO;
import capstone.project.recipes.database.dao.UserDAO;
import capstone.project.recipes.database.entity.Recipe;
import capstone.project.recipes.database.entity.User;
import capstone.project.recipes.service.UserService;
import groovy.util.logging.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserService userService;

    @GetMapping("/fileupload")
    public ModelAndView recipeFileUpload(@RequestParam Long id) {
        ModelAndView response = new ModelAndView("user/fileupload");

        User user = userDAO.findById(id).orElse(null);
        response.addObject("user", user);

        logger.info("In fileupload with no Args");
        return response;
    }

    @PostMapping("/fileUploadSubmit")
    public ModelAndView recipeFileUploadSubmit(@RequestParam("profilePhoto") MultipartFile file,
                                               @RequestParam Long id) {
        ModelAndView response = new ModelAndView("redirect:/user/profile?id=" + id);

        logger.info("Filename = " + file.getOriginalFilename());
        logger.info("Size     = " + file.getSize());
        logger.info("Type     = " + file.getContentType());

        // Get the file and save it somewhere
        File f = new File("./src/main/webapp/pub/images/" + file.getOriginalFilename());
        try (OutputStream outputStream = new FileOutputStream(f.getAbsolutePath())) {
            IOUtils.copy(file.getInputStream(), outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        User user = userDAO.findById(id).orElse(null);
        if (user != null) {
            user.setProfilePhoto("/pub/images/" + file.getOriginalFilename());
            userDAO.save(user); // Save the updated user entity
        }

        return response;
    }

    @GetMapping("/deleteUser/{userId}")
    public ModelAndView deleteUser(@PathVariable Long userId) {
        // Logic to delete the user
        userService.deleteUser(userId);
        // Redirect to a suitable page after deleting the user
        return new ModelAndView("redirect:/admin/dashboard"); // Redirect to the admin dashboard or another appropriate page
    }
}
