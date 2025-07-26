package br.com.fiap.chapecos.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.model.Audit;
import br.com.fiap.chapecos.model.Item;
import br.com.fiap.chapecos.model.LocalConsumption;
import com.fasterxml.jackson.annotation.JsonView;

public record ItemResponseDTO(

        @JsonView(View.Compact.class)
        Long idItem,

        @JsonView(View.Compact.class)
        String name,

        @JsonView(View.Synthetic.class)
        String description,

        @JsonView(View.Compact.class)
        Double price,

        @JsonView(View.Analytic.class)
        LocalConsumptionResponseDTO localConsumption,

        @JsonView(View.Compact.class)
        MenuResponseDTO menu,

        @JsonView(View.Complete.class)
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
