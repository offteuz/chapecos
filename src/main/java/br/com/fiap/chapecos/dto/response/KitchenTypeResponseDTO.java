package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.model.KitchenType;
import com.fasterxml.jackson.annotation.JsonView;

public record KitchenTypeResponseDTO(

        @JsonView(View.Synthetic.class)
        String name,

        @JsonView(View.Synthetic.class)
        KitchenCategoryResponseDTO kitchenCategory
) {

    public KitchenTypeResponseDTO(KitchenType kitchenType) {
        this(
                kitchenType.getName(),
                new KitchenCategoryResponseDTO(kitchenType.getCategoryKitchen())
        );
    }
}
