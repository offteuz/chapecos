package br.com.fiap.chapecos.adapter.outbound.mapper;

import br.com.fiap.chapecos.adapter.inbound.dto.request.MenuRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.request.MenuTypeRequestDTO;
import br.com.fiap.chapecos.domain.model.Menu;
import br.com.fiap.chapecos.domain.model.MenuType;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        uses = {EstablishmentMapperHelper.class})
public interface MenuMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    @Mapping(source = "idEstablishment", target = "establishment")
    Menu toModel(MenuRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(MenuRequestDTO dto, @MappingTarget Menu menu);

    default MenuType map(MenuTypeRequestDTO dto) {
        if (dto == null || dto.name() == null || dto.name().isBlank()) {
            return null;
        }

        for (MenuType type : MenuType.values()) {
            if (type.getName().equalsIgnoreCase(dto.name())) {
                return type;
            }
        }

        throw new IllegalArgumentException("Tipo de menu inválido" + dto.name());
    }
}
