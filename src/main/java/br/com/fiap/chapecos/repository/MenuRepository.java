package br.com.fiap.chapecos.repository;

import br.com.fiap.chapecos.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
