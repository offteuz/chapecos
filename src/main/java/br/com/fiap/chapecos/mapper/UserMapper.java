package br.com.fiap.chapecos.mapper;

import br.com.fiap.chapecos.dto.request.UserRequestDTO;
import br.com.fiap.chapecos.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRequestDTO dto(User user);

    User user(UserRequestDTO dto);
}
