package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.domain.model.Audit;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record AuditUpdateAsResponseDTO(

        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime updatedAs
) {

    public AuditUpdateAsResponseDTO(Audit audit) {
        this(
                audit.getUpdateAs()
        );
    }
}
