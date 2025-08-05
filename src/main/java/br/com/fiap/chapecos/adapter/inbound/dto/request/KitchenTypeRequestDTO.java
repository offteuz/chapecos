package br.com.fiap.chapecos.adapter.inbound.dto.request;

import jakarta.validation.constraints.NotNull;

public record KitchenTypeRequestDTO(

        @NotNull
        String name,

        @NotNull
        KitchenCategoryRequestDTO kitchenCategory
) {
}
