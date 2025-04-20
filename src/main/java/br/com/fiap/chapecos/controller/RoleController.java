package br.com.fiap.chapecos.controller;

import br.com.fiap.chapecos.dto.request.RoleRequestDTO;
import br.com.fiap.chapecos.dto.response.RoleResponseDTO;
import br.com.fiap.chapecos.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create/v0/role")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponseDTO create(@Valid @RequestBody RoleRequestDTO dto) {
        return roleService.create(dto);
    }

    @GetMapping("/find-all/v0/role")
    @ResponseStatus(HttpStatus.OK)
    public List<RoleResponseDTO> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/find-by-id/v0/role/{idRole}")
    @ResponseStatus(HttpStatus.OK)
    public RoleResponseDTO findById(@PathVariable Long idRole) {
        return roleService.findById(idRole);
    }
}
