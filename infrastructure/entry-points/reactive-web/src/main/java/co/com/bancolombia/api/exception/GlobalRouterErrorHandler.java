package co.com.bancolombia.api.exception;


import co.com.bancolombia.model.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Map;

@Component
public class GlobalRouterErrorHandler {

    public HandlerFilterFunction<ServerResponse, ServerResponse> errorHandler() {
        return (request, next) -> next.handle(request)
                .onErrorResume(DomainException.class, ex ->
                        ServerResponse.badRequest()
                                .bodyValue(Map.of("error", ex.getMessage()))
                )
                .onErrorResume(IllegalArgumentException.class, ex ->
                        ServerResponse.badRequest()
                                .bodyValue(Map.of("error", ex.getMessage()))
                )
                .onErrorResume(Exception.class, ex ->
                        ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .bodyValue(Map.of("error", "Error in server"))
                );
    }
}