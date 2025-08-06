package br.com.fiap.chapecos.adapter.inbound.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.domain.model.LocalConsumption;
import com.fasterxml.jackson.annotation.JsonView;

public record LocalConsumptionResponseDTO(

        @JsonView(View.Analytic.class)
        String name
) {

    public LocalConsumptionResponseDTO(LocalConsumption localConsumption) {
        this(localConsumption.getName());
    }
}
