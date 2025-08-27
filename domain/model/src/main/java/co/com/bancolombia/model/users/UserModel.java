package co.com.bancolombia.model.users;
import co.com.bancolombia.model.exception.DomainException;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserModel {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String documentIdentity;
    private String phone;
    private BigDecimal baseSalary;
    private Long rolId;

    public Mono<UserModel> validateMandatoryFieldsReactive() {
        return Mono.defer(() -> {
            Map<String, Object> fields = new HashMap<>();
            fields.put("name", name);
            fields.put("lastName", lastName);
            fields.put("email", email);
            fields.put("baseSalary", baseSalary);

            for (Map.Entry<String, Object> entry : fields.entrySet()) {
                Object value = entry.getValue();

                if (value == null) {
                    return Mono.error(DomainException.emptyField(entry.getKey()));
                }

                if (value instanceof String s && s.isBlank()) {
                    return Mono.error(DomainException.emptyField(entry.getKey()));
                }
            }

            return Mono.just(this);
        });
    }
    public Mono<UserModel> validateBaseSalaryReactive() {
        return Mono.defer(() -> {
            if (baseSalary == null) {
                return Mono.error(DomainException.invalidBaseSalaryFormat());
            }
            try {
                BigDecimal salary =baseSalary;
                if (salary.compareTo(BigDecimal.ZERO) < 0 || salary.compareTo(new BigDecimal("15000000")) > 0) {
                    return Mono.error(DomainException.invalidBaseSalaryMount());
                }
            } catch (NumberFormatException e) {
                return Mono.error(DomainException.invalidBaseSalaryFormat());
            }
            return Mono.just(this);
        });
    }
    public Mono<UserModel> validateEmailFormatReactive() {
        return Mono.defer(() -> {
            String emailRegex = "^[\\w.-]+@bancolombia\\.com$";
            Pattern pattern = Pattern.compile(emailRegex);
            if (!pattern.matcher(email).matches()) {
                return Mono.error(DomainException.invalidEmail());
            }
            return Mono.just(this);
        });
    }
}
