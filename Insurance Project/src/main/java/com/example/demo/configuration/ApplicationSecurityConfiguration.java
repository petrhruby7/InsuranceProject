package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
public class ApplicationSecurityConfiguration {

    @Bean
    public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/").permitAll() // Povolit přístup na "/login" a "/register" bez přihlášení
                        .anyRequest().authenticated()  // Každý jiný požadavek musí být autentizován
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Přihlašovací stránka na URL "/login"
                        .permitAll()  // Umožní všem přístup na přihlašovací stránku
                )
                /* todo LOGOUT vyřeším později
                .logout(LogoutConfigurer::permitAll  // Umožní všem přístup na odhlášení
                )*/;
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
