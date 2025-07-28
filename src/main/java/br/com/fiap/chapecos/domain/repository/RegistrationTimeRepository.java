package br.com.fiap.chapecos.repository;

import br.com.fiap.chapecos.model.RegistrationTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationTimeRepository extends JpaRepository<RegistrationTime, Long> {
}
