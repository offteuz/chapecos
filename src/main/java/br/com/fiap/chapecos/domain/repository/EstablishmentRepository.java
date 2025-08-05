package br.com.fiap.chapecos.domain.repository;

import br.com.fiap.chapecos.domain.model.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {
}
