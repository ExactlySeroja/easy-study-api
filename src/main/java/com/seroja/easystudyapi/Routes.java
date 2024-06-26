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
     * General routes
     */
    public static final String GET_ALL_COURSES = API_URL + "/courses";
    public static final String GET_ALL_THEMES_BY_COURSE = API_URL + "/courses/{id}/themes";
    public static final String GET_COURSE_BY_ID = API_URL + "/courses/{id}";
    public static final String GET_MATERIALS_BY_THEME = API_URL + "/themes/{id}/materials";
    public static final String GET_ALL_CATEGORIES = API_URL + "/categories";
    public static final String CHECK_EXISTING_USER_EMAIL = API_URL + "/check-email";
    public static final String CHECK_EXISTING_USER_PHONE = API_URL + "/check-phone";
    public static final String CHECK_EXISTING_USER_USERNAME = API_URL + "/check-username";
    public static final String GET_THEME_BY_ID = API_URL + "/themes/{id}";

    /**
     * Teacher routes
     */
    public static final String TEACHER = API_URL + "/teacher";

    public static final String TEACHER_GET_ALL_MY_COURSES = TEACHER + "/my-courses";
    public static final String TEACHER_CREATE_NEW_COURSE = TEACHER + "/courses";
    public static final String TEACHER_DELETE_UPDATE_COURSE = TEACHER + "/courses/{id}";

    public static final String TEACHER_GET_STUDENTS_BY_COURSE_ID = TEACHER + "/courses/{id}/students";
    public static final String TEACHER_GET_PERFORMANCE_BY_STUDENT_ID = TEACHER + "/students/{id}/performance";

    public static final String TEACHER_CREATE_THEME = TEACHER + "/theme";
    public static final String TEACHER_GET_THEME_BY_ID = TEACHER + "/theme/{id}";

    public static final String TEACHER_GET_MATERIAL_BY_ID = TEACHER + "/materials/{id}";
    public static final String TEACHER_CREATE_NEW_MATERIAL = TEACHER + "/materials";

    public static final String TEACHER_GET_ALL_TASK_PERFORMANCE_BY_MATERIAL = TEACHER + "/materials/{id}/performance";
    public static final String TEACHER_UPDATE_PERFORMANCE = TEACHER + "/performance/{id}";

    public static final String TEACHER_CREATE_NEW_CERTIFICATE = TEACHER + "/certificate";
    public static final String TEACHER_GET_ALL_CERTIFICATES = TEACHER + "/certificates";
    public static final String TEACHER_GET_CERTIFICATE_BY_ID = TEACHER + "/certificate/{id}";

    public static final String TEACHER_GET_ALL_APPLICATIONS = TEACHER + "/applications";
    public static final String TEACHER_GET_APPLICATION_BY_ID = TEACHER + "/application/{id}";


    public static final String TEACHER_GET_STUDENTS = TEACHER + "/students";
    public static final String TEACHER_MY_PROFILE = TEACHER + "/profile";

    /**
     * Student routes
     */
    public static final String STUDENT = API_URL + "/student";

    public static final String STUDENT_GET_ALL_MY_COURSES = STUDENT + "/my-courses";
    public static final String STUDENT_GET_ALL_MY_APPLICATIONS = STUDENT + "/applications";
    public static final String STUDENT_GET_ALL_MY_CERTIFICATES = STUDENT + "/certificates";
    public static final String STUDENT_GET_MATERIAL = STUDENT + "/materials/{materialId}";
    public static final String STUDENT_CREATE_APPLICATION = STUDENT + "/application";
    public static final String STUDENT_GET_MY_TASK_PERFORMANCE_BY_MATERIAL = STUDENT + "/materials/{materialId}/my-performance";
    public static final String STUDENT_ADD_TASK_PERFORMANCE = STUDENT + "/performance";


    public static final String[] SWAGGER_ENDPOINTS = {
            "api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

}
