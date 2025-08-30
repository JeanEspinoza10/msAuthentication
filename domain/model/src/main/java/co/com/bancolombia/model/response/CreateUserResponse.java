package co.com.bancolombia.model.response;

import co.com.bancolombia.model.config.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserResponse {

    private Boolean success;
    private String message;

    public static CreateUserResponse success() {
        return new CreateUserResponse(Boolean.TRUE,Messages.CREATE_USER_OK );
    }

    public static CreateUserResponse error(String message){
        return new CreateUserResponse(Boolean.FALSE,message );
    }
}
