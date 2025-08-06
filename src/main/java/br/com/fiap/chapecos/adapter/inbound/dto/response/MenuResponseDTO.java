package br.com.fiap.chapecos.adapter.inbound.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.domain.model.Audit;
import br.com.fiap.chapecos.domain.model.Item;
import br.com.fiap.chapecos.domain.model.Menu;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Set;
import java.util.stream.Collectors;

public record MenuResponseDTO(

        @JsonView(View.Compact.class)
        Long idMenu,

        @JsonView(View.Compact.class)
        MenuTypeResponseDTO menuType,

        @JsonView(View.Analytic.class)
        Set<ItemResponseDTO> items,

        @JsonView(View.Compact.class)
        EstablishmentResponseDTO establishment,

        @JsonView(View.Complete.class)
        AuditResponseDTO audit
) {

    public MenuResponseDTO(Menu menu) {
        this(
                menu.getId(),
                new MenuTypeResponseDTO(menu.getMenuType()),
                menu.getItems().stream()
                        .map(ItemResponseDTO::new)
                        .collect(Collectors.toSet()),
                new EstablishmentResponseDTO(menu.getEstablishment()),
                new AuditResponseDTO(menu.getAudit())
        );
    }
}
