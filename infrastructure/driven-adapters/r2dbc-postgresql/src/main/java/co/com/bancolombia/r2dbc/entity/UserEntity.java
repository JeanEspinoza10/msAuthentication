package co.com.bancolombia.r2dbc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("users")
public class UserEntity {

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("email")
    private String email;

    @Column("phone")
    private String phone;

    @Column("last_name")
    private String lastName;

    @Column("document_identity")
    private String documentIdentity;

    @Column("base_salary")
    private BigDecimal baseSalary;

    @Column("rol_id")
    private Long rolId;
}
