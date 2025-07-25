package br.com.fiap.chapecos.repository;

import br.com.fiap.chapecos.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
