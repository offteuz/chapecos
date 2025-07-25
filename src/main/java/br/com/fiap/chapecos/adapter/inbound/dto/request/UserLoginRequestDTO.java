package br.com.fiap.chapecos.dto.request;

import jakarta.validation.constraints.NotNull;

public record UserLoginRequestDTO(

        @NotNull
        String identifier,

        @NotNull
        String password
) {
}
