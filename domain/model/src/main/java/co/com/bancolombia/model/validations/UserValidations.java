package co.com.bancolombia.model.validations;

import co.com.bancolombia.model.config.CreateUserConfig;
import co.com.bancolombia.model.exception.DomainException;
import co.com.bancolombia.model.users.UserModel;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class UserValidations {

    private UserValidations() {}

    public static Mono<UserModel> validateMandatoryFields(UserModel user) {
        return Mono.defer(() -> {
            Map<String, Object> fields = new HashMap<>();
            fields.put("name", user.getName());
            fields.put("lastName", user.getLastName());
            fields.put("email", user.getEmail());
            fields.put("baseSalary", user.getBaseSalary());

            for (Map.Entry<String, Object> entry : fields.entrySet()) {
                Object value = entry.getValue();

                if (value == null) {
                    return Mono.error(DomainException.emptyField(entry.getKey()));
                }

                if (value instanceof String s && s.isBlank()) {
                    return Mono.error(DomainException.emptyField(entry.getKey()));
                }
            }

            return Mono.just(user);
        });
    }

    public static Mono<UserModel> validateBaseSalary(UserModel user) {
        return Mono.defer(() -> {
            if (user.getBaseSalary() == null) {
                return Mono.error(DomainException.invalidBaseSalaryFormat());
            }
            try {
                BigDecimal salary = user.getBaseSalary();
                if (salary.compareTo(BigDecimal.ZERO) < 0 ||
                        salary.compareTo(new BigDecimal("15000000")) > 0) {
                    return Mono.error(DomainException.invalidBaseSalaryMount());
                }
            } catch (NumberFormatException e) {
                return Mono.error(DomainException.invalidBaseSalaryFormat());
            }
            return Mono.just(user);
        });
    }

    public static Mono<UserModel> validateEmailFormat(UserModel user) {
        return Mono.defer(() -> {
            String emailRegex = "^[\\w.-]+@bancolombia\\.com$";
            Pattern pattern = Pattern.compile(emailRegex);
            if (!pattern.matcher(user.getEmail()).matches()) {
                return Mono.error(DomainException.invalidEmail());
            }
            return Mono.just(user);
        });
    }

    public static Mono<UserModel> withDefaultRoleIfNull(UserModel user) {
        return Mono.just(
                user.getRolId() == null
                        ? user.toBuilder()
                        .rolId(CreateUserConfig.DEFAULT_ROLE_ID)
                        .build()
                        : user
        );
    }
}