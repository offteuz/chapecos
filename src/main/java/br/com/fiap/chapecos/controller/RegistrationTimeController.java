package br.com.fiap.chapecos.controller;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.dto.request.RegistrationTimeRequestDTO;
import br.com.fiap.chapecos.dto.response.RegistrationTimeResponseDTO;
import br.com.fiap.chapecos.service.RegistrationTimeService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegistrationTimeController {

    private final RegistrationTimeService registrationTimeService;

    public RegistrationTimeController(RegistrationTimeService registrationTimeService) {
        this.registrationTimeService = registrationTimeService;
    }

    @JsonView(View.Analytic.class)
    @PostMapping("/registration-time/v1/create")
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationTimeResponseDTO create(@Valid @RequestBody RegistrationTimeRequestDTO dto) {
        return registrationTimeService.create(dto);
    }
}
