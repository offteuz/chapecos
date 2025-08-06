package br.com.fiap.chapecos.adapter.inbound.controller;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.adapter.inbound.dto.request.EstablishmentRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.EstablishmentResponseDTO;
import br.com.fiap.chapecos.application.service.EstablishmentService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @JsonView({View.Synthetic.class})
    @GetMapping("/establishment/v1/find-all")
    @ResponseStatus(HttpStatus.OK)
    public List<EstablishmentResponseDTO> findAll() {
        return establishmentService.findAll();
    }

    @JsonView({View.Complete.class})
    @GetMapping("/establishment/v1/find-by-id/{idEstablishment}")
    @ResponseStatus(HttpStatus.OK)
    public EstablishmentResponseDTO findById(@PathVariable Long idEstablishment) {
        return establishmentService.findById(idEstablishment);
    }

    @DeleteMapping("/establishment/v1/delete/{idEstablishment}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long idEstablishment) {
        establishmentService.delete(idEstablishment);
    }
}
