package com.example.todayexercise.config;

import com.example.todayexercise.security.UserAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PERMIT_URL_ARRAY = {
           "/swagger-resources/**","/v3/api-docs/**","/swagger-ui/**",
            "/user/login","/user/signup","/user/check/**","/workout/**",
            "/actuator/prometheus"
    };
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsCustomize -> corsCustomize.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowCredentials(true);
                    config.addAllowedOriginPattern("*");
                    config.addAllowedMethod(HttpMethod.OPTIONS);
                    config.addAllowedMethod(HttpMethod.GET);
                    config.addAllowedMethod(HttpMethod.POST);
                    config.addAllowedMethod(HttpMethod.PUT);
                    config.addAllowedMethod(HttpMethod.DELETE);
                    config.addAllowedHeader("Content-Type");
                    config.setMaxAge(3600L);
                    return config;
                }))
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers(PERMIT_URL_ARRAY).permitAll()
                                .anyRequest().authenticated())
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .addFilterBefore(new UserAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
