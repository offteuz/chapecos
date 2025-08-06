package br.com.fiap.chapecos.adapter.inbound.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.domain.model.Audit;
import br.com.fiap.chapecos.domain.model.Establishment;
import br.com.fiap.chapecos.domain.model.RegistrationTime;
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

        @JsonView(View.Synthetic.class)
        Set<RegistrationTime> registrationTimes,

        @JsonView(View.Complete.class)
        AuditResponseDTO audit
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
                new AuditResponseDTO(establishment.getAudit())
        );
    }
}
