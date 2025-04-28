package br.com.fiap.chapecos.dto.request;
import br.com.fiap.chapecos.model.Address;
import br.com.fiap.chapecos.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(

        @Email
        @NotNull
        String email,

        @NotNull
        String userName,

        @NotNull
        String password,

        @NotNull
        Address address,

        @NotNull
        Role role
) {
}
