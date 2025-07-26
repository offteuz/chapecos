package br.com.fiap.chapecos.mapper;

import br.com.fiap.chapecos.dto.request.ItemRequestDTO;
import br.com.fiap.chapecos.dto.request.LocalConsumptionRequestDTO;
import br.com.fiap.chapecos.model.Item;
import br.com.fiap.chapecos.model.LocalConsumption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = MenuMapperHelper.class,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "idMenu", target = "menu")
    Item toModel(ItemRequestDTO dto);

    default LocalConsumption map(LocalConsumptionRequestDTO dto) {
        if (dto == null || dto.name() == null || dto.name().isBlank()) {
            return null;
        }
        return LocalConsumption.valueOf(dto.name());
    }
}
