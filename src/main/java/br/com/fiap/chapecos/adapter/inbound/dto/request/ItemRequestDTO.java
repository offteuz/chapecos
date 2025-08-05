package br.com.fiap.chapecos.adapter.inbound.dto.request;

import br.com.fiap.chapecos.domain.model.LocalConsumption;
import jakarta.validation.constraints.NotNull;

public record ItemRequestDTO(

        @NotNull
        String name,

        @NotNull
        String description,

        @NotNull
        Double price,

        @NotNull
        LocalConsumption localConsumption,

        @NotNull
        Long idMenu
) {
}
