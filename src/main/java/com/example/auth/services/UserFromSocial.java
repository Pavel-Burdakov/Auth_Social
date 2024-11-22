package com.example.auth.services;
import com.example.auth.entity.TaskUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserFromSocial {
    public TaskUser GetUserFromSocial(OAuth2User principal);

}
