package br.com.fiap.chapecos.controller;

import br.com.fiap.chapecos.dto.request.ItemRequestDTO;
import br.com.fiap.chapecos.dto.response.ItemResponseDTO;
import br.com.fiap.chapecos.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ItemController {

    public final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/create/v1/item")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponseDTO create(@Valid @RequestBody ItemRequestDTO dto) {
        return itemService.create(dto);
    }
}
