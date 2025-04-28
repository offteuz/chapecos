package br.com.fiap.chapecos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Embeddable
public class Address {

    @Column(name = "postal_code")
    @NotNull
    @Pattern(regexp = "^\\d{5}-\\d{3}$")
    private String postalCode;

    @NotNull
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$")
    private String street;

    @NotNull
    @Pattern(regexp = "^\\d+$")
    private String number;

    @NotNull
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$")
    private String neighborhood;

    @NotNull
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$")
    private String city;

    @NotNull
    @Size(min = 2, max = 2)
    @Pattern(regexp = "^[A-Z]{2}$")
    private String state;

    @NotNull
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$")
    private String country;
}
