package br.com.fiap.chapecos.adapter.inbound.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.domain.model.Audit;
import com.fasterxml.jackson.annotation.JsonView;
import java.time.Instant;

public record AuditResponseDTO(

        @JsonView(View.Complete.class)
        String createdBy,

        @JsonView(View.Complete.class)
        Instant createdDate,

        @JsonView(View.Complete.class)
        String lastModifiedBy,

        @JsonView(View.Complete.class)
        Instant lastModifiedDate
) {

    public AuditResponseDTO(Audit audit) {
        this(
                audit.getCreatedBy(),
                audit.getCreatedDate(),
                audit.getLastModifiedBy(),
                audit.getLastModifiedDate()
        );
    }
}
