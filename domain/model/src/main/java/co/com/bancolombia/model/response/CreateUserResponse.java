package co.com.bancolombia.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserResponse {
    private String email;
    private String message;
}
