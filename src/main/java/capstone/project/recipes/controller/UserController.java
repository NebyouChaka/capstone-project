package capstone.project.recipes.controller;

import capstone.project.recipes.database.dao.UserDAO;
import capstone.project.recipes.database.entity.User;
import capstone.project.recipes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService.FileStorageService fileStorageService;

    @Autowired
    private UserDAO userDao;

    @PostMapping("/uploadProfilePhoto")
    public String uploadProfilePhoto(@RequestParam("file") MultipartFile file, Principal principal, RedirectAttributes redirectAttributes) {
        String filename = fileStorageService.store(file);
        User user = userDao.findByEmailIgnoreCase(principal.getName());
        if (user != null) {
            user.setProfilePhoto(filename);
            userDao.save(user);
            redirectAttributes.addFlashAttribute("message", "Profile photo uploaded successfully!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to upload profile photo.");
        }
        return "redirect:/user/profile";
    }

    // Other controller methods...
}