package br.com.fiap.chapecos.adapter.inbound.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressRequestDTO(

        @Pattern(regexp = "^\\d{5}-\\d{3}$")
        @NotNull
        String postalCode,

        @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$")
        @NotNull
        String street,

        @Pattern(regexp = "^\\d+$")
        @NotNull
        String number,

        @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$")
        @NotNull
        String neighborhood,

        @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$")
        @NotNull
        String city,

        @Size(min = 2, max = 2)
        @Pattern(regexp = "^[A-Z]{2}$")
        @NotNull
        String state,

        @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$")
        @NotNull
        String country
) {
}
