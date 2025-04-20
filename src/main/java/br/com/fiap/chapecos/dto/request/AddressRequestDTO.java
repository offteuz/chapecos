package br.com.fiap.chapecos.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressRequestDTO(

        @NotNull
        @Pattern(regexp = "^\\d{5}-\\d{3}$")
        String postalCode,

        @NotNull
        @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$")
        String street,

        @NotNull
        @Pattern(regexp = "^\\d+$")
        String number,

        @NotNull
        @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$")
        String neighborhood,

        @NotNull
        @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$")
        String city,

        @NotNull
        @Size(min = 2, max = 2)
        @Pattern(regexp = "^[A-Z]{2}$")
        String state,

        @NotNull
        @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$")
        String country
) {
}
