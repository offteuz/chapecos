package br.com.fiap.chapecos.dto.request;

import br.com.fiap.chapecos.config.ValidationMessage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RoleRequestDTO(

        @NotNull(message = "O campo 'descricao' é obrigatório.")
        @Size(min = 3, max = 20)
        @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$")
        String description
) {
}
