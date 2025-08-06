package br.com.fiap.chapecos.application.service;

import br.com.fiap.chapecos.adapter.inbound.dto.request.EstablishmentRequestDTO;
import br.com.fiap.chapecos.adapter.inbound.dto.response.EstablishmentResponseDTO;
import br.com.fiap.chapecos.infrastructure.exception.EstablishmentNotFoundException;
import br.com.fiap.chapecos.infrastructure.exception.UserNotAdminException;
import br.com.fiap.chapecos.adapter.outbound.mapper.EstablishmentMapper;
import br.com.fiap.chapecos.domain.model.Establishment;
import br.com.fiap.chapecos.domain.model.Role;
import br.com.fiap.chapecos.domain.repository.EstablishmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstablishmentService {

    private final EstablishmentRepository establishmentRepository;

    private final EstablishmentMapper establishmentMapper;

    public EstablishmentService(EstablishmentRepository establishmentRepository, EstablishmentMapper establishmentMapper) {
        this.establishmentRepository = establishmentRepository;
        this.establishmentMapper = establishmentMapper;
    }

    public EstablishmentResponseDTO create(EstablishmentRequestDTO dto) {
        Establishment establishment = establishmentMapper.toModel(dto);

        if (establishment.getUser().getRole() != Role.ADMIN) {
            throw new UserNotAdminException();
        }

        return new EstablishmentResponseDTO(establishmentRepository.save(establishment));
    }

    public EstablishmentResponseDTO findById(Long idEstablishment) {
        Establishment establishment = establishmentRepository.findById(idEstablishment)
                .orElseThrow(EstablishmentNotFoundException::new);

        return new EstablishmentResponseDTO(establishment);
    }

    public List<EstablishmentResponseDTO> findAll() {
        return establishmentRepository.findAll()
                .stream()
                .map(EstablishmentResponseDTO::new)
                .toList();
    }

    public void delete(Long idEstablishment) {
        Establishment establishment = establishmentRepository.findById(idEstablishment)
                .orElseThrow(EstablishmentNotFoundException::new);

        establishmentRepository.delete(establishment);
    }

    public EstablishmentResponseDTO update(Long idEstablishment, EstablishmentRequestDTO dto) {
        Establishment establishment = establishmentRepository.findById(idEstablishment)
                .orElseThrow(EstablishmentNotFoundException::new);

        establishmentMapper.updateFromDto(dto, establishment);

        return new EstablishmentResponseDTO(establishmentRepository.save(establishment));
    }
}
