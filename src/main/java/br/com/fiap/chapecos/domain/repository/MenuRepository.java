package br.com.fiap.chapecos.domain.repository;

import br.com.fiap.chapecos.domain.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
