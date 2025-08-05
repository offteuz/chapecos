package br.com.fiap.chapecos.domain.repository;

import br.com.fiap.chapecos.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
