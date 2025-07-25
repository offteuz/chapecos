package br.com.fiap.chapecos.controller;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.dto.request.EstablishmentRequestDTO;
import br.com.fiap.chapecos.dto.response.EstablishmentResponseDTO;
import br.com.fiap.chapecos.service.EstablishmentService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EstablishmentController {

    private final EstablishmentService establishmentService;

    public EstablishmentController(EstablishmentService establishmentService) {
        this.establishmentService = establishmentService;
    }

    @JsonView({View.Synthetic.class})
    @PostMapping("/establishment/v1/create")
    @ResponseStatus(HttpStatus.CREATED)
    public EstablishmentResponseDTO create(@Valid @RequestBody EstablishmentRequestDTO dto) {
        return establishmentService.create(dto);
    }
}
