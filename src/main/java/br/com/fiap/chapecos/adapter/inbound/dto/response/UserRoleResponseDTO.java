package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.domain.model.User;

public record UserRoleResponseDTO(

        Long id,

        String email,

        String userName
) {

    public UserRoleResponseDTO(User user) {
        this(
                user.getId(),
                user.getEmail(),
                user.getUsername()
        );
    }
}
