package br.com.fiap.chapecos.adapter.inbound.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.domain.model.Establishment;
import com.fasterxml.jackson.annotation.JsonView;

public record EstablishmentSummaryResponseDTO(
        @JsonView(View.Compact.class)
        Long id,

        @JsonView(View.Compact.class)
        String name
) {
    public EstablishmentSummaryResponseDTO(Establishment establishment) {
        this(
                establishment != null ? establishment.getId() : null,
                establishment != null ? establishment.getName() : null
        );
    }
}