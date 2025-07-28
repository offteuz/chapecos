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
@Embeddable
public class Address {

    @Column(name = "postal_code")
    private String postalCode;

    private String street;

    private String number;

    private String neighborhood;

    private String city;

    private String state;

    private String country;
}
