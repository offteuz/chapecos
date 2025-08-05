package br.com.fiap.chapecos.adapter.outbound.mapper;

import br.com.fiap.chapecos.adapter.inbound.dto.request.AddressRequestDTO;
import br.com.fiap.chapecos.domain.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AddressMapper {

    Address toModel(AddressRequestDTO dto);
}
