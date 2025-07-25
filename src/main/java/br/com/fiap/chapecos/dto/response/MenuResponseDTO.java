package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.model.*;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Set;

public record MenuResponseDTO(

        @JsonView(View.Synthetic.class)
        Long id,

        @JsonView(View.Synthetic.class)
        MenuType menuType,

        @JsonView(View.Analytic.class)
        Set<Item> items,

        @JsonView(View.Synthetic.class)
        EstablishmentResponseDTO establishment,

        @JsonView(View.Complete.class)
        Audit audit
) {

    public MenuResponseDTO(Menu menu) {
        this(
                menu.getId(),
                menu.getMenuType(),
                menu.getItems(),
                new EstablishmentResponseDTO(menu.getEstablishment()),
                menu.getAudit()
        );
    }
}
