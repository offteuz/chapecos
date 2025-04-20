package br.com.fiap.chapecos.mapper;

import br.com.fiap.chapecos.dto.request.RoleRequestDTO;
import br.com.fiap.chapecos.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role role(RoleRequestDTO dto);

    RoleRequestDTO dto(Role role);
}
