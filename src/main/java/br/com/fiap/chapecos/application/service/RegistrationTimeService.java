package br.com.fiap.chapecos.application.service;

import br.com.fiap.chapecos.adapter.inbound.dto.request.RegistrationTimeRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.EstablishmentResponseDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.RegistrationTimeResponseDTO;
import br.com.fiap.chapecos.adapter.outbound.mapper.RegistrationTimeMapper;
import br.com.fiap.chapecos.domain.model.Establishment;
import br.com.fiap.chapecos.domain.model.RegistrationTime;
import br.com.fiap.chapecos.domain.repository.EstablishmentRepository;
import br.com.fiap.chapecos.domain.repository.RegistrationTimeRepository;
import br.com.fiap.chapecos.infrastructure.exception.EstablishmentNotFoundException;
import br.com.fiap.chapecos.infrastructure.exception.RegistrationTimeNotFoundException;
import br.com.fiap.chapecos.logic.ExpedientTime;
import br.com.fiap.chapecos.logic.TimeEntry;
import br.com.fiap.chapecos.logic.ValidatorSchedules;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RegistrationTimeService {

    private final RegistrationTimeRepository registrationTimeRepository;

    private final EstablishmentRepository establishmentRepository;

    private final RegistrationTimeMapper registrationTimeMapper;

    private final ValidatorSchedules validatorSchedules;

    public RegistrationTimeService(RegistrationTimeRepository registrationTimeRepository, RegistrationTimeMapper registrationTimeMapper, ValidatorSchedules validatorSchedules, EstablishmentRepository establishmentRepository) {
        this.registrationTimeRepository = registrationTimeRepository;
        this.registrationTimeMapper = registrationTimeMapper;
        this.validatorSchedules = validatorSchedules;
        this.establishmentRepository = establishmentRepository;
    }

    public RegistrationTimeResponseDTO create(RegistrationTimeRequestDTO dto) {
        Establishment establishment = establishmentRepository.findById(dto.idEstablishment())
                .orElseThrow(EstablishmentNotFoundException::new);

        RegistrationTime registrationTime = registrationTimeMapper.toModel(dto);

        Set<RegistrationTime> futureState = new HashSet<>(establishment.getRegistrationTimes());
        futureState.add(registrationTime);

        Set<TimeEntry> timeEntries = futureState.stream()
                .filter(Objects::nonNull)
                .map(expedient -> new TimeEntry(
                        expedient.getDayOfWeek(),
                        new ExpedientTime(expedient.getOpening(), expedient.getClosing())
                ))
                .collect(Collectors.toSet());

        validatorSchedules.validate(timeEntries);

        return new RegistrationTimeResponseDTO(registrationTimeRepository.save(registrationTime));

    }

    public List<RegistrationTimeResponseDTO> findAll() {
        return registrationTimeRepository.findAll()
                .stream()
                .map(RegistrationTimeResponseDTO::new)
                .toList();
    }

    public RegistrationTimeResponseDTO findById(Long idRegistrationTime) {
        RegistrationTime registrationTime = registrationTimeRepository.findById(idRegistrationTime)
                .orElseThrow(RegistrationTimeNotFoundException::new);

        return new RegistrationTimeResponseDTO(registrationTime);
    }

    public void delete(Long idRegistrationTime) {
        RegistrationTime registrationTime = registrationTimeRepository.findById(idRegistrationTime)
                .orElseThrow(RegistrationTimeNotFoundException::new);

        registrationTimeRepository.delete(registrationTime);
    }

    public RegistrationTimeResponseDTO update(Long idRegistrationTime, RegistrationTimeRequestDTO dto) {
        RegistrationTime registrationTime = registrationTimeRepository.findById(idRegistrationTime)
                .orElseThrow(RegistrationTimeNotFoundException::new);

        registrationTimeMapper.updateFromDto(dto, registrationTime);

        return new RegistrationTimeResponseDTO(registrationTimeRepository.save(registrationTime));
    }
}
