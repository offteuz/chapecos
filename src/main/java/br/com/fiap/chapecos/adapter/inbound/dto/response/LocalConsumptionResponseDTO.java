package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.model.LocalConsumption;

public record LocalConsumptionResponseDTO(

        String name
) {

    public LocalConsumptionResponseDTO(LocalConsumption localConsumption) {
        this(localConsumption.getName());
    }
}
