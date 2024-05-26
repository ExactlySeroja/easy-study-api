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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity()
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtTokenFilter jwtTokenFilter) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(Routes.REGISTER_ROUTE_SECURITY,
                                Routes.LOGIN).permitAll()

                        .requestMatchers(Routes.SWAGGER_ENDPOINTS).permitAll()
                        .requestMatchers("/v3/api-docs", "/swagger-ui/**", "/swagger-ui.html", "/webjars/**").permitAll()

                        .requestMatchers(Routes.TEACHER + "/**",
                                Routes.STUDENT_GET_ALL_MY_COURSES,
                                Routes.STUDENT_GET_ALL_AVAILABLE_COURSES,
                                Routes.STUDENT_GET_ALL_THEMES_BY_COURSE,
                                Routes.STUDENT_GET_ALL_MATERIALS_BY_THEME,
                                Routes.STUDENT_FILTER_COURSE_BY_NAME,
                                Routes.STUDENT_FILTER_COURSE_BY_CATEGORY,
                                Routes.STUDENT_FILTER_COURSE_BY_PRICE,
                                Routes.STUDENT_FILTER_COURSE_BY_START_DATE,
                                Routes.STUDENT_GET_ALL_MY_APPLICATIONS,
                                Routes.STUDENT_GET_ALL_MY_CERTIFICATES,
                                Routes.STUDENT_GET_MATERIAL,
                                Routes.STUDENT_CREATE_APPLICATION,
                                Routes.STUDENT_ADD_TASK_PERFORMANCE).hasRole("STUDENT")

                        .requestMatchers(Routes.TEACHER + "/**",
                                Routes.TEACHER_ADD_APPLICATION,
                                Routes.TEACHER_ADD_CATEGORY,
                                Routes.TEACHER_ADD_CERTIFICATE,
                                Routes.TEACHER_ADD_COURSE,
                                Routes.TEACHER_ADD_THEME,
                                Routes.TEACHER_ADD_CATEGORY,
                                Routes.TEACHER_ADD_EDUCATIONAL_MATERIAL,
                                Routes.TEACHER_ADD_USER,
                                Routes.TEACHER_CHECK_APPLICATION_BY_ID,
                                Routes.TEACHER_GET_ALL_APPLICATIONS,
                                Routes.TEACHER_CATEGORY_BY_ID,
                                Routes.TEACHER_GET_ALL_CATEGORIES,
                                Routes.TEACHER_CERTIFICATE_BY_ID,
                                Routes.TEACHER_GET_ALL_CERTIFICATES,
                                Routes.TEACHER_COURSE_BY_ID,
                                Routes.TEACHER_GET_ALL_COURSES,
                                Routes.TEACHER_EDUCATIONAL_MATERIAL_BY_ID,
                                Routes.TEACHER_GET_ALL_EDUCATIONAL_MATERIAL,
                                Routes.TEACHER_TASK_PERFORMANCE_BY_ID,
                                Routes.TEACHER_GET_ALL_TASK_PERFORMANCE,
                                Routes.TEACHER_THEME_BY_ID,
                                Routes.TEACHER_GET_ALL_THEMES,
                                Routes.TEACHER_USER_BY_ID,
                                Routes.TEACHER_GET_ALL_USER
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
