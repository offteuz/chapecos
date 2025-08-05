package br.com.fiap.chapecos.adapter.inbound.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.domain.model.KitchenType;
import com.fasterxml.jackson.annotation.JsonView;

public record KitchenTypeResponseDTO(

        @JsonView(View.Compact.class)
        String name,

        @JsonView(View.Compact.class)
        KitchenCategoryResponseDTO kitchenCategory
) {

    public KitchenTypeResponseDTO(KitchenType kitchenType) {
        this(
                kitchenType.getName(),
                new KitchenCategoryResponseDTO(kitchenType.getCategoryKitchen())
        );
    }
}
