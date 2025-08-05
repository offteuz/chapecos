package br.com.fiap.chapecos.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
