package br.com.fiap.chapecos.dto.request;

import br.com.fiap.chapecos.model.MenuType;

public record MenuRequestDTO(

        MenuType menuType,

        Long idEstablishment
) {
}
