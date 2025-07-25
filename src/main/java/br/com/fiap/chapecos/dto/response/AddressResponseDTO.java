package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.dto.request.AddressRequestDTO;
import br.com.fiap.chapecos.model.Address;
import com.fasterxml.jackson.annotation.JsonView;

public record AddressResponseDTO(

        @JsonView(View.Synthetic.class)
        String postalCode,

        @JsonView(View.Synthetic.class)
        String street,

        @JsonView(View.Synthetic.class)
        String number,

        @JsonView(View.Synthetic.class)
        String neighborhood,

        @JsonView(View.Synthetic.class)
        String city,

        @JsonView(View.Synthetic.class)
        String state,

        @JsonView(View.Synthetic.class)
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
