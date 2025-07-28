package br.com.fiap.chapecos.dto.request;

import br.com.fiap.chapecos.model.KitchenType;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CNPJ;

public record EstablishmentRequestDTO(

        String name,

        @CNPJ
        String cnpj,

        KitchenType kitchenType,

        @Valid
        AddressRequestDTO address,

        String timeZone,

        Long userId
) {
}
