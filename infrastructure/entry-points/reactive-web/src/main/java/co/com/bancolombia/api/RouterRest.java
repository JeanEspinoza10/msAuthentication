package co.com.bancolombia.api;

import co.com.bancolombia.api.exception.GlobalRouterErrorHandler;
import co.com.bancolombia.api.request.UserBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/users",
                    produces = { MediaType.APPLICATION_JSON_VALUE },
                    method = RequestMethod.POST,
                    beanClass = Handler.class,
                    beanMethod = "createUser",
                    operation = @Operation(
                            summary = "Create user",
                            description = "Creates a new user in the system",
                            tags = { "Users" },
                            requestBody = @RequestBody(
                                    description = "User creation payload",
                                    required = true,
                                    content = @Content(
                                            schema = @Schema(implementation = UserBody.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "User created successfully"),
                                    @ApiResponse(responseCode = "400", description = "Invalid request"),
                                    @ApiResponse(responseCode = "500", description = "Internal server error")
                            }
                    )
            )
    })

    public RouterFunction<ServerResponse> routerFunction(Handler handler,  GlobalRouterErrorHandler globalRouterErrorHandler) {
        return route()
                .POST("/api/v1/users", handler::createUser)
                .filter(globalRouterErrorHandler.errorHandler())
                .build();
    }

}
