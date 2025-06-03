package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.model.Audit;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record AuditAllResponseDTO(

        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime createdAs,

        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime updatedAs
) {

    public AuditAllResponseDTO(Audit audit) {
        this(
                audit.getCreateAs(),
                audit.getUpdateAs()
        );
    }
}
