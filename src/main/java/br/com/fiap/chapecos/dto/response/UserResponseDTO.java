package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.model.Address;
import br.com.fiap.chapecos.model.Audit;
import br.com.fiap.chapecos.model.Role;
import br.com.fiap.chapecos.model.User;
import com.fasterxml.jackson.annotation.JsonView;

public record UserResponseDTO(

        @JsonView(View.Synthetic.class)
        Long id,

        @JsonView(View.Analytic.class)
        String email,

        @JsonView(View.Synthetic.class)
        String userName,

        @JsonView(View.Analytic.class)
        Address address,

        @JsonView(View.Synthetic.class)
        Role role,

        @JsonView(View.Complete.class)
        Audit audit
) {

    public UserResponseDTO(User user) {
        this(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getAddress(),
                user.getRole(),
                user.getAudit()
        );
    }
}
