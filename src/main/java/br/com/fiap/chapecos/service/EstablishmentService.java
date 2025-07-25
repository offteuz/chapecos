package br.com.fiap.chapecos.service;

import br.com.fiap.chapecos.dto.request.EstablishmentRequestDTO;
import br.com.fiap.chapecos.dto.response.EstablishmentResponseDTO;
import br.com.fiap.chapecos.exception.EstablishmentNotFoundException;
import br.com.fiap.chapecos.exception.UserNotAdminException;
import br.com.fiap.chapecos.mapper.EstablishmentMapper;
import br.com.fiap.chapecos.model.Address;
import br.com.fiap.chapecos.model.Establishment;
import br.com.fiap.chapecos.model.Role;
import br.com.fiap.chapecos.repository.EstablishmentRepository;
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

    public EstablishmentResponseDTO findById(Long id) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(EstablishmentNotFoundException::new);

        return new EstablishmentResponseDTO(establishment);
    }

    public List<EstablishmentResponseDTO> findAll() {
        return establishmentRepository.findAll()
                .stream()
                .map(EstablishmentResponseDTO::new)
                .toList();
    }

    public void delete(Long id) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(EstablishmentNotFoundException::new);

        establishmentRepository.delete(establishment);
    }
}
