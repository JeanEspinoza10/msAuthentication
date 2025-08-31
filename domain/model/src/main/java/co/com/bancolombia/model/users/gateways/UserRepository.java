package co.com.bancolombia.model.users.gateways;

import co.com.bancolombia.model.users.UserModel;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<UserModel> findById (Long id);
    Mono<UserModel> save (UserModel user);
    Mono<UserModel> findByEmail(String email);
    Mono<UserModel> findByDocumentIdentity(String documentIdentity);
}
