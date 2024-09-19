package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true) //todo potřebuju až budu řešit role
public class ApplicationSecurityConfiguration {

    @Bean
    public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/", "/aboutUs", "/contact").permitAll() // Allow access without authentication
                        .requestMatchers("/styles/**", "/js/**", "/images/**", "/files/**").permitAll() //Allow access to files for CSS, img and JS files
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Přihlašovací stránka na URL "/login"
                        .defaultSuccessUrl("/home", true) // Po přihlášení přesměruje na stránku home
                        .usernameParameter("userName") //přihlašujeme se pomoci User name
                        .permitAll()  // Umožní všem přístup na přihlašovací stránku
                )
                .logout(LogoutConfigurer::permitAll  // Umožní všem přístup na odhlášení
                );
        return http.build();
    }

    //will allow to passwords be hashed into database
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
