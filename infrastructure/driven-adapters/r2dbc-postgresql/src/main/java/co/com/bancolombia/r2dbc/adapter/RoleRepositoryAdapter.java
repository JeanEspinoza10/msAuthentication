package co.com.bancolombia.r2dbc.adapter;

import co.com.bancolombia.model.roles.Rol;
import co.com.bancolombia.model.roles.gateways.RoleRepository;
import co.com.bancolombia.r2dbc.entity.RoleEntity;
import co.com.bancolombia.r2dbc.repository.RoleReactiveRepository;
import co.com.bancolombia.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryAdapter extends ReactiveAdapterOperations<
        Rol/* change for domain model */,
        RoleEntity/* change for adapter model */,
        Long,
        RoleReactiveRepository
> implements RoleRepository {
    public RoleRepositoryAdapter(RoleReactiveRepository roleReactiveRepository, ObjectMapper mapper) {

        super(roleReactiveRepository, mapper, d -> mapper.map(d, Rol.class));
    }

}
