package br.com.fiap.chapecos.adapter.inbound.dto.response;

import br.com.fiap.chapecos.domain.model.LocalConsumption;

public record LocalConsumptionResponseDTO(

        String name
) {

    public LocalConsumptionResponseDTO(LocalConsumption localConsumption) {
        this(localConsumption.getName());
    }
}
