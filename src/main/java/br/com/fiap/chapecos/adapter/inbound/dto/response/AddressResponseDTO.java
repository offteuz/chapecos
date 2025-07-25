package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.domain.model.Address;

public record AddressResponseDTO(

        String postalCode,

        String street,

        String number,

        String neighborhood,

        String city,

        String state,

        String country
) {

    public AddressResponseDTO(Address address) {
        this(
                address.getPostalCode(),
                address.getStreet(),
                address.getNumber(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getCountry()
        );
    }
}
