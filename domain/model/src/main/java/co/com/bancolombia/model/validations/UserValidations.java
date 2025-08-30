package co.com.bancolombia.model.validations;


import co.com.bancolombia.model.config.CreateUserConfig;
import co.com.bancolombia.model.exception.DomainException;
import co.com.bancolombia.model.users.UserModel;


import java.math.BigDecimal;
public class UserValidations {

    private UserValidations() {}

    public static void validateMandatoryFields(UserModel user) {
        if (user.getName() == null || user.getName().isBlank()) {
            throw DomainException.emptyField("name");
        }
        if (user.getLastName() == null || user.getLastName().isBlank()) {
            throw DomainException.emptyField("lastName");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw DomainException.emptyField("email");
        }
        if (user.getBaseSalary() == null) {
            throw DomainException.emptyField("baseSalary");
        }
    }

    public static void validateBaseSalary(UserModel user) {
        BigDecimal salary = user.getBaseSalary();
        if (salary.compareTo(BigDecimal.ZERO) < 0 ||
                salary.compareTo(new BigDecimal("15000000")) > 0) {
            throw DomainException.invalidBaseSalaryMount();
        }
    }

    public static void validateEmailFormat(UserModel user) {
        String emailRegex = "^[\\w.-]+@bancolombia\\.com$";
        if (!user.getEmail().matches(emailRegex)) {
            throw DomainException.invalidEmail();
        }
    }
    public static UserModel withDefaultRoleIfNull(UserModel user) {
        return user.getRolId() == null
                ? user.toBuilder().rolId(3L).build()
                : user;
    }
}