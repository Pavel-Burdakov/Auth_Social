package com.example.auth;
// src/main/java/com/example/demo/controller/HomeController.java
import com.example.auth.entity.TaskUser;
import com.example.auth.repo.TaskUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    TaskUserRepository taskUserRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal OAuth2User principal, Model model) {
        
        if (principal.getAttributes().toString().contains("gmail")){

                if (taskUserRepository.findByEmail(principal.getAttribute("email")).isEmpty()) {
                    TaskUser taskUser = new TaskUser();
                    taskUser.setUserName(principal.getAttribute("name"));
                    taskUser.setEmail(principal.getAttribute("email"));
                    taskUserRepository.save(taskUser);
                }
                model.addAttribute("name", principal.getAttribute("name"));
                model.addAttribute("email", principal.getAttribute("email"));
            }
        if (principal.getAttributes().toString().contains("yandex")){
                if (taskUserRepository.findByEmail(principal.getAttribute("default_email")).isEmpty()) {
                    TaskUser taskUser = new TaskUser();
                    taskUser.setUserName(principal.getAttribute("display_name"));
                    taskUser.setEmail(principal.getAttribute("default_email"));
                    taskUserRepository.save(taskUser);

                }
            model.addAttribute("name", principal.getAttribute("display_name"));
            model.addAttribute("email", principal.getAttribute("default_email"));

            }
        return "home";
    }

    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();


}
