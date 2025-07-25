package br.com.fiap.chapecos.mapper;

import br.com.fiap.chapecos.dto.request.UserUpdateRequestDTO;
import br.com.fiap.chapecos.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    void updateAll(UserUpdateRequestDTO dto, @MappingTarget User user);
}
