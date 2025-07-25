package br.com.fiap.chapecos.dto.request;

import br.com.fiap.chapecos.model.LocalConsumption;

public record ItemRequestDTO(

        String name,

        String description,

        Double price,

        LocalConsumption localConsumption,

        Long idMenu
) {
}
