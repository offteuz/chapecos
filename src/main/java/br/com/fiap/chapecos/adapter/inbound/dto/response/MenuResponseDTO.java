package br.com.fiap.chapecos.adapter.inbound.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.domain.model.Audit;
import br.com.fiap.chapecos.domain.model.Item;
import br.com.fiap.chapecos.domain.model.Menu;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Set;

public record MenuResponseDTO(

        @JsonView(View.Compact.class)
        Long idMenu,

        @JsonView(View.Compact.class)
        MenuTypeResponseDTO menuType,

        @JsonView(View.Analytic.class)
        Set<Item> items,

        @JsonView(View.Compact.class)
        EstablishmentResponseDTO establishment,

        @JsonView(View.Complete.class)
        Audit audit
) {

    public MenuResponseDTO(Menu menu) {
        this(
                menu.getId(),
                new MenuTypeResponseDTO(menu.getMenuType()),
                menu.getItems(),
                new EstablishmentResponseDTO(menu.getEstablishment()),
                menu.getAudit()
        );
    }
}
