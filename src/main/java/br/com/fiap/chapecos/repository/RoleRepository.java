package br.com.fiap.chapecos.repository;

import br.com.fiap.chapecos.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
