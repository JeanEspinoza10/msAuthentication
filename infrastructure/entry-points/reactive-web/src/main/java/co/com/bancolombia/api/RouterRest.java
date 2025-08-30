package co.com.bancolombia.api;

import co.com.bancolombia.api.exception.GlobalRouterErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler,  GlobalRouterErrorHandler globalRouterErrorHandler) {
        return route()
                .POST("/api/v1/users", handler::createUser)
                .filter(globalRouterErrorHandler.errorHandler())
                .build();
    }

}
