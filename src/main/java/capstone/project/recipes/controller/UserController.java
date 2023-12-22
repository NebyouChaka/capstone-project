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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String UPLOAD_DIR = "./src/main/webapp/pub/images/";
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
    public ModelAndView recipeFileUploadSubmit(@RequestParam("profilePhoto") MultipartFile file, @RequestParam Long id) {
        ModelAndView response = new ModelAndView("redirect:/user/profile?id=" + id);

        try {
            if (file.isEmpty() || !isImage(file)) {
                throw new IllegalArgumentException("Invalid file type or empty file.");
            }

            // Log file details
            logger.info("Filename = " + file.getOriginalFilename());
            logger.info("Size     = " + file.getSize());
            logger.info("Type     = " + file.getContentType());

            // Generate a unique filename to prevent overwriting
            String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            Path destinationFile = Paths.get("./src/main/webapp/pub/images/").resolve(filename);

            // Save the file
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            // Update user's profile photo in the database
            User user = userDAO.findById(id).orElse(null);
            if (user != null) {
                user.setProfilePhoto("/pub/images/" + filename);
                userDAO.save(user);
            }
        } catch (Exception e) {
            logger.error("Error during file upload: ", e);
            response = new ModelAndView("user/fileupload");
            response.addObject("error", "Error occurred during file upload: " + e.getMessage());
            return response;
        }

        return response;
    }

    private boolean isImage(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image");
    }


    @GetMapping("/deleteUser/{userId}")
    public ModelAndView deleteUser(@PathVariable Long userId) {
        // Logic to delete the user
        userService.deleteUser(userId);
        // Redirect to a suitable page after deleting the user
        return new ModelAndView("redirect:/admin/dashboard"); // Redirect to the admin dashboard or another appropriate page
    }
}
