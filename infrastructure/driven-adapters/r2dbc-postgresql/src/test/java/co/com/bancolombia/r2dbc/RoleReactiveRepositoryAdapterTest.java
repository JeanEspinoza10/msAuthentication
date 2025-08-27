package co.com.bancolombia.r2dbc;

import co.com.bancolombia.r2dbc.adapter.RoleRepositoryAdapter;
import co.com.bancolombia.r2dbc.repository.RoleReactiveRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RoleReactiveRepositoryAdapterTest {


    @InjectMocks
    RoleRepositoryAdapter repositoryAdapter;

    @Mock
    RoleReactiveRepository repository;

    @Mock
    ObjectMapper mapper;


}
