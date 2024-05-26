package com.seroja.easystudyapi;

public class Routes {

    private static final String API_URL = "/easy-study-api";

    /**
     * Users management
     */
    public static final String LOGIN = API_URL + "/login";
    public static final String REGISTER_ROUTE_SECURITY = API_URL + "/registration";
    public static final String REFRESH_TOKEN = API_URL + "/refresh-token";

    /**
     * Teacher routes
     */
    public static final String TEACHER = API_URL + "/teacher";
    public static final String TEACHER_GET_ALL_APPLICATIONS = TEACHER + "/applications/";
    public static final String TEACHER_CHECK_APPLICATION_BY_ID = TEACHER + "/application/{id}";
    public static final String TEACHER_ADD_APPLICATION = TEACHER + "/application";

    public static final String TEACHER_GET_ALL_CATEGORIES = TEACHER + "/categories";
    public static final String TEACHER_CATEGORY_BY_ID = TEACHER + "/category/{id}";
    public static final String TEACHER_ADD_CATEGORY = TEACHER + "/category";

    public static final String TEACHER_GET_ALL_CERTIFICATES = TEACHER + "/certificates";
    public static final String TEACHER_CERTIFICATE_BY_ID = TEACHER + "/certificate/{id}";
    public static final String TEACHER_ADD_CERTIFICATE = TEACHER + "/certificate";

    public static final String TEACHER_GET_ALL_COURSES = TEACHER + "/courses";
    public static final String TEACHER_COURSE_BY_ID = TEACHER + "/course/{id}";
    public static final String TEACHER_ADD_COURSE = TEACHER + "/course";

    public static final String TEACHER_GET_ALL_EDUCATIONAL_MATERIAL = TEACHER + "/educational-materials";
    public static final String TEACHER_EDUCATIONAL_MATERIAL_BY_ID = TEACHER + "/educational-material/{id}";
    public static final String TEACHER_ADD_EDUCATIONAL_MATERIAL = TEACHER + "/educational-material";

    public static final String TEACHER_GET_ALL_TASK_PERFORMANCE = TEACHER + "/performances";
    public static final String TEACHER_TASK_PERFORMANCE_BY_ID = TEACHER + "/performance/{id}";

    public static final String TEACHER_GET_ALL_THEMES = TEACHER + "/themes";
    public static final String TEACHER_THEME_BY_ID = TEACHER + "/themes/{id}";
    public static final String TEACHER_ADD_THEME = TEACHER + "/theme";

    public static final String TEACHER_GET_ALL_USER = TEACHER + "/users";
    public static final String TEACHER_USER_BY_ID = TEACHER + "/profile";
    public static final String TEACHER_ADD_USER = TEACHER + "/user";

    /**
     * Student routes
     */
    public static final String STUDENT = API_URL + "/student";

    public static final String STUDENT_GET_ALL_MY_COURSES = STUDENT + "/my-courses";
    public static final String STUDENT_GET_ALL_AVAILABLE_COURSES = STUDENT + "/courses";
    public static final String STUDENT_GET_ALL_THEMES_BY_COURSE = STUDENT + "/courses/{courseId}/themes";
    public static final String STUDENT_GET_ALL_MATERIALS_BY_THEME = STUDENT + "/themes/{themeId}/materials";
    public static final String STUDENT_FILTER_COURSE_BY_NAME = STUDENT + "/courses/like/{courseName}";
    public static final String STUDENT_FILTER_COURSE_BY_CATEGORY = STUDENT + "/courses/{categoryId}";
    public static final String STUDENT_FILTER_COURSE_BY_PRICE = STUDENT + "/courses/filter-by-price/{order}";
    public static final String STUDENT_FILTER_COURSE_BY_START_DATE = STUDENT + "/courses/filter-by-start-date/{order}";
    public static final String STUDENT_GET_ALL_MY_APPLICATIONS = STUDENT + "/applications";
    public static final String STUDENT_GET_ALL_MY_CERTIFICATES = STUDENT + "/certificates";
    public static final String STUDENT_GET_MATERIAL = STUDENT + "/materials/{materialId}";
    public static final String STUDENT_CREATE_APPLICATION = STUDENT + "/application";

    public static final String STUDENT_ADD_TASK_PERFORMANCE = STUDENT + "/performance";


    public static final String[] SWAGGER_ENDPOINTS = {
            "api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

}
