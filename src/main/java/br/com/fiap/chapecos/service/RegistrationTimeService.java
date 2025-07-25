package br.com.fiap.chapecos.service;

import br.com.fiap.chapecos.dto.request.RegistrationTimeRequestDTO;
import br.com.fiap.chapecos.dto.response.RegistrationTimeResponseDTO;
import br.com.fiap.chapecos.mapper.RegistrationTimeMapper;
import br.com.fiap.chapecos.model.RegistrationTime;
import br.com.fiap.chapecos.repository.RegistrationTimeRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationTimeService {

    private final RegistrationTimeRepository registrationTimeRepository;

    private final RegistrationTimeMapper registrationTimeMapper;

    public RegistrationTimeService(RegistrationTimeRepository registrationTimeRepository, RegistrationTimeMapper registrationTimeMapper) {
        this.registrationTimeRepository = registrationTimeRepository;
        this.registrationTimeMapper = registrationTimeMapper;
    }

    public RegistrationTimeResponseDTO create(RegistrationTimeRequestDTO dto) {
        RegistrationTime registrationTime = registrationTimeMapper.toModel(dto);

        return new RegistrationTimeResponseDTO(registrationTimeRepository.save(registrationTime));
    }
}
