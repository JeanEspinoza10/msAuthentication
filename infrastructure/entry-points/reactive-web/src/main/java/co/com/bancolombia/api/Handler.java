package co.com.bancolombia.api;

import co.com.bancolombia.api.mapper.UserBodyMapper;
import co.com.bancolombia.api.request.UserBody;
import co.com.bancolombia.api.utils.RequestLogger;
import co.com.bancolombia.usecase.createuser.CreateUserUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class Handler {

    private final CreateUserUseCase createUserUseCase;
    private final UserBodyMapper userBodyMapper;

    public Handler(CreateUserUseCase createUserUseCase, UserBodyMapper userBodyMapper) {
        this.createUserUseCase = createUserUseCase;
        this.userBodyMapper = userBodyMapper;
    }

    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        RequestLogger.logRequest(serverRequest, "CreateUser");
        return serverRequest.bodyToMono(UserBody.class)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Request body is empty or invalid")))
                .flatMap(userBody -> createUserUseCase.execute(userBodyMapper.toModel(userBody)))
                .flatMap(res -> {
                    RequestLogger.logResponse("CreateUser", HttpStatus.OK);
                    return ServerResponse.ok().bodyValue(res);
                });
    }
}
