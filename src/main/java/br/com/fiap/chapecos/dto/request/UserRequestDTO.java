package br.com.fiap.chapecos.dto.request;
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
        AddressRequestDTO address,

        @NotNull
        Long roleId
) {
}
