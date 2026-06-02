package com.coachassist.backend.security.config;

import com.coachassist.backend.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

                .cors(cors -> {})

                .csrf(csrf -> csrf.disable())

                .httpBasic(httpBasic -> httpBasic.disable())

                .formLogin(form -> form.disable())

                .logout(logout -> logout.disable())

                .sessionManagement(session -> session
                        .sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

//Paarent and COaching Both
                        .requestMatchers(

                                "/api/marks/student/**",

                                "/api/marks/analytics/**"

                        ).hasAnyRole(
                                "PARENT",
                                "COACHING"
                        )
                        .requestMatchers(

                                "/api/admin/login",

                                "/api/coaching/login",
                                "/api/coaching/register",

                                "/api/parent/login",
                                "/swagger-ui/**",

                                "/v3/api-docs/**"

                        ).permitAll()

                        // ADMIN
                        .requestMatchers(

                                "/api/coaching/all",
                                "/api/coaching/search",
                                "/api/coaching/delete/**"

                        ).hasRole("ADMIN")

                        // PARENT FIRST (IMPORTANT)
                        .requestMatchers(

                                "/api/marks/student/**",

                                "/api/attendance/student/**",

                                "/api/announcement/active/**"

                        ).hasRole("PARENT")

                        // COACHING
                        .requestMatchers(

                                "/api/student/**",

                                "/api/attendance/**",

                                "/api/marks/**",

                                "/api/announcement/create",

                                "/api/dashboard/**"

                        ).hasRole("COACHING")




                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}