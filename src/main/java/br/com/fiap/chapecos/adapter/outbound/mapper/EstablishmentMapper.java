package br.com.fiap.chapecos.adapter.outbound.mapper;

import br.com.fiap.chapecos.adapter.inbound.dto.request.EstablishmentRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.request.KitchenTypeRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.EstablishmentResponseDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.KitchenCategoryResponseDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.KitchenTypeResponseDTO;
import br.com.fiap.chapecos.domain.model.Establishment;
import br.com.fiap.chapecos.domain.model.KitchenType;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
    uses = {UserMapperHelper.class, AddressMapper.class},
    unmappedTargetPolicy = ReportingPolicy.WARN)
public interface EstablishmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registrationTimes", ignore = true)
    @Mapping(source = "userId", target = "user")
    @Mapping(source = "address", target = "address")
    Establishment toModel(EstablishmentRequestDTO dto);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "kitchenType", target = "kitchenType")
    EstablishmentResponseDTO toResponse(Establishment establishment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(EstablishmentRequestDTO dto, @MappingTarget Establishment establishment);

    default KitchenType map(KitchenTypeRequestDTO dto) {
        if (dto == null || dto.name() == null || dto.name().isBlank()) {
            return null;
        }
        return KitchenType.valueOf(dto.name());
    }

    default KitchenTypeResponseDTO mapKitchenType(KitchenType kitchenType) {

        if (kitchenType == null) {
            return null;
        }

        KitchenCategoryResponseDTO dto = new KitchenCategoryResponseDTO(
                kitchenType.getCategoryKitchen().name()
        ) ;

        return new KitchenTypeResponseDTO(
                kitchenType.name(),
                dto
        );
    }
}
