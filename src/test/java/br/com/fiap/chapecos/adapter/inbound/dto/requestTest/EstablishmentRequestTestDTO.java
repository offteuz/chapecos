package br.com.fiap.chapecos.adapter.inbound.dto.requestTest;

import br.com.fiap.chapecos.adapter.inbound.dto.request.AddressRequestDTO;
import br.com.fiap.chapecos.domain.model.KitchenType;

public record EstablishmentRequestTestDTO(

        // Validações removidas para o ambiente de teste
        String name,

        String cnpj,

        KitchenType kitchenType,

        AddressRequestDTO address,

        String timeZone,

        Long userId
) {}
