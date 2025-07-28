package br.com.fiap.chapecos.dto.request;

public record KitchenTypeRequestDTO(

        String name,

        KitchenCategoryRequestDTO kitchenCategory
) {
}
