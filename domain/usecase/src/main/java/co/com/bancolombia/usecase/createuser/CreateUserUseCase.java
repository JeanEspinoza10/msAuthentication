package co.com.bancolombia.usecase.createuser;

import co.com.bancolombia.model.command.CreateUserCommand;
import co.com.bancolombia.model.config.CreateUserConfig;
import co.com.bancolombia.model.exception.DomainException;
import co.com.bancolombia.model.roles.gateways.RoleRepository;
import co.com.bancolombia.model.users.UserModel;
import co.com.bancolombia.model.users.gateways.UserRepository;
import reactor.core.publisher.Mono;



public class CreateUserUseCase {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public CreateUserUseCase(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public Mono<UserModel> execute(CreateUserCommand commandUser) {
        UserModel user = UserModel.builder()
                .name(commandUser.getName())
                .lastName(commandUser.getLastName())
                .email(commandUser.getEmail())
                .documentIdentity(commandUser.getDocumentIdentity())
                .phone(commandUser.getPhone())
                .baseSalary(commandUser.getBaseSalary())
                .rolId(CreateUserConfig.DEFAULT_ROLE_ID)
                .build();

        return Mono.just(user)
                .flatMap(UserModel::validateMandatoryFieldsReactive)
                .flatMap(UserModel::validateBaseSalaryReactive)
                .flatMap(UserModel::validateEmailFormatReactive)
                .flatMap(u ->
                        userRepository.findByEmail(u.getEmail())
                                .hasElement()
                                .flatMap(exists -> {
                                    if (Boolean.TRUE.equals(exists)) {
                                        return Mono.error(DomainException.duplicateEmail());
                                    } else {
                                        return Mono.just(u);
                                    }
                                })
                )
                .flatMap(userRepository::save);
    }
}
