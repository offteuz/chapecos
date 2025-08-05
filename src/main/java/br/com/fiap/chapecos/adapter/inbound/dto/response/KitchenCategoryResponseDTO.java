package br.com.fiap.chapecos.adapter.inbound.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.domain.model.KitchenCategory;
import com.fasterxml.jackson.annotation.JsonView;

public record KitchenCategoryResponseDTO(

        @JsonView(View.Compact.class)
        String name
) {

    public KitchenCategoryResponseDTO(KitchenCategory kitchenCategory) {
        this(kitchenCategory.getName());
    }
}
