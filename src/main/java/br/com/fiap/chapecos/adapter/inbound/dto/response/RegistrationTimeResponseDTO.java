package br.com.fiap.chapecos.adapter.inbound.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.domain.model.Audit;
import br.com.fiap.chapecos.domain.model.RegistrationTime;
import com.fasterxml.jackson.annotation.JsonView;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record RegistrationTimeResponseDTO(

        @JsonView(View.Compact.class)
        Long id,

        @JsonView(View.Compact.class)
        DayOfWeek dayOfWeek,

        @JsonView(View.Compact.class)
        LocalTime opening,

        @JsonView(View.Compact.class)
        LocalTime closing,

        @JsonView(View.Compact.class)
        EstablishmentResponseDTO establishment,

        @JsonView(View.Complete.class)
        AuditResponseDTO audit
) {

    public RegistrationTimeResponseDTO(RegistrationTime registrationTime) {
        this(
                registrationTime.getId(),
                registrationTime.getDayOfWeek(),
                registrationTime.getOpening(),
                registrationTime.getClosing(),
                new EstablishmentResponseDTO(registrationTime.getEstablishment()),
                new AuditResponseDTO(registrationTime.getAudit())
        );
    }
}
