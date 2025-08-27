package co.com.bancolombia.r2dbc.adapter;

import co.com.bancolombia.model.users.UserModel;
import co.com.bancolombia.model.users.gateways.UserRepository;
import co.com.bancolombia.r2dbc.entity.UserEntity;
import co.com.bancolombia.r2dbc.helper.ReactiveAdapterOperations;
import co.com.bancolombia.r2dbc.repository.UserReactiveRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserRepositoryAdapter extends ReactiveAdapterOperations<
        UserModel,
        UserEntity,
        Long,
        UserReactiveRepository
        > implements UserRepository {


    public UserRepositoryAdapter(UserReactiveRepository userReactiveRepository, ObjectMapper mapper) {
        super(userReactiveRepository, mapper, d -> mapper.map(d, UserModel.class));
    }

    @Override
    public Mono<UserModel> findByEmail(String email) {
        return repository
                .findByEmail(email)
                .map(entity -> mapper.map(entity, UserModel.class));
    }
}
