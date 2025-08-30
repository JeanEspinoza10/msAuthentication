package co.com.bancolombia.model.users;


import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;


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

}
