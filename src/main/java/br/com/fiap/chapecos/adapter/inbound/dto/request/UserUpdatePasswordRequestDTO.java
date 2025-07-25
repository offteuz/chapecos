package br.com.fiap.chapecos.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserUpdatePasswordRequestDTO(

        @NotNull
        String currentPassword,

        @NotNull
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,20}$",
                message = "A nova senha deve conter entre 4 e 20 caracteres, com letras maiúsculas, minúsculas, números e símbolo especial.")
        String newPassword
) {
}
