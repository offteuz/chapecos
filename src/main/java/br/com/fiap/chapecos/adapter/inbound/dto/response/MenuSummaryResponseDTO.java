package br.com.fiap.chapecos.adapter.inbound.dto.response;

import br.com.fiap.chapecos.domain.model.Menu;

public record MenuSummaryResponseDTO(
        Long idMenu,
        MenuTypeResponseDTO menuType
) {
    public MenuSummaryResponseDTO(Menu menu) {
        this(menu.getId(), new MenuTypeResponseDTO(menu.getMenuType()));
    }
}