package br.com.fiap.chapecos.adapter.inbound.controller;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.adapter.inbound.dto.request.ItemRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.ItemResponseDTO;
import br.com.fiap.chapecos.application.service.ItemService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemController {

    public final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @JsonView(View.Compact.class)
    @PostMapping("/item/v1/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponseDTO create(@Valid @RequestBody ItemRequestDTO dto) {
        return itemService.create(dto);
    }

    @JsonView(View.Compact.class)
    @GetMapping("/item/v1/find-all")
    @ResponseStatus(HttpStatus.OK)
    public List<ItemResponseDTO> findAll() {
        return itemService.findAll();
    }

    @JsonView(View.Complete.class)
    @GetMapping("/item/v1/find-by-id/{idItem}")
    @ResponseStatus(HttpStatus.OK)
    public ItemResponseDTO findById(@PathVariable Long idItem) {
        return itemService.findById(idItem);
    }

    @DeleteMapping("/item/v1/delete/{idItem}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long idItem) {
        itemService.delete(idItem);
    }
}
