package com.example.auth.services.impl;
import com.example.auth.entity.TaskUser;
import com.example.auth.services.UserFromSocial;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class UserFromYandex implements UserFromSocial {
    @Override
    public TaskUser GetUserFromSocial(OAuth2User principal) {
        TaskUser taskUser = new TaskUser();
        taskUser.setUserName(principal.getAttribute("display_name"));
        taskUser.setEmail(principal.getAttribute("default_email"));
        return taskUser;
    }
}
