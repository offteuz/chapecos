package br.com.fiap.chapecos.domain.repository;

import br.com.fiap.chapecos.domain.model.RegistrationTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationTimeRepository extends JpaRepository<RegistrationTime, Long> {
}
