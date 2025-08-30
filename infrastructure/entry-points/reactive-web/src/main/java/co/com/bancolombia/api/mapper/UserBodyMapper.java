package co.com.bancolombia.api.mapper;

import co.com.bancolombia.api.request.UserBody;
import co.com.bancolombia.model.users.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserBodyMapper {

    UserBodyMapper INSTANCE = Mappers.getMapper(UserBodyMapper.class);

    @Mapping(target = "id", ignore = true)

    UserModel toModel(UserBody userBody);
}
