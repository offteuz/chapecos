package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.model.Role;
import br.com.fiap.chapecos.model.User;

public record UserResponseDTO(

        Long id,

        String email,

        String userName,

        AddressResponseDTO address,

        Role role,

        AuditAllResponseDTO audit
) {

    public UserResponseDTO(User user) {
        this(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                new AddressResponseDTO(user.getAddress()),
                user.getRole(),
                new AuditAllResponseDTO(user.getAudit().getCreateAs(), user.getAudit().getUpdateAs())
        );
    }
}
