package br.com.fiap.chapecos.controller;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.dto.request.MenuRequestDTO;
import br.com.fiap.chapecos.dto.response.MenuResponseDTO;
import br.com.fiap.chapecos.service.MenuService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @JsonView(View.Synthetic.class)
    @PostMapping("/create/v1/menu")
    @ResponseStatus(HttpStatus.CREATED)
    public MenuResponseDTO create(@Valid @RequestBody MenuRequestDTO dto) {
        return menuService.create(dto);
    }
}
