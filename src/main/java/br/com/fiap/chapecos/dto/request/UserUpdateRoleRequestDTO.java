package br.com.fiap.chapecos.dto.request;

import br.com.fiap.chapecos.model.Role;
import jakarta.validation.constraints.NotNull;
public record UserUpdateRoleRequestDTO(

        @NotNull
        Role role
) {
}
