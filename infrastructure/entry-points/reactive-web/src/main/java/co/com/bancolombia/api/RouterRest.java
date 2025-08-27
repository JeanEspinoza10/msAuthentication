package co.com.bancolombia.api;

import co.com.bancolombia.model.exception.DomainException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import java.util.Map;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route()
                .POST("/api/v1/users", handler::createUser)
                .filter(errorHandler())
                .build();

    }
    private HandlerFilterFunction<ServerResponse, ServerResponse> errorHandler() {
        return (request, next) -> next.handle(request)
                .onErrorResume(DomainException.class, ex ->
                        ServerResponse.badRequest()
                                .bodyValue(Map.of("error", ex.getMessage()))
                )
                .onErrorResume(Exception.class, ex ->
                        ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .bodyValue(Map.of(
                                        "error", "Error in server"
                                ))
                );
    }
}
