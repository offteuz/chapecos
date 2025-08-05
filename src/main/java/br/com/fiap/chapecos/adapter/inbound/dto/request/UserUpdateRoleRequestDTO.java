package br.com.fiap.chapecos.adapter.inbound.dto.request;

import br.com.fiap.chapecos.domain.model.Role;
import jakarta.validation.constraints.NotNull;
public record UserUpdateRoleRequestDTO(

        @NotNull
        Role role
) {
}
