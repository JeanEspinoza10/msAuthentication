package co.com.bancolombia.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBody {

    private String name;
    private String lastName;
    private String email;
    private String documentIdentity;
    private String phone;
    private BigDecimal baseSalary;
    private LocalDate birthDate;
    private String address;
    private Long rolId;
}
