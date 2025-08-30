package co.com.bancolombia.usecase.createuser;

import co.com.bancolombia.model.exception.DomainException;
import co.com.bancolombia.model.response.CreateUserResponse;
import co.com.bancolombia.model.roles.gateways.RoleRepository;
import co.com.bancolombia.model.users.UserModel;
import co.com.bancolombia.model.users.gateways.UserRepository;
import co.com.bancolombia.model.validations.UserValidations;
import reactor.core.publisher.Mono;



public class CreateUserUseCase {
    private final UserRepository userRepository;


    public CreateUserUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public Mono<CreateUserResponse> execute(UserModel userModel) {

        return Mono.just(userModel)
                .map(u -> {
                    UserValidations.validateMandatoryFields(u);
                    UserValidations.validateBaseSalary(u);
                    UserValidations.validateEmailFormat(u);
                    return u;
                })
                .flatMap(u ->
                        userRepository.findByEmail(u.getEmail())
                                .hasElement()
                                .flatMap(exists -> Boolean.TRUE.equals(exists)
                                        ? Mono.error(DomainException.duplicateEmail())
                                        : Mono.just(UserValidations.withDefaultRoleIfNull(u))
                                )
                )
                .flatMap(userRepository::save)
                .map(saved->CreateUserResponse.success());

    }

}
