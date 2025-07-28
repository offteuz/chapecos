package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.model.Role;
import br.com.fiap.chapecos.model.User;

public record UserUpdateResponseDTO(

        Long id,

        String email,

        String userName,

        AddressResponseDTO address,

        Role role,

        AuditUpdateAsResponseDTO audit
) {

    public UserUpdateResponseDTO(User user) {
        this(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                new AddressResponseDTO(user.getAddress()),
                user.getRole(),
                new AuditUpdateAsResponseDTO(user.getAudit().getUpdateAs())
        );
    }
}
