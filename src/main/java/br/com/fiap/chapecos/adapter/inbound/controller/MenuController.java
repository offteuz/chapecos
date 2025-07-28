package br.com.fiap.chapecos.controller;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.dto.request.MenuRequestDTO;
import br.com.fiap.chapecos.dto.response.MenuResponseDTO;
import br.com.fiap.chapecos.service.MenuService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @JsonView(View.Compact.class)
    @PostMapping("/menu/v1/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MenuResponseDTO create(@Valid @RequestBody MenuRequestDTO dto) {
        return menuService.create(dto);
    }

    @JsonView(View.Compact.class)
    @GetMapping("/menu/v1/find-all")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuResponseDTO> findAll() {
        return menuService.findAll();
    }

    @JsonView(View.Complete.class)
    @GetMapping("/menu/v1/find-by-id/{idMenu}")
    @ResponseStatus(HttpStatus.OK)
    public MenuResponseDTO findById(@PathVariable Long idMenu) {
        return menuService.findById(idMenu);
    }

    @DeleteMapping("/menu/v1/delete/{idMenu}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long idMenu) {
        menuService.delete(idMenu);
    }
}
