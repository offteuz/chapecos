package br.com.fiap.chapecos.dto.request;

import jakarta.validation.constraints.NotNull;

public record UserUpdatePasswordRequestDTO(

        @NotNull
        String currentPassword,

        @NotNull
        String newPassword
) {
}
