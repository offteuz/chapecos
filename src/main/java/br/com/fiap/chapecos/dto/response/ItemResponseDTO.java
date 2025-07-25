package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.model.Audit;
import br.com.fiap.chapecos.model.Item;
import br.com.fiap.chapecos.model.LocalConsumption;

public record ItemResponseDTO(

        Long id,

        String name,

        String description,

        Double price,

        LocalConsumptionResponseDTO localConsumption,

        MenuResponseDTO menu,

        Audit audit
) {

    public ItemResponseDTO(Item item) {
        this(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                new LocalConsumptionResponseDTO(item.getLocalConsumption()),
                new MenuResponseDTO(item.getMenu()),
                item.getAudit()
        );
    }
}
