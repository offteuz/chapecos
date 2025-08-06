package br.com.fiap.chapecos.adapter.inbound.dto.response;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.domain.model.Audit;
import br.com.fiap.chapecos.domain.model.Item;
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
        MenuSummaryResponseDTO menu,

        @JsonView(View.Complete.class)
        String picture,

        @JsonView(View.Complete.class)
        AuditResponseDTO audit
) {

    public ItemResponseDTO(Item item) {
        this(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                new LocalConsumptionResponseDTO(item.getLocalConsumption()),
                new MenuSummaryResponseDTO(item.getMenu()),
                item.getPicture(),
                new AuditResponseDTO(item.getAudit())
        );
    }
}
