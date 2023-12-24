package capstone.project.recipes.controller;

import capstone.project.recipes.database.dao.UserDAO;
import capstone.project.recipes.database.entity.User;
import capstone.project.recipes.formbean.RegisterUserFormBean;
import capstone.project.recipes.security.AuthenticatedUserService;
import capstone.project.recipes.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Controller
@Service
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;


    @GetMapping("/auth/login")
    public ModelAndView login() {
        ModelAndView response = new ModelAndView();
        response.setViewName("auth/login");
        return response;
    }



    @GetMapping("/auth/register")
    public ModelAndView register() {
        ModelAndView response = new ModelAndView();
        response.setViewName("auth/register");
        return response;
    }

    @PostMapping("/auth/registerSubmit")
    public ModelAndView registerSubmit(@Valid RegisterUserFormBean form,
                                       @RequestParam("profilePhoto") MultipartFile profilePhoto,
                                       BindingResult bindingResult,
                                       HttpSession session) {
        ModelAndView response = new ModelAndView();

        if (bindingResult.hasErrors()) {
            response.setViewName("auth/register");
            response.addObject("form", form);
            response.addObject("errors", bindingResult);
            return response;
        }

        User newUser = userService.createNewUser(form);

        if (!profilePhoto.isEmpty()) {
            try {
                String filename = saveProfilePhoto(profilePhoto, newUser.getId());
                newUser.setProfilePhoto(filename);
                userDAO.save(newUser);
            } catch (IOException e) {
                log.error("Error during file upload: ", e);
                response.setViewName("auth/register");
                response.addObject("fileError", "Error occurred during file upload: " + e.getMessage());
                return response;
            }
        }

        authenticatedUserService.authenticateNewUser(session, newUser.getEmail(), form.getPassword());
        response.setViewName("redirect:/");
        return response;
    }

    private String saveProfilePhoto(MultipartFile file, int userId) throws IOException {
        // Logic to save the file in a directory and return the file path or filename
        // Ensure this logic handles file validation, storage and returns the path or filename for the saved file
        if (file.isEmpty()) {
            throw new IOException("Cannot save an empty file.");
        }

        // Validate the file type, size, etc.
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image")) {
            throw new IOException("Invalid file type. Only image files are allowed.");
        }

        // Construct a unique filename (e.g., using user ID and original filename)
        String originalFilename = file.getOriginalFilename();
        String filename = userId + "_" + originalFilename;

        // Define the path where the file will be saved
        Path destinationPath = Paths.get("./src/main/webapp/pub/images/").resolve(filename);

        // Save the file
        Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

        // Return the path or filename as a string
        return "/pub/images/" + filename;
    }

}