package br.com.fiap.chapecos.mapper;

import br.com.fiap.chapecos.dto.request.EstablishmentRequestDTO;
import br.com.fiap.chapecos.dto.request.KitchenCategoryRequestDTO;
import br.com.fiap.chapecos.dto.request.KitchenTypeRequestDTO;
import br.com.fiap.chapecos.dto.response.KitchenCategoryResponseDTO;
import br.com.fiap.chapecos.dto.response.KitchenTypeResponseDTO;
import br.com.fiap.chapecos.model.Establishment;
import br.com.fiap.chapecos.model.KitchenType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    uses = {UserMapperHelper.class, AddressMapper.class},
    unmappedTargetPolicy = ReportingPolicy.WARN)
public interface EstablishmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registrationTimes", ignore = true)
    @Mapping(source = "userId", target = "user")
    @Mapping(source = "address", target = "address")
    Establishment toModel(EstablishmentRequestDTO dto);

    void update(EstablishmentRequestDTO dto, @MappingTarget Establishment establishment);

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
