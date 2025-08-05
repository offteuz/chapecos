package br.com.fiap.chapecos.adapter.outbound.mapper;

import br.com.fiap.chapecos.infrastructure.exception.MenuNotFoundException;
import br.com.fiap.chapecos.domain.model.Menu;
import br.com.fiap.chapecos.domain.repository.MenuRepository;
import org.springframework.stereotype.Component;

@Component
public class MenuMapperHelper {

    public final MenuRepository menuRepository;

    public MenuMapperHelper(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu map(Long idMenu) {
        return menuRepository.findById(idMenu)
                .orElseThrow(MenuNotFoundException::new);
    }
}
