package br.com.fiap.chapecos.adapter.outbound.mapper;

import br.com.fiap.chapecos.adapter.inbound.dto.request.ItemRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.request.LocalConsumptionRequestDTO;
import br.com.fiap.chapecos.domain.model.Item;
import br.com.fiap.chapecos.domain.model.LocalConsumption;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = MenuMapperHelper.class,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "idMenu", target = "menu")
    Item toModel(ItemRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(ItemRequestDTO dto, @MappingTarget Item item);

    default LocalConsumption map(LocalConsumptionRequestDTO dto) {
        if (dto == null || dto.name() == null || dto.name().isBlank()) {
            return null;
        }
        return LocalConsumption.valueOf(dto.name());
    }
}
