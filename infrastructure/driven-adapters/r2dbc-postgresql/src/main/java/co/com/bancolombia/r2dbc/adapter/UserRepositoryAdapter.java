package co.com.bancolombia.r2dbc.adapter;

import co.com.bancolombia.model.users.UserModel;
import co.com.bancolombia.model.users.gateways.UserRepository;
import co.com.bancolombia.r2dbc.entity.UserEntity;
import co.com.bancolombia.r2dbc.helper.ReactiveAdapterOperations;
import co.com.bancolombia.r2dbc.repository.UserReactiveRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

@Repository
public class UserRepositoryAdapter extends ReactiveAdapterOperations<
        UserModel,
        UserEntity,
        Long,
        UserReactiveRepository
        > implements UserRepository {

    private final TransactionalOperator txOperator;

    public UserRepositoryAdapter(
            UserReactiveRepository userReactiveRepository,
            ObjectMapper mapper,
            TransactionalOperator txOperator
    ) {
        super(userReactiveRepository, mapper, d -> mapper.map(d, UserModel.class));
        this.txOperator = txOperator;
    }


    @Override
    public Mono<UserModel> findByEmail(String email) {
        return repository
                .findByEmail(email)
                .map(entity -> mapper.map(entity, UserModel.class));
    }

    @Override
    public Mono<UserModel> findByDocumentIdentity(String documentIdentity) {
        return repository
                .findByDocumentIdentity(documentIdentity)
                .map(entity -> mapper.map(entity, UserModel.class));
    }

    @Override
    public Mono<UserModel> save(UserModel model) {
        return txOperator.transactional(
                repository.save(mapper.map(model, UserEntity.class))
                        .map(entity -> mapper.map(entity, UserModel.class))
        );
    }

}
