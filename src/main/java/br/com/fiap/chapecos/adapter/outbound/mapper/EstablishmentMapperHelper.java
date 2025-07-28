package br.com.fiap.chapecos.mapper;

import br.com.fiap.chapecos.exception.EstablishmentNotFoundException;
import br.com.fiap.chapecos.model.Establishment;
import br.com.fiap.chapecos.repository.EstablishmentRepository;
import org.springframework.stereotype.Component;

@Component
public class EstablishmentMapperHelper {

    public final EstablishmentRepository establishmentRepository;

    public EstablishmentMapperHelper(EstablishmentRepository establishmentRepository) {
        this.establishmentRepository = establishmentRepository;
    }

    public Establishment map(Long idEstablishment) {
        return establishmentRepository.findById(idEstablishment)
                .orElseThrow(EstablishmentNotFoundException::new);
    }
}
