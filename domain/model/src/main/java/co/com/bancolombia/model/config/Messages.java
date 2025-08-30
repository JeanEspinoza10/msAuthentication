package co.com.bancolombia.model.config;

public class Messages {
    private Messages(){}
    public static final String CREATE_USER_OK = "User created successfully";
    public static final String CREATE_USER_ERROR = "Error creating user";
    public static final String INVALID_USER_EMAIL = "Email format invalid";
    public static final String DUPLICATE_USER_EMAIL = "Can't create user with duplicate email";
    public static final String INVALID_BASE_SALARY_MOUNT = "The field 'baseSalary' must be between 0 and 15,000,000";
    public static final String INVALID_BASE_SALARY_FORMAT = "The field 'baseSalary' must be a valid number";
    public static final String EMPTY_FIELD = "The field '%s' cannot be empty";
}

