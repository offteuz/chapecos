package br.com.fiap.chapecos.adapter.inbound.controller;

import br.com.fiap.chapecos.config.view.View;
import br.com.fiap.chapecos.adapter.inbound.dto.request.RegistrationTimeRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.RegistrationTimeResponseDTO;
import br.com.fiap.chapecos.application.service.RegistrationTimeService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RegistrationTimeController {

    private final RegistrationTimeService registrationTimeService;

    public RegistrationTimeController(RegistrationTimeService registrationTimeService) {
        this.registrationTimeService = registrationTimeService;
    }

    @JsonView(View.Compact.class)
    @PostMapping("/registration-time/v1/create")
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationTimeResponseDTO create(@Valid @RequestBody RegistrationTimeRequestDTO dto) {
        return registrationTimeService.create(dto);
    }

    @JsonView(View.Compact.class)
    @GetMapping("/registration-time/v1/find-all")
    @ResponseStatus(HttpStatus.OK)
    public List<RegistrationTimeResponseDTO> findAll() {
        return registrationTimeService.findAll();
    }

    @JsonView(View.Complete.class)
    @GetMapping("/registration-time/v1/find-by-id/{idRegistrationTime}")
    @ResponseStatus(HttpStatus.OK)
    public RegistrationTimeResponseDTO findById(@PathVariable Long idRegistrationTime) {
        return registrationTimeService.findById(idRegistrationTime);
    }

    @DeleteMapping("/registration-time/v1/delete/{idRegistrationTime}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long idRegistrationTime) {
        registrationTimeService.delete(idRegistrationTime);
    }

    @JsonView(View.Complete.class)
    @PatchMapping("/registration-time/v1/update/{idRegistrationTime}")
    @ResponseStatus(HttpStatus.OK)
    public RegistrationTimeResponseDTO update(@Valid @PathVariable Long idRegistrationTime, @RequestBody RegistrationTimeRequestDTO dto) {
        return registrationTimeService.update(idRegistrationTime, dto);
    }
}
