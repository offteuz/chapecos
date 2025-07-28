package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.model.*;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Set;

public record EstablishmentResponseDTO(

        @JsonView(View.Compact.class)
        Long idEstablishment,

        @JsonView(View.Compact.class)
        String name,

        @JsonView(View.Compact.class)
        String cnpj,

        @JsonView(View.Synthetic.class)
        KitchenTypeResponseDTO kitchenType,

        @JsonView(View.Synthetic.class)
        AddressResponseDTO address,

        @JsonView(View.Synthetic.class)
        String timeZone,

        @JsonView(View.Compact.class)
        UserResponseDTO user,

        @JsonView(View.Analytic.class)
        Set<RegistrationTime> registrationTimes,

        @JsonView(View.Complete.class)
        Audit audit
) {
    public EstablishmentResponseDTO(Establishment establishment) {

        this(
                establishment.getId(),
                establishment.getName(),
                establishment.getCnpj(),
                new KitchenTypeResponseDTO(establishment.getKitchenType()),
                new AddressResponseDTO(establishment.getAddress()),
                establishment.getTimeZone(),
                new UserResponseDTO(establishment.getUser()),
                establishment.getRegistrationTimes(),
                establishment.getAudit()
        );
    }
}
