package br.com.fiap.chapecos.adapter.inbound.dto.request;

import br.com.fiap.chapecos.domain.model.KitchenType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

public record EstablishmentRequestDTO(

        @NotNull
        String name,

        @CNPJ
        String cnpj,

        @NotNull
        KitchenType kitchenType,

        @Valid
        @NotNull
        AddressRequestDTO address,

        @NotNull
        String timeZone,

        @NotNull
        Long userId
) {
}
