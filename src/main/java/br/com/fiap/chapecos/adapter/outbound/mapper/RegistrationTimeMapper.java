package br.com.fiap.chapecos.mapper;

import br.com.fiap.chapecos.dto.request.RegistrationTimeRequestDTO;
import br.com.fiap.chapecos.model.RegistrationTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {EstablishmentMapperHelper.class},
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface RegistrationTimeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "idEstablishment", target = "establishment")
    RegistrationTime toModel(RegistrationTimeRequestDTO dto);
}
