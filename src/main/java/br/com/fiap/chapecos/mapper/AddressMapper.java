package br.com.fiap.chapecos.mapper;

import br.com.fiap.chapecos.dto.request.AddressRequestDTO;
import br.com.fiap.chapecos.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AddressMapper {

    Address toDto(AddressRequestDTO dto);
}
