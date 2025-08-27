package co.com.bancolombia.api.mapper;

import co.com.bancolombia.api.request.UserBody;
import co.com.bancolombia.model.command.CreateUserCommand;

public class UserBodyMapper {
    private UserBodyMapper() {}

    public static CreateUserCommand toRequest(UserBody userBody) {
        if (userBody == null) {
            return null;
        }
        return CreateUserCommand.builder()
                .name(userBody.getName())
                .lastName(userBody.getLastName())
                .email(userBody.getEmail())
                .documentIdentity(userBody.getDocumentIdentity())
                .phone(userBody.getPhone())
                .baseSalary(userBody.getBaseSalary())
                .build();
    }
}
