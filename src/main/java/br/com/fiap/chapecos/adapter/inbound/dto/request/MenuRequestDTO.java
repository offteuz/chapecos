package br.com.fiap.chapecos.adapter.inbound.dto.request;

import br.com.fiap.chapecos.domain.model.MenuType;
import jakarta.validation.constraints.NotNull;

public record MenuRequestDTO(

        @NotNull
        MenuType menuType,

        @NotNull
        Long idEstablishment
) {
}
