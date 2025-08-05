package br.com.fiap.chapecos.adapter.outbound.mapper;

import br.com.fiap.chapecos.adapter.inbound.dto.request.RegistrationTimeRequestDTO;
import br.com.fiap.chapecos.domain.model.Establishment;
import br.com.fiap.chapecos.domain.model.RegistrationTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {EstablishmentMapperHelper.class},
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface RegistrationTimeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "idEstablishment", target = "establishment")
    //RegistrationTime toModel(RegistrationTimeRequestDTO dto);
    public default RegistrationTime toModel(RegistrationTimeRequestDTO dto) {
        RegistrationTime rt = new RegistrationTime();
        rt.setDayOfWeek(dto.dayOfWeek());
        rt.setOpening(dto.opening());
        rt.setClosing(dto.closing());

        Establishment est = new Establishment();
        est.setId(dto.idEstablishment());
        rt.setEstablishment(est);

        return rt;
    }

}
