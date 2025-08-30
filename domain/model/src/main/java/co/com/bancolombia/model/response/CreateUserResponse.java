package co.com.bancolombia.model.response;

import co.com.bancolombia.model.config.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserResponse {
    private String message;
    private Long id;

    public static CreateUserResponse success(Long id) {
        return new CreateUserResponse(Messages.CREATE_USER_OK, id);
    }

}
