package com.seroja.easystudyapi.seeder;

import com.seroja.easystudyapi.entity.*;
import com.seroja.easystudyapi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final ThemeRepository themeRepository;
    private final EducationalMaterialRepository educationalMaterialRepository;
    private final TaskPerformanceRepository taskPerformanceRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    private final CertificateRepository certificateRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        if (userRepository.count() == 0) {
            seedUsersTable();
        }
        if (categoryRepository.count() == 0) {
            seedCategoryTable();
        }
        if (courseRepository.count() == 0) {
            seedCourseTable();
        }
        if (applicationRepository.count() == 0) {
            seedApplicationTable();
        }
        if (themeRepository.count() == 0) {
            seedThemeTable();
        }
        if (educationalMaterialRepository.count() == 0) {
            seedEducationMaterialTable();
        }
        if (taskPerformanceRepository.count() == 0) {
            seedTaskPerformanceTable();
        }
        if (certificateRepository.count() == 0) {
            seedCertificateTable();
        }
    }

    private void seedUsersTable() {
        AppUser appUser = new AppUser();
        appUser.setUsername("superTeacher");
        appUser.setFullName("Valerii Zmishenko");
        appUser.setDateOfBirth(LocalDate.of(1990, 2, 21));
        appUser.setPassword(passwordEncoder.encode("superTeacher"));
        appUser.setPhoneNumber("+38080734534");
        appUser.setEmail("super@email.com");
        appUser.setRole(Set.of("TEACHER"));
        userRepository.save(appUser);

        AppUser appUser1 = new AppUser();
        appUser1.setUsername("codingNinja");
        appUser1.setFullName("Alex Johnson");
        appUser1.setDateOfBirth(LocalDate.of(1988, 5, 14));
        appUser1.setPassword(passwordEncoder.encode("codingNinja"));
        appUser1.setPhoneNumber("+38045678901");
        appUser1.setEmail("alex.johnson@example.com");
        appUser1.setRole(Set.of("TEACHER"));
        userRepository.save(appUser1);

        AppUser appUser2 = new AppUser();
        appUser2.setUsername("dataGuru");
        appUser2.setFullName("Maria Garcia");
        appUser2.setDateOfBirth(LocalDate.of(1992, 7, 23));
        appUser2.setPassword(passwordEncoder.encode("dataGuru"));
        appUser2.setPhoneNumber("+38076543210");
        appUser2.setEmail("maria.garcia@example.com");
        appUser2.setRole(Set.of("STUDENT"));
        userRepository.save(appUser2);

        AppUser appUser3 = new AppUser();
        appUser3.setUsername("techWizard");
        appUser3.setFullName("John Doe");
        appUser3.setDateOfBirth(LocalDate.of(1985, 11, 30));
        appUser3.setPassword(passwordEncoder.encode("techWizard"));
        appUser3.setPhoneNumber("+38051234567");
        appUser3.setEmail("john.doe@example.com");
        appUser3.setRole(Set.of("STUDENT"));
        userRepository.save(appUser3);

        AppUser appUser4 = new AppUser();
        appUser4.setUsername("designPro");
        appUser4.setFullName("Emma Brown");
        appUser4.setDateOfBirth(LocalDate.of(1995, 3, 18));
        appUser4.setPassword(passwordEncoder.encode("designPro"));
        appUser4.setPhoneNumber("+38023334444");
        appUser4.setEmail("emma.brown@example.com");
        appUser4.setRole(Set.of("STUDENT"));
        userRepository.save(appUser4);

        AppUser appUser5 = new AppUser();
        appUser5.setUsername("networkKing");
        appUser5.setFullName("David Smith");
        appUser5.setDateOfBirth(LocalDate.of(1989, 9, 5));
        appUser5.setPassword(passwordEncoder.encode("networkKing"));
        appUser5.setPhoneNumber("+38069871234");
        appUser5.setEmail("david.smith@example.com");
        appUser5.setRole(Set.of("STUDENT"));
        userRepository.save(appUser5);
    }

    private void seedCategoryTable() {
        Category category = new Category();
        category.setCategoryName("Programming");

        Category category2 = new Category();
        category2.setCategoryName("Design and Graphics");

        Category category3 = new Category();
        category3.setCategoryName("Math and Science");

        Category category4 = new Category();
        category4.setCategoryName("Languages and linguistics");

        Category category5 = new Category();
        category5.setCategoryName("Marketing and business");

        categoryRepository.save(category);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        categoryRepository.save(category5);
    }

    private void seedCourseTable() {

        Course course = new Course();
        course.setCourseName("Introduction to Java Programming");
        course.setCategory(categoryRepository.findById(1).get());
        course.setCourseStartDate(LocalDate.of(2024, 4, 5));
        course.setCourseEndDate(LocalDate.of(2024, 5, 8));
        course.setCoursePrice(200);
        course.setTeacher(userRepository.findById(1).get());
        courseRepository.save(course);

        Course course2 = new Course();
        course2.setCourseName("Advanced Python Programming");
        course2.setCategory(categoryRepository.findById(1).get());
        course2.setCourseStartDate(LocalDate.of(2024, 6, 1));
        course2.setCourseEndDate(LocalDate.of(2024, 7, 15));
        course2.setCoursePrice(250);
        course2.setTeacher(userRepository.findById(2).get());
        courseRepository.save(course2);

        Course course3 = new Course();
        course3.setCourseName("Graphic Design with Photoshop");
        course3.setCategory(categoryRepository.findById(2).get());
        course3.setCourseStartDate(LocalDate.of(2024, 8, 10));
        course3.setCourseEndDate(LocalDate.of(2024, 9, 20));
        course3.setCoursePrice(180);
        course3.setTeacher(userRepository.findById(1).get());
        courseRepository.save(course3);

        Course course4 = new Course();
        course4.setCourseName("Data Analysis with R");
        course4.setCategory(categoryRepository.findById(3).get());
        course4.setCourseStartDate(LocalDate.of(2024, 10, 5));
        course4.setCourseEndDate(LocalDate.of(2024, 11, 15));
        course4.setCoursePrice(220);
        course4.setTeacher(userRepository.findById(2).get());
        courseRepository.save(course4);

        Course course5 = new Course();
        course5.setCourseName("Business Marketing Strategies");
        course5.setCategory(categoryRepository.findById(5).get());
        course5.setCourseStartDate(LocalDate.of(2024, 12, 1));
        course5.setCourseEndDate(LocalDate.of(2025, 1, 10));
        course5.setCoursePrice(300);
        course5.setTeacher(userRepository.findById(1).get());
        courseRepository.save(course5);
    }

    private void seedApplicationTable() {
        Application application = new Application();
        application.setCourse(courseRepository.findById(1).get());
        application.setStudent(userRepository.findById(3).get());
        application.setDateOfCreation(LocalDate.of(2024, 3, 1));
        application.setApplicationStatus(true);
        applicationRepository.save(application);

        Application application2 = new Application();
        application2.setCourse(courseRepository.findById(2).get());
        application2.setStudent(userRepository.findById(4).get());
        application2.setDateOfCreation(LocalDate.of(2024, 5, 10));
        application2.setApplicationStatus(false);
        applicationRepository.save(application2);

        Application application3 = new Application();
        application3.setCourse(courseRepository.findById(3).get());
        application3.setStudent(userRepository.findById(5).get());
        application3.setDateOfCreation(LocalDate.of(2024, 7, 15));
        application3.setApplicationStatus(false);
        applicationRepository.save(application3);

        Application application4 = new Application();
        application4.setCourse(courseRepository.findById(4).get());
        application4.setStudent(userRepository.findById(3).get());
        application4.setDateOfCreation(LocalDate.of(2024, 9, 1));
        application4.setApplicationStatus(false);
        applicationRepository.save(application4);

        Application application5 = new Application();
        application5.setCourse(courseRepository.findById(5).get());
        application5.setStudent(userRepository.findById(5).get());
        application5.setDateOfCreation(LocalDate.of(2024, 11, 15));
        application5.setApplicationStatus(false);
        applicationRepository.save(application5);
    }

    private void seedThemeTable() {
        Theme theme = new Theme();
        theme.setCourse(courseRepository.findById(1).get());
        theme.setThemeName("Basics of Java");
        themeRepository.save(theme);

        Theme theme2 = new Theme();
        theme2.setCourse(courseRepository.findById(2).get());
        theme2.setThemeName("Python Syntax and Semantics");
        themeRepository.save(theme2);

        Theme theme3 = new Theme();
        theme3.setCourse(courseRepository.findById(3).get());
        theme3.setThemeName("Introduction to Photoshop");
        themeRepository.save(theme3);

        Theme theme4 = new Theme();
        theme4.setCourse(courseRepository.findById(4).get());
        theme4.setThemeName("Data Cleaning in R");
        themeRepository.save(theme4);

        Theme theme5 = new Theme();
        theme5.setCourse(courseRepository.findById(5).get());
        theme5.setThemeName("Marketing Fundamentals");
        themeRepository.save(theme5);
    }

    private void seedEducationMaterialTable() {
        EducationalMaterial educationalMaterial = new EducationalMaterial();
        educationalMaterial.setTheme(themeRepository.findById(1).get());
        educationalMaterial.setEd_material_name("Beginners mistakes");
        educationalMaterial.setDateOfUpload(LocalDate.of(2024, 4, 15));
        educationalMaterial.setContent("The most common mistake is...");
        educationalMaterialRepository.save(educationalMaterial);
        EducationalMaterial educationalMaterial2 = new EducationalMaterial();
        educationalMaterial2.setTheme(themeRepository.findById(2).get());
        educationalMaterial2.setEd_material_name("How to print Hello World");
        educationalMaterial2.setDateOfUpload(LocalDate.of(2024, 5, 10));
        educationalMaterial2.setContent("Just use some print methods");
        educationalMaterialRepository.save(educationalMaterial2);
    }

    private void seedTaskPerformanceTable() {
        TaskPerformance taskPerformance = new TaskPerformance();
        taskPerformance.setEdMaterial(educationalMaterialRepository.findById(1).get());
        taskPerformance.setDoneBy(userRepository.findById(3).get());
        taskPerformance.setAnswer("Install Java");
        taskPerformance.setDateOfCompletion(LocalDate.of(2024, 4, 17));
        taskPerformance.setGrade(100);
        taskPerformanceRepository.save(taskPerformance);

        TaskPerformance taskPerformance2 = new TaskPerformance();
        taskPerformance2.setEdMaterial(educationalMaterialRepository.findById(2).get());
        taskPerformance2.setDoneBy(userRepository.findById(4).get());
        taskPerformance2.setAnswer("Which one?");
        taskPerformance2.setDateOfCompletion(LocalDate.of(2024, 5, 12));
        taskPerformanceRepository.save(taskPerformance2);
    }

    private void seedCertificateTable() {
        Certificate certificate = new Certificate();
        certificate.setTeacher(userRepository.findById(1).get());
        certificate.setApplication(applicationRepository.findById(1).get());
        certificate.setDateOfIssue(LocalDate.of(2024, 5, 10));
        certificateRepository.save(certificate);
    }

}
