package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.model.MenuType;
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
