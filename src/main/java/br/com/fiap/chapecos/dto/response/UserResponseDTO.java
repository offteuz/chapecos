package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.model.Role;
import br.com.fiap.chapecos.model.User;

public record UserResponseDTO(

        Long id,

        String email,

        String userName,

        String password,

        AddressResponseDTO address,

        AuditResponseDTO audit,

        RoleResponseDTO role
) {

    public UserResponseDTO(User user) {
        this(
                user.getId(),
                user.getEmail(),
                user.getUserName(),
                user.getPassword(),
                new AddressResponseDTO(user.getAddress()),
                new AuditResponseDTO(user.getAudit().getCreateAs(), user.getAudit().getUpdateAs()),
                new RoleResponseDTO(user.getRole())
        );
    }
}
