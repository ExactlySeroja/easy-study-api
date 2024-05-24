package com.seroja.easystudyapi.config;

import com.seroja.easystudyapi.Routes;
import com.seroja.easystudyapi.security.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtTokenFilter jwtTokenFilter) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers( Routes.REGISTER_ROUTE_SECURITY,
                                Routes.LOGIN).permitAll()

                        .requestMatchers(Routes.SWAGGER_ENDPOINTS).permitAll()
                        .requestMatchers("/v3/api-docs", "/swagger-ui/**", "/swagger-ui.html", "/webjars/**").permitAll()

                        .requestMatchers(Routes.STUDENT + "/**",
                                Routes.STUDENT_CATEGORY_BY_ID,
                                Routes.STUDENT_THEME_BY_ID,
                                Routes.STUDENT_GET_ALL_THEMES,
                                Routes.STUDENT_USER_BY_ID,
                                Routes.STUDENT_EDUCATIONAL_MATERIAL_BY_ID,
                                Routes.STUDENT_ADD_APPLICATION).hasRole("STUDENT")

                        .requestMatchers(Routes.TEACHER + "/**"
                                ).hasRole("TEACHER")

                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .logout(Customizer.withDefaults());
        http.sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterAfter(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
