package co.com.bancolombia.api;

import co.com.bancolombia.api.mapper.UserBodyMapper;
import co.com.bancolombia.api.request.UserBody;
import co.com.bancolombia.usecase.createuser.CreateUserUseCase;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class Handler {

    private final CreateUserUseCase createUserUseCase;

    public Handler(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(UserBody.class)
                .switchIfEmpty(Mono.error(new RuntimeException("Request body is empty or invalid")))
                .flatMap(userBody -> createUserUseCase.execute(UserBodyMapper.toRequest(userBody)))
                .flatMap(res -> ServerResponse.ok().bodyValue(res.getId()));
    }
}
