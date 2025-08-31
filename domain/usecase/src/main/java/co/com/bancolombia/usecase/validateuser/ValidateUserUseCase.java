package co.com.bancolombia.usecase.validateuser;

import co.com.bancolombia.model.response.ValidatedUserResponse;
import co.com.bancolombia.model.users.gateways.UserRepository;
import reactor.core.publisher.Mono;

import java.util.Collections;


public class ValidateUserUseCase {
    private final UserRepository userRepository;

    public ValidateUserUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Mono<ValidatedUserResponse> execute(String documentIdentity){

        return userRepository.findByDocumentIdentity(documentIdentity)
                .map(user -> ValidatedUserResponse.success(
                        Collections.singletonList(ValidatedUserResponse.UserData.fromUserModel(user))
                ))
                .switchIfEmpty(Mono.just(ValidatedUserResponse.error("User not found")));
    }

}
