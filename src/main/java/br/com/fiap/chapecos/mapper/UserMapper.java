package br.com.fiap.chapecos.mapper;

import br.com.fiap.chapecos.dto.request.UserRequestDTO;
import br.com.fiap.chapecos.dto.request.UserRoleRequestDTO;
import br.com.fiap.chapecos.dto.response.UserResponseDTO;
import br.com.fiap.chapecos.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "audit", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    void updateAll(UserRoleRequestDTO dto, @MappingTarget User user);
}
