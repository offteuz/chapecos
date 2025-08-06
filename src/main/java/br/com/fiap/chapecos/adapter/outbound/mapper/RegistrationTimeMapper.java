package br.com.fiap.chapecos.adapter.outbound.mapper;

import br.com.fiap.chapecos.adapter.inbound.dto.request.RegistrationTimeRequestDTO;
import br.com.fiap.chapecos.domain.model.Establishment;
import br.com.fiap.chapecos.domain.model.RegistrationTime;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {EstablishmentMapperHelper.class},
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface RegistrationTimeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "idEstablishment", target = "establishment")
    default RegistrationTime toModel(RegistrationTimeRequestDTO dto) {
        RegistrationTime rt = new RegistrationTime();
        rt.setDayOfWeek(dto.dayOfWeek());
        rt.setOpening(dto.opening());
        rt.setClosing(dto.closing());

        Establishment est = new Establishment();
        est.setId(dto.idEstablishment());
        rt.setEstablishment(est);

        return rt;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(RegistrationTimeRequestDTO dto, @MappingTarget RegistrationTime registrationTime);
}
