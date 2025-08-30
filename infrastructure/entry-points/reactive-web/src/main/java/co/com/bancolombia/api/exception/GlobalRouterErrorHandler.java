package co.com.bancolombia.api.exception;


import co.com.bancolombia.api.utils.RequestLogger;
import co.com.bancolombia.model.exception.DomainException;
import co.com.bancolombia.model.response.CreateUserResponse;
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
                        {
                            RequestLogger.logError("Domain", ex, HttpStatus.BAD_REQUEST);
                            return ServerResponse.badRequest()
                                    .bodyValue(CreateUserResponse.error(ex.getMessage()));
                        }
                )
                .onErrorResume(IllegalArgumentException.class, ex ->{
                            RequestLogger.logError("Validation", ex, HttpStatus.BAD_REQUEST);
                            return ServerResponse.badRequest()
                                    .bodyValue(CreateUserResponse.error(ex.getMessage()));
                        }
                )
                .onErrorResume(Exception.class, ex ->{
                    RequestLogger.logError("Server", ex, HttpStatus.INTERNAL_SERVER_ERROR);
                            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .bodyValue(Map.of("error", "Error in server"));
                        }
                );
    }
}