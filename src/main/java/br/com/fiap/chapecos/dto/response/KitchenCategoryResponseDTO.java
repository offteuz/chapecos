package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.model.KitchenCategory;
import com.fasterxml.jackson.annotation.JsonView;

public record KitchenCategoryResponseDTO(

        @JsonView(View.Synthetic.class)
        String name
) {

    public KitchenCategoryResponseDTO(KitchenCategory kitchenCategory) {
        this(kitchenCategory.getName());
    }
}
