package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.model.Role;

import java.util.ArrayList;
import java.util.List;

public record RoleResponseDTO(

        Long id,

        String description,

        List<UserRoleResponseDTO> users
) {

    public RoleResponseDTO(Role role) {
        this(
                role.getId(),
                role.getDescription(),
                role.getUsers() != null
                        ? role.getUsers().stream().map(UserRoleResponseDTO::new).toList()
                        : new ArrayList<>()
        );
    }
}
