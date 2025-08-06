package br.com.fiap.chapecos.adapter.inbound.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.domain.model.Address;
import br.com.fiap.chapecos.domain.model.Audit;
import br.com.fiap.chapecos.domain.model.Role;
import br.com.fiap.chapecos.domain.model.User;
import com.fasterxml.jackson.annotation.JsonView;

public record UserResponseDTO(

        @JsonView(View.Compact.class)
        Long id,

        @JsonView(View.Analytic.class)
        String email,

        @JsonView(View.Compact.class)
        String userName,

        @JsonView(View.Analytic.class)
        Address address,

        @JsonView(View.Compact.class)
        Role role,

        @JsonView(View.Complete.class)
        AuditResponseDTO audit
) {

    public UserResponseDTO(User user) {
        this(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getAddress(),
                user.getRole(),
                new AuditResponseDTO(user.getAudit())
        );
    }
}
