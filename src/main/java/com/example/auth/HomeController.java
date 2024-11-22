package com.example.auth;
import com.example.auth.dto.TaskUserDTO;
import com.example.auth.entity.TaskUser;
import com.example.auth.repo.TaskUserRepository;
import com.example.auth.services.MappingUtils;
import com.example.auth.services.impl.UserFromGoogle;
import com.example.auth.services.impl.UserFromYandex;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    TaskUserRepository taskUserRepository;
    @Autowired
    MappingUtils mappingUtils;
    private String email;
    private TaskUser user;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public TaskUserDTO home(@AuthenticationPrincipal OAuth2User principal) {
        switch (whoAreYouUser(principal)) {
            case "Google":
                if (taskUserRepository.findByEmail(email).isEmpty()) {
                    taskUserRepository.save(new UserFromGoogle().GetUserFromSocial(principal));
                }
                break;
            case "Yandex":
                if (taskUserRepository.findByEmail(email).isEmpty()) {
                    taskUserRepository.save(new UserFromYandex().GetUserFromSocial(principal));
                }
                break;
        }
        return getUserInfo();
    }

    public TaskUserDTO getUserInfo() {
        user = taskUserRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        TaskUserDTO dto = mappingUtils.mapToDto(user);
        return dto;
    }

    public String whoAreYouUser(OAuth2User principal) {
        String userType = null;
        if (principal.getAttributes().toString().contains("gmail")) {
            userType = "Google";
            email = principal.getAttribute("email");
        }
        if (principal.getAttributes().toString().contains("yandex")) {
            userType = "Yandex";
            email = principal.getAttribute("default_email");
        }
        return userType;
    }
}
