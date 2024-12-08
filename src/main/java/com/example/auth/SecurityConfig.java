package com.example.auth;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    private ClientRegistrationRepository clientRegistrationRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/index").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        http
                .logout(
                        logout -> logout
                                .logoutUrl("/logout") // URL для выхода
                                .logoutSuccessUrl("/index") // URL перенаправления после выхода
                                .invalidateHttpSession(true) // Инвалидировать сессию
                                .clearAuthentication(true) // Очистить аутентификацию
                                .permitAll() // Разрешить выход всем пользователям
                );
        return http.build();
    }
}





