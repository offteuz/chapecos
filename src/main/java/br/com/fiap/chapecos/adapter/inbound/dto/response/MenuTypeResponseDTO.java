package br.com.fiap.chapecos.adapter.inbound.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.domain.model.MenuType;
import com.fasterxml.jackson.annotation.JsonView;

public record MenuTypeResponseDTO(

        @JsonView(View.Compact.class)
        String name
) {

    public MenuTypeResponseDTO(MenuType menuType) {
        this(
                menuType.getName()
        );
    }
}
