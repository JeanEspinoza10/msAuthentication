package co.com.bancolombia.model.roles.gateways;

import co.com.bancolombia.model.roles.Rol;
import reactor.core.publisher.Mono;

public interface RoleRepository {
    Mono<Rol> findById(Long id);
}