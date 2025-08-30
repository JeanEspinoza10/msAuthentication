package co.com.bancolombia.api.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;

@Slf4j
public class RequestLogger {

    private RequestLogger() {}

    public static void logRequest(ServerRequest request, String action) {
        String clientIp = extractClientIp(request);

        log.info("[{}] Incoming request path={} ip={} method={}",
                action, request.path(), clientIp, request.method());
    }

    private static String extractClientIp(ServerRequest request) {
        String clientIp = request.headers().firstHeader("X-Forwarded-For");
        if (clientIp == null) {
            clientIp = request.remoteAddress()
                    .map(adder -> adder.getAddress().getHostAddress())
                    .orElse("unknown");
        }
        return clientIp;
    }
    public static void logResponse(String action, HttpStatus status) {
        log.info("[{}] Response status={}", action, status.value());
    }

    public static void logAction(String action, String message) {
        log.info("[{}] {}", action, message);
    }
    public static void logError(String errorType, Throwable ex, HttpStatus status) {
        log.error("[{}] {} | status={}", errorType, ex.getMessage(), status.value());
    }
}
