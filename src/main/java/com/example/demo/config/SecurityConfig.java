package com.example.demo.config;

import com.example.demo.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> {}) // ⭐ Включили CORS
                .csrf(csrf -> csrf.disable()) // Отключили CSRF (для JWT)

                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()

                        // разрешить всем авторизованным (или ADMIN+MANAGER) получить себя
                        .requestMatchers("/users/me").hasAnyRole("ADMIN", "MANAGER")

                        // список/управление пользователями только админ
                        .requestMatchers("/users/**").hasRole("ADMIN")

                        .requestMatchers("/clients/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/orders/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/categories/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/products/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/order-items/**").hasAnyRole("ADMIN", "MANAGER")

                        .anyRequest().authenticated()
                );


        // Добавляем JWT фильтр
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
