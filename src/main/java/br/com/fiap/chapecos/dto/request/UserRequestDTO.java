package br.com.fiap.chapecos.dto.request;
import br.com.fiap.chapecos.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserRequestDTO(

        @NotNull
        @Email
        String email,

        @NotNull
        @Pattern(regexp = "^[A-Za-z]+\\s+[A-Za-z]{2,20}$",
                message = "O nome de usuário deve conter NOME e SOBRENOME separados, além de ter entre 2 e 20 caracteres (para cada palavra, antes e depois do espaco")
        String userName,

        @NotNull
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,20}$",
                message = "A senha deve conter entre 4 e 20 caracteres, com letras maiúsculas, minúsculas, números e símbolo especial.")
        String password,

        @NotNull
        AddressRequestDTO address
) {
}
