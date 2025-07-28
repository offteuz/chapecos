package br.com.fiap.chapecos.repository;

import br.com.fiap.chapecos.model.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {
}
