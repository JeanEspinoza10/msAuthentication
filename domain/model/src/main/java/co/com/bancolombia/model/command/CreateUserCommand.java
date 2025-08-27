package co.com.bancolombia.model.command;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserCommand {
    private String name;
    private String lastName;
    private String email;
    private String documentIdentity;
    private String phone;
    private BigDecimal baseSalary;
}
