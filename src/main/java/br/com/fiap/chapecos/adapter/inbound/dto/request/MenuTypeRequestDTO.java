package br.com.fiap.chapecos.adapter.inbound.dto.request;

import jakarta.validation.constraints.NotNull;

public record MenuTypeRequestDTO(

        @NotNull
        String name
) {
}
