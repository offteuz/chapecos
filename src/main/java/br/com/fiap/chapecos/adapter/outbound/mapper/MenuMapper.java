package br.com.fiap.chapecos.mapper;

import br.com.fiap.chapecos.dto.request.MenuRequestDTO;
import br.com.fiap.chapecos.dto.request.MenuTypeRequestDTO;
import br.com.fiap.chapecos.model.Menu;
import br.com.fiap.chapecos.model.MenuType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        uses = {EstablishmentMapperHelper.class})
public interface MenuMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    @Mapping(source = "idEstablishment", target = "establishment")
    Menu toModel(MenuRequestDTO dto);

    default MenuType map(MenuTypeRequestDTO dto) {
        if (dto == null || dto.name() == null || dto.name().isBlank()) {
            return null;
        }

        return MenuType.valueOf(dto.name());
    }
}
